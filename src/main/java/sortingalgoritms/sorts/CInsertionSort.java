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
 * Implementation of the Insertion Sorting algorithm.
 *
 * @author Eric Canull
 */
public final class CInsertionSort extends AbstractSort {

    public static final CInsertionSort SINGLETON = new CInsertionSort();

    /**
     * Implementation of the Insertion Sorting algorithm.
     */
    private CInsertionSort() { /* private constructor */
        super(SINGLETON);
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
