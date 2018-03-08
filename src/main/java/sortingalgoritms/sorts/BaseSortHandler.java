package sortingalgoritms.sorts;

import sortingalgoritms.util.IHandler;

/**
 * Used to create a singleton sort class and handle the incremental sorting 
 * changes during the timer animation update.
 * @author Eric Canull
 * @version 1.0
 */
public final class BaseSortHandler implements IHandler {
    
    /**
     * A singleton sorting class object
     */
    public final static BaseSortHandler SINGLETON = new BaseSortHandler();
    
    /**
     * Private constructor 
     */
    private BaseSortHandler(){
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