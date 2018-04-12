/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
 */
package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Passes the selected sort operation to the abstract base sorting algorithm 
 * class which in-turn invokes the requested concrete
 * sort class. 
 * @author Eric Canull
 * @version 1.0
 *
 */
public class BaseSortOperator extends ASortOperator{
    
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
