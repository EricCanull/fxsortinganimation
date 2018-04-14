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
 * Implementation of the Pancake Sort algorithm.
 *
 * @author Eric Canull
 */
public final class CPancakeSort extends AbstractSort {

    public static final CPancakeSort SINGLETON = new CPancakeSort();

    /**
     * Implementation of the Pancake Sort algorithm.
     */
    private CPancakeSort() { /* private constructor */
        super(SINGLETON);
    }

    /**
     * Starts the Pancake Sort algorithm.
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
        IComparable max;

        for (int index = 0; index < numbers.length; index++) {
            max = numbers[0];
            lowIndex = 0;
            count();
            for (int j = 0; j < numbers.length - index; j++) {
                count();
                if (numbers[j].compare(max) == IComparable.GREATER) {
                    max = numbers[j];
                    lowIndex = j;
                    count();
                }
            }

            flip(numbers, lowIndex, numbers.length - 1 - index);
            count();
        }
    }

    /**
     * Flips the array like a pancake.
     *
     * @param numbers an array of numbers used for the sorting
     * @param left a integer representing the left index position in the array
     * @param right a integer representing the right index position in the array
     */
    public void flip(IComparable[] numbers, int left, int right) {

        while (left <= right) {
            IComparable temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
            count();
        }
    }
}
