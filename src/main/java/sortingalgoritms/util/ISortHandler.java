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
 * Represents the abstract lambda expression whose sole purpose in life is to
 * evaluate itself on an input and return the result of the evaluation.
 *
 * @author Eric Canull
 * @version 1.0
 */
public interface ISortHandler {

    /**
     * @param arg input object for the lambda expression.
     * @return an output object resulting from evaluating the lambda expression
     * on the input arg.
     */
    public Object apply(Object arg);
}
