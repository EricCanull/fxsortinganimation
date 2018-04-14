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
 * Implementation of the Heap Sort algorithm.
 *
 * @author Eric Canull
 */
public final class CHeapSort extends AbstractSort {

    public static final CHeapSort SINGLETON = new CHeapSort();

    private IComparable temp[];
    private int number;
    private int left;
    private int right;
    private int largest;

    /**
     * Implementation of the Heap Sort algorithm.
     */
    private CHeapSort() { /* private constructor */
        super(SINGLETON);
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
