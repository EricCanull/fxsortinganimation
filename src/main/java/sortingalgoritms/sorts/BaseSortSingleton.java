/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
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