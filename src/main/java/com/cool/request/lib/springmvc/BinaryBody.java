/*
 * Copyright 2024 XIN LIN HOU<hxl49508@gmail.com>
 * BinaryBody.java is part of Cool Request
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

package com.cool.request.lib.springmvc;

import com.cool.request.components.http.net.MediaTypes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BinaryBody implements Body {
    private String selectFile;

    public BinaryBody(String selectFile) {
        this.selectFile = selectFile;
    }

    @Override
    public byte[] contentConversion() {
        if (selectFile == null) return new byte[0];
        if (Files.exists(Paths.get(selectFile))) {
            try {
                return Files.readAllBytes(Paths.get(selectFile));
            } catch (IOException e) {

            }
        }
        return new byte[0];
    }
    @Override
    public String getMediaType() {
        return MediaTypes.APPLICATION_STREAM;
    }
    public String getSelectFile() {
        return selectFile;
    }
}
