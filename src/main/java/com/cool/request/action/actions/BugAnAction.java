/*
 * Copyright 2024 XIN LIN HOU<hxl49508@gmail.com>
 * BugAnAction.java is part of Cool Request
 *
 * License: GPL-3.0+
 *
 * Cool Request is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cool Request is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cool Request.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cool.request.action.actions;

import com.cool.request.common.icons.CoolRequestIcons;
import com.cool.request.utils.ResourceBundleUtils;
import com.cool.request.view.dialog.BugDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class BugAnAction extends BaseAnAction {
    /**
     * BugAction is a class that extends BaseAnAction. It represents an action related to bugs in the system.
     * This action, when triggered, opens up a BugDialog.
     */
    public BugAnAction(Project project) {
        super(project, () -> ResourceBundleUtils.getString("bug"),
                () -> ResourceBundleUtils.getString("bug"), CoolRequestIcons.DEBUG );
    }

    /**
     * This method is triggered when the associated action is performed.
     * It creates a new BugDialog and displays it.
     *
     * @param anActionEvent The event object associated with the action.
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        BugDialog bugDialog = new BugDialog(anActionEvent.getProject());
        bugDialog.show();
    }
}
