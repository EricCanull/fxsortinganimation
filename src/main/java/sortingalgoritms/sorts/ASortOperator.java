package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;
import sortingalgoritms.util.IHandler;
import sortingalgoritms.util.Logger;

/**
 * An abstract base for the concrete sorting classes
 *
 * @author Eric Canull
 * @version 1.0
 */
public abstract class ASortOperator {
       
    /**
     * Sets up the sorting data and then invokes the abstract method to start
     * sorting.
     *
     * @param numbers the array of numbers to be sorted.
     * @param lowIndex the low index of the array.
     * @param highIndex the high index of array.
     */
    public final void sort(IComparable[] numbers, int lowIndex, int highIndex) {
        startSort(numbers, lowIndex, highIndex);
    }

    /**
     * The abstract method used by all of the concrete sorting classes
     *
     * @param numbers the array of numbers to be sorted.
     * @param lowIndex the low index of the array.
     * @param highIndex the high index of array.
     */
    protected abstract void startSort(IComparable[] numbers, int lowIndex, int highIndex);

    /**
     * Resets the sort count.
     */
    protected void resetCount() {
        Logger.resetCount();
    }

    /**
     * Increments iteration count.
     */
    protected void count() {
        Logger.count();
    }
    
//     /**
//     * Returns either a sort or a null object
//     * @param array an array of objects to be evaluated
//     * @param handler input object to compare values
//     * @return an output sorted object or null
//     */
//    public Object[] apply(Object array[], IHandler handler) {
//        Object[] result = new Object[array.length];
//        for (int i = 0; i < array.length; i++) {
//            result[i] = handler.apply (array[i]);
//        }
//        return result;
//    }
//    
//    /**
//     * Null object pattern method
//     * @param arg an object
//     * @return 
//     */
//    @Override
//    public Object apply(Object arg) {
//        return null;
//    }
}
