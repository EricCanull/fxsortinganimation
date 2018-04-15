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
package sortingalgoritms.ui;

import javafx.scene.paint.Color;
import sortingalgoritms.MainController;
import sortingalgoritms.util.IComparable;

/**
 *  A rectangular bar with an index and given value.
 *
 * @author Eric Canull
 */
public final class CompareValue implements IComparable {
    
    private final Color NORMAL_COLOR = Color.web("#3073b4");
    private final Color SELECTED_COLOR = Color.web("#a07617");
    
    private int index;
    private int value;
    
    private Color color;
   
    /**
     * Creates a rectangular bar with a index and value.
     *
     * @param index
     * @param value an integer reference to a number
     */
    public CompareValue(int index, int value) {
        this.index = index;
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
        
        color = SELECTED_COLOR;

        compareValue.color = SELECTED_COLOR;

        try {
            Thread.sleep(MainController.DELAY_PROPERTY.get());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        color = NORMAL_COLOR;
        
        compareValue.color = NORMAL_COLOR;

        return value < compareValue.value ? IComparable.LESS // -1 
                : value > compareValue.value ? IComparable.GREATER //  1
                        : IComparable.EQUAL;  //  0                              
    }

    /**
     * Gets the index of the bar
     * @return 
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Sets the index of the bar
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the value of the bar.
     *
     * @return An integer value specifying the current number stored
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the bar.
     *
     * @param number a reference to the new integer value to be set
     */
    public void setValue(int number) {
        this.value = number;
    }

    /**
     * Sets the color of the bar
     * @param color 
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the bar
     * @return 
     */
    @Override
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Gets the value of the bar as a string
     * @return 
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
