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

import sortingalgoritms.util.ISortHandler;

/**
 * Creates a singleton for retrieving the bars array data as its being sorted
 * after the timer animation is started.
 *
 * @author Eric Canull
 * @version 1.0
 */
public class BaseSortSingleton implements ISortHandler {

    private static final BaseSortSingleton SINGLETON = new BaseSortSingleton();
    
    public static BaseSortSingleton getSingleton() {
        return SINGLETON;
    }
 
    private BaseSortSingleton(){ /*private constructor */ }

    /**
     * Returns the sorted array every time the timer running
     * @param array an array of objects to be evaluated
     * @param cmd input object to compare values
     * @return an output sorted object or null
     */
    public Object[] apply(Object array[], ISortHandler cmd) {
        Object[] result = new Object[array.length];
        for(int i = 0; i < array.length; i++) {
            result[i] = cmd.apply (array[i]);
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