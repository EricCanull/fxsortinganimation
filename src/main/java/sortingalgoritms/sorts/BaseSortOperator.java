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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Passes the selected sort operation to the abstract base sorting algorithm
 * class which in-turn invokes the requested concrete sort class.
 *
 * @author Eric Canull
 */
public class BaseSortOperator extends ASortOperator {

    public final ASortOperator sortOperator;

    /**
     * Passes the selected sort operation to the abstract base sorting algorithm
     * class which in-turn invokes the requested concrete sorting classes.
     *
     * @param sortOperator
     */
    public BaseSortOperator(ASortOperator sortOperator) {
        this.sortOperator = sortOperator;
    }

    /**
     * The abstract method used by all of the concrete sorting classes
     *
     * @param numbers the array of numbers to be sorted.
     * @param lowIndex the low index of the array.
     * @param highIndex the high index of array.
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        sortOperator.startSort(numbers, lowIndex, highIndex); // delegates to decoree
    }
}
