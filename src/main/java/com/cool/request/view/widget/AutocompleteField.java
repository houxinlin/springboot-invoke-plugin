package com.cool.request.view.widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public final class AutocompleteField extends JTextField implements FocusListener, DocumentListener, KeyListener {

    private Function<String, List<String>> lookup;
    private final List<String> results;
    private final JWindow popup;
    private final JList list;
    private final ListModel model;

    public AutocompleteField(final Function<String, List<String>> lookup) {
        super();
        this.lookup = lookup;
        this.results = new ArrayList<>();

        final Window parent = SwingUtilities.getWindowAncestor(this);
        popup = new JWindow(parent);
        popup.setType(Window.Type.POPUP);
        popup.setFocusableWindowState(false);
        popup.setAlwaysOnTop(true);

        model = new ListModel();
        list = new JList(model);

        JScrollPane jScrollPane = new JScrollPane(list) {
            @Override
            public Dimension getPreferredSize() {
                final Dimension ps = super.getPreferredSize();
                ps.width = AutocompleteField.this.getWidth();
                ps.height = 100;
                return ps;
            }
        };
        popup.add(jScrollPane);

        addFocusListener(this);
        getDocument().addDocumentListener(this);
        addKeyListener(this);
    }

    public void setLookup(Function<String, List<String>> lookup) {
        this.lookup = lookup;
    }

    private void showAutocompletePopup() {
        final Point los = AutocompleteField.this.getLocationOnScreen();
        popup.setLocation(los.x, los.y + getHeight());
        popup.setVisible(true);
    }

    private void hideAutocompletePopup() {
        popup.setVisible(false);
    }

    @Override
    public void focusGained(final FocusEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (results.size() > 0) {
                showAutocompletePopup();
            }
        });
    }

    private void documentChanged() {
        SwingUtilities.invokeLater(() -> {
            results.clear();
            if (lookup != null) {
                results.addAll(lookup.apply(getText()));
            }

            model.updateView();
            list.setVisibleRowCount(Math.min(results.size(), 10));

            if (results.size() > 0) {
                list.setSelectedIndex(0);
            }

            popup.pack();

            if (results.size() > 0) {
                showAutocompletePopup();
            } else {
                hideAutocompletePopup();
            }
        });
    }

    @Override
    public void focusLost(final FocusEvent e) {
        SwingUtilities.invokeLater(this::hideAutocompletePopup);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            final int index = list.getSelectedIndex();
            if (index != -1 && index > 0) {
                list.setSelectedIndex(index - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            final int index = list.getSelectedIndex();
            if (index != -1 && list.getModel().getSize() > index + 1) {
                list.setSelectedIndex(index + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            final String text = (String) list.getSelectedValue();
            setText(text);
            setCaretPosition(text.length());
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            hideAutocompletePopup();
        }
    }

    @Override
    public void insertUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void changedUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    private class ListModel extends AbstractListModel {
        @Override
        public int getSize() {
            return results.size();
        }

        @Override
        public Object getElementAt(final int index) {
            return results.get(index);
        }


        public void updateView() {
            super.fireContentsChanged(AutocompleteField.this, 0, getSize());
        }
    }
}