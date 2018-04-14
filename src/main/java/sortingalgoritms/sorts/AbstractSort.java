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
import sortingalgoritms.util.Logger;

/**
 * An abstract base for the concrete sorting classes
 *
 * @author Eric Canull
 */
public abstract class AbstractSort {
    
    private AbstractSort sorter = null;
    
    public AbstractSort(AbstractSort sorter) {
        this.sorter = sorter;
    }
    
    /**
     * Sets up the sorting data and then invokes the abstract method to start
     * sorting.
     *
     * @param numbers the array of numbers to be sorted.
     * @param lowIndex the low index of the array.
     * @param highIndex the high index of array.
     */
    public final void sort(IComparable[] numbers, int lowIndex, int highIndex) {
        startSort(numbers, lowIndex, highIndex);
    }

    /**
     * The abstract method used by all of the concrete sorting classes
     *
     * @param numbers the array of numbers to be sorted.
     * @param lowIndex the low index of the array.
     * @param highIndex the high index of array.
     */
    protected abstract void startSort(IComparable[] numbers, int lowIndex, int highIndex);

    /**
     * Resets the sort count.
     */
    protected void resetCount() {
        Logger.resetCount();
    }

    /**
     * Increments iteration count.
     */
    protected void count() {
        Logger.count();
    }
 }
