/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
 */
package sortingalgoritms.util;

/**
 * Implements the functionality of the collection lists java.lang.Comparable.
 *
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
