package sortingalgoritms.util;

/**
 * Returns the numbers and colors that are determined during the sorting
 * process.
 * @author Eric Canull
 * @version 1.0
 */
public final class UtilSortHandler implements IHandler {
    
    /**
     * A concrete sorting class
     */
    public final static UtilSortHandler SortClass = new UtilSortHandler();
    
    /**
     * Returns the sorted array from the concrete sorting classes.
     */
    private UtilSortHandler(){
    }
    
    /**
     * Returns the sorted array every time the timer running
     * @param array an array of objects to be evaluated
     * @param cmd input object to compare values
     * @return an output sorted object or null
     */
    public Object[] apply(Object array[], IHandler cmd) {
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