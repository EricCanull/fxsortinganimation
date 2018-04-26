/*
 * Copyright (C) 2018 Your Organisation
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package sortingalgoritms.ui;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author Eric Canull
 */
public final class Bar extends Region {
    
    public final SimpleObjectProperty<Color> colorProperty = new SimpleObjectProperty<>(Color.web("#3073b4"));
   
    public Bar() {
        
        colorProperty.addListener(this::colorPropertyAction);
    }

    public void colorPropertyAction(Observable observable) {
        if (colorProperty.get() == null) {
            return;
        }
        String color = "#".concat(Integer.toHexString(colorProperty.get().hashCode()));
        setStyle("-fx-background-color: " + color + ";");
    }
}
