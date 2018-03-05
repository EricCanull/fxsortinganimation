package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Implementation of Quick Sort Algorithm
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CQuickSorter extends ASortOperator {

    public static final CQuickSorter SINGLETON = new CQuickSorter();

    /**
     * Implementation of Quick Sort Algorithm
     */
    private CQuickSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Starts the Quick Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the
     * array
     * @param highIndex a integer representing the highest index position in the
     * array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        qsort(numbers, 0, numbers.length - 1);
    }

    /**
     * Recursive method to partition the array.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the
     * array
     * @param highIndex a integer representing the highest index position in the
     * array
     */
    private void qsort(IComparable[] numbers, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return;
        }
        count();
        int index = partition(numbers, lowIndex, highIndex);
        qsort(numbers, lowIndex, index - 1);
        qsort(numbers, index + 1, highIndex);
    }

    /**
     * Recursive method to partition the array.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the
     * array
     * @param highIndex a integer representing the highest index position in the
     * array
     */
    private int partition(IComparable[] numbers, int lowIndex, int highIndex) {
        IComparable tmp;
        int low = lowIndex - 1;
        int high = highIndex;
        IComparable pivot = numbers[highIndex]; // partition point
        while (true) {

            // scan up to find first item greater than partition
            // won't go past end because partition = last item in array
            while (numbers[++low].compare(pivot) == IComparable.LESS) {
                count();
            }

            // scan down down to find first item less than v
            // or quit if there are none
            while (pivot.compare(numbers[--high]) == IComparable.LESS) {
                count();
                if (high == lowIndex) {
                    break;
                }
            }
            // if scan points cross, quit
            if (low >= high) {
                break;
            }
            count();
            // exchange the elements
            tmp = numbers[low];
            numbers[low] = numbers[high];
            numbers[high] = tmp;

        }

        // final swap
        numbers[highIndex] = numbers[low];
        numbers[low] = pivot;

        count();
        return low;
    }
}
