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
public class BaseSortOperator extends ASortOperator {

    private final ASortOperator sortOperator;

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
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        sortOperator.startSort(numbers, lowIndex, highIndex); // delegates to decoree

    }
}
