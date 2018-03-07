package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Implementation of the Merge Sort algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CMergeSorter extends ASortOperator {

    public static final CMergeSorter SINGLETON = new CMergeSorter();

    /**
     * Merges then copies back to numbers list.
     */
    private CMergeSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Starts the Merge Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        mergeSort(numbers);
    }

    /**
     * Create a temporary array to store the divisions.
     *
     * @param numbers an array of numbers used for the sorting
     */
    private void mergeSort(IComparable[] numbers) {
        IComparable[] temp = new IComparable[numbers.length];
        mergeSort(numbers, temp, 0, numbers.length - 1);
    }

    /**
     * Recursively divides the arrays in half and merges them.
     *
     * @param numbers an array of numbers used for the sorting
     * @param temp an array of numbers used for the sorting
     * @param left a integer representing the left index position in the array
     * @param right a integer representing the right index position in the array
     */
    private void mergeSort(IComparable[] numbers, IComparable[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(numbers, temp, left, center);
            mergeSort(numbers, temp, center + 1, right);
            merge(numbers, temp, left, center + 1, right);
            count();
        }
    }

    /**
     * Sorts the divided arrays and copies the temporary array back into the
     * original array.
     *
     * @param numbers an array of numbers used for the sorting
     * @param temp an array of numbers used for the sorting
     * @param left a integer representing the left index position in the array
     * @param right a integer representing the right index position in the array
     * @param rightEnd a integer representing the rightEnd position in the array
     */
    private void merge(IComparable[] numbers, IComparable[] temp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int index = left;
        int num = rightEnd - left + 1;
        count();

        while (left <= leftEnd && right <= rightEnd) {

            if (numbers[left].compare(numbers[right]) == IComparable.LESS) {
                temp[index++] = numbers[left++];
                count();
            } else {
                temp[index++] = numbers[right++];
                count();
            }
        }

        // Copy rest of first half
        while (left <= leftEnd) {
            temp[index++] = numbers[left++];
            count();
        }

        // Copy rest of right half
        while (right <= rightEnd) {
            temp[index++] = numbers[right++];
            count();
        }

        // Copy the temp array back into the original array
        for (int i = 0; i < num; i++, rightEnd--) {
            numbers[rightEnd] = temp[rightEnd];
        }
        count();
    }
}
