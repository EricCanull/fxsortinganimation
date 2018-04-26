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

import javafx.scene.paint.Color;

/**
 * Implements the functionality of the collection lists java.lang.Comparable.
 */
public interface IComparable {

    int LESS = -1;
    int EQUAL = 0;
    int GREATER = 1;

    Color NORMAL_COLOR = Color.web("#3073b4");
    Color SELECTED_COLOR = Color.web("#a07617");
    Color GREATER_COLOR = Color.web("#2da762");
    Color LESS_COLOR = Color.web("#7F5096");
    
    /**
     * Similar to Comparable.compareTo
     *
     * @param number a value to compare
     * @return the result of the comparison
     */
    int compare(IComparable number);   
    
    void setColor(Color color);

    Color getColor();
}
