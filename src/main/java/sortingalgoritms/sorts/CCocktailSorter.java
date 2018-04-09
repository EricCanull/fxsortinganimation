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
 * Implementation of the Cocktail or Shaker sorting algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CCocktailSorter extends ASortOperator {

    public static final CCocktailSorter SINGLETON = new CCocktailSorter();

    /**
     * Implementation of the Cocktail or Shaker sorting algorithm.
     */
    private CCocktailSorter() {
        // Private Constructor will prevent the instantiation of this class
        // directly
    }

    /**
     * Starts the Cocktail Sorting algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the array
     * @param highIndex a integer representing the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i <= numbers.length - 2; i++) {
                count();
                if (numbers[i].compare(numbers[i + 1]) == IComparable.GREATER) {
                    // test whether the two elements are in the wrong order
                    IComparable temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                    count();
                }
            }

            if (!swapped) {
                //  exit the outer loop here if no swaps occurred.
                break;
            }
            swapped = false;
            for (int i = numbers.length - 2; i >= 0; i--) {
                count();
                if (numbers[i].compare(numbers[i + 1]) == IComparable.GREATER) {
                    IComparable temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                    count();
                }
            }
            // If no elements have been swapped, then the list is sorted
        } while (swapped);
    }
}
