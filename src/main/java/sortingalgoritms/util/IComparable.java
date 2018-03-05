package sortingalgoritms.util;

/**
 * Implements the functionality of the collection lists java.lang.Comparable.
 * @author Eric Canull
 * @version 1.0
 */
public interface IComparable {

    final int LESS = -1;
    final int EQUAL = 0;
    final int GREATER = 1;

    /**
     * Similar to Comparable.compareTo
     *
     * @param number a value to compare
     * @return the result of the comparison
     */
    int compare(IComparable number);
}
