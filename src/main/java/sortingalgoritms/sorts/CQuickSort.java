/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Implementation of the quick sort algorithm
 *
 * @author Eric Canull
 */
public final class CQuickSort extends AbstractSort {

    public static final CQuickSort SINGLETON = new CQuickSort();

    /** Implementation of the quick sort algorithm */
    private CQuickSort() { } 

    /**
     * Starts the quick sort algorithm.
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
     * Partitions the array.
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

            // scan down down to find first item less in partion
            // or quit if none
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
