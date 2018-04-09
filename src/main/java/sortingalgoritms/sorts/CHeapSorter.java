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
 * Implementation of the Heap Sort algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CHeapSorter extends ASortOperator {

    public static final CHeapSorter SINGLETON = new CHeapSorter();

    private IComparable temp[];
    private int number;
    private int left;
    private int right;
    private int largest;

    /**
     * Implementation of the Heap Sort algorithm.
     */
    private CHeapSorter() {
        // Private Constructor will prevent the instantiation of this class
        // directly
    }

    /**
     * Starts the Heap Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex lowest index position in the array
     * @param highIndex highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        temp = numbers;
        buildheap(temp);
        for (int i = number; i > 0; i--) {
            count();
            exchange(0, i);
            number = number - 1;
            maxheap(temp, 0);
        }
    }

    /**
     * @param numbers an array of numbers used for the sorting
     */
    private void buildheap(IComparable[] numbers) {
        number = numbers.length - 1;
        for (int i = number / 2; i >= 0; i--) {
            maxheap(numbers, i);
        }
    }

    /**
     *
     * @param numbers an array of numbers used for the sorting
     * @param index the lowest index position in the array
     */
    private void maxheap(IComparable[] numbers, int index) {
        left = 2 * index;
        right = 2 * index + 1;

        if (left <= number && numbers[left].compare(numbers[index]) == IComparable.GREATER) {
            largest = left;
            count();
        } else {
            largest = index;
            count();
        }

        if (right <= number && numbers[right].compare(numbers[largest]) == IComparable.GREATER) {
            largest = right;
        }

        if (largest != index) {
            exchange(index, largest);
            maxheap(numbers, largest);
            count();
        }
    }

    /**
     * Exchange the numbers in the arrays
     *
     * @param i a integer representing the lowest index position in the array
     * @param j a integer representing the lowest index position in the array
     */
    private void exchange(int i, int j) {
        IComparable arrayExchange = temp[i];
        temp[i] = temp[j];
        temp[j] = arrayExchange;
        count();
    }
}
