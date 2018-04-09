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

/**
 * Implementation of the Insertion Sorting algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CInsertionSorter extends ASortOperator {

    public static final CInsertionSorter SINGLETON = new CInsertionSorter();

    /**
     * Implementation of the Insertion Sorting algorithm.
     */
    private CInsertionSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Implementation of the Insertion Sorting algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex  the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        // Sub-array used to hold the sorted numbers
        IComparable temp;
        resetCount();

        // Iterates through numbers array one time, swapping any numbers it finds less than 
        // it's next index until reaching the last and the array is sorted
        for (int i = 1; i < numbers.length; i++) {
            count();
            for (int j = i; j > 0; j--) {
                count();
                if (numbers[j].compare(numbers[j - 1]) == IComparable.LESS) {
                    temp = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = temp;
                    count();
                }
            }
        }
    }
}
