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

import sortingalgoritms.util.IComparable;
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
 }
