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
package sortingalgoritms.sorts;

import sortingalgoritms.util.ISortOperator;

/**
 * Creates a singleton for retrieving the bars array data as its being sorted
 * after the timer animation is started.
 *
 * @author Eric Canull
 * @version 1.0
 */
public class SortOperator implements ISortOperator {
    private SortOperator() {} /* non-use private constructor */

    private static SortOperator sortOperator = null;
    
    public static SortOperator getInstance() {
        if (sortOperator == null) {
            sortOperator = new SortOperator();
        }
        return sortOperator;
    }
 
    /**
     * Returns the sorted array every time the timer running
     * @param objects an array being sorted
     * @param sortOperator an object that returns the sorted array
     * @return an output sorted object or null
     */
    public Object[] apply(Object objects[], ISortOperator sortOperator) {
        Object[] result = new Object[objects.length];
        for(int i = 0; i < objects.length; i++) {
            result[i] = sortOperator.apply(objects[i]);
        }
        return result;
    }
    
    /**
     * Null object pattern method
     * @param arg an object
     */
    @Override
    public Object apply(Object arg) {
        return null;
    }
}