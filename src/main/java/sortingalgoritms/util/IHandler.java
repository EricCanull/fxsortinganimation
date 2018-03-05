package sortingalgoritms.util;

/**
 * Represents the abstract lambda expression whose sole purpose in life is to
 * evaluate itself on an input and return the result of the evaluation.
 * @author Eric Canull
 * @version 1.0
 */
public interface IHandler {
    
    /**
     * @param arg input object for the lambda expression.
     * @return an output object resulting from evaluating the lambda expression on the input arg.
     */
    public Object apply(Object arg);
}

