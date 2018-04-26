/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sortingalgoritms.util;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import sortingalgoritms.MainController;

/**
 * Creates comparable value with changing colors.
 *
 * @author Eric Canull
 */
public final class CompareValue implements IComparable {
    
    private int value;
    private Color color;
  
    int i;
    
    /**
     * Creates comparable value with changing colors.
     *
     * @param value an integer reference to a number
     */
    public CompareValue(int value) {
        this.value = value;
        this.color = NORMAL_COLOR;
    }
    
      /**
     * Compares two values, changes their colors and returns -1, 0, or 1.
     *
     * @param comparable
     * @return An object with a value less than, greater than, or equals
     */
    @Override
    public int compare(IComparable comparable) {
        CompareValue compareValue = (CompareValue) comparable;

        compareValue.color = SELECTED_COLOR;
        color = SELECTED_COLOR;
         try {
            Thread.sleep(MainController.DELAY_PROPERTY.get()/2);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        if (value < compareValue.value) {
            compareValue.color = GREATER_COLOR;
            color = LESS_COLOR;
            i = IComparable.LESS;
        } else if (value > compareValue.value) {
            compareValue.color = LESS_COLOR;
            color = GREATER_COLOR;
            i = IComparable.GREATER;
        } else {
            i = IComparable.EQUAL;
        }
          try {
            Thread.sleep(MainController.DELAY_PROPERTY.get()/2);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
         compareValue.color = NORMAL_COLOR;
        color = NORMAL_COLOR;
        return i;
    }
    
    /**
     * Gets the value.
     *
     * @return An integer value specifying the current number stored
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value a reference to the new integer value to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the color
     * @param color 
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color
     * @return 
     */
    @Override
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Gets the value as a string
     * @return 
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
