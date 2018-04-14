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
 * Implementation of the Exchange sort algorithm.
 *
 * @author Eric Canull
 */
public final class CExchangeSort extends AbstractSort {

    public static final CExchangeSort SINGLETON = new CExchangeSort();

    /**
     * Implementation of the Exchange sort algorithm.
     */
    private CExchangeSort() { }/* private constructor */

    /**
     * Starts the Exchange Sort algorithm.
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
        IComparable temp;
        for (int i = 0; i < numbers.length - 1; i++) {
            count();
            for (int j = i + 1; j < numbers.length; j++) {
                count();
                if (numbers[i].compare(numbers[j]) == IComparable.GREATER) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    count();
                }
            }
        }
    }
}
