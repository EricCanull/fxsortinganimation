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
 * Implementation of the Selection Sort algorithm.
 *
 * @author Eric Canull
 */
public final class CSelectionSorter extends ASortOperator {

    public static final CSelectionSorter SINGLETON = new CSelectionSorter();

    /**
     * Implementation of the Selection Sort algorithm.
     */
    private CSelectionSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Starts of the Selection Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    public void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        IComparable minValue = numbers[highIndex];

        int index, minIndex;
        for (lowIndex = 0; lowIndex < numbers.length; lowIndex++) {
            minValue = numbers[lowIndex];
            minIndex = lowIndex;
            count();
            for (index = lowIndex; index < numbers.length; index++) {
                if (numbers[index].compare(minValue) == IComparable.LESS) {
                    minValue = numbers[index];
                    minIndex = index;
                    count();

                }
                count();
            }

            if (minValue.compare(numbers[lowIndex]) == IComparable.LESS) {
                minValue = numbers[lowIndex];
                numbers[lowIndex] = numbers[minIndex];
                numbers[minIndex] = minValue;
                count();
            }
        }
    }
}
