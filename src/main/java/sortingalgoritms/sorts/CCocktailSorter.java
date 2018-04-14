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
 * Implementation of the Cocktail or Shaker sorting algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CCocktailSorter extends ASorter {

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
