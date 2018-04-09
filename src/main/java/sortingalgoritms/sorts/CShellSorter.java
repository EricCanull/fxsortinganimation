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
 * Implementation of the Shell Sort algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CShellSorter extends ASortOperator {

    public static final CShellSorter SINGLETON = new CShellSorter();

    /**
     * Implementation of the Shell Sort algorithm.
     */
    private CShellSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Starts the Shell Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        int inner, outer;
        IComparable temp;

        highIndex = 1;
        while (highIndex <= numbers.length / 3) {
            highIndex = highIndex * 3 + 1;
        }

        while (highIndex > 0) {
            for (outer = highIndex; outer < numbers.length; outer++) {
                temp = numbers[outer];
                inner = outer;

                while (inner > highIndex - 1 && numbers[inner - highIndex].compare(temp) >= IComparable.GREATER) {
                    numbers[inner] = numbers[inner - highIndex];
                    inner -= highIndex;

                    // count outer loop swaps
                    count();
                }

                numbers[inner] = temp;

            }
            // count outer loop swaps
            count();
            highIndex = (highIndex - 1) / 3;
        }
    }

//    protected void startAlgorithm(IComparable[] numbers, int lowIndex, int highIndex) {
//       // alternative for analysis manages to have the best time 
//	
//        int hi;
//        int lo = 0;
//        int leftSide = numbers.length - 1;
//        for (hi = 1; hi <= (leftSide - lo) / 9; hi = 3 * hi + 1);
//        for (; hi > 0; hi /= 3) {
//            for (int i = lo + hi; i <= leftSide; i++) {
//                int j = i;
//                IComparable x = numbers[i];
//                while ((j >= lo + hi) && (x.compare(numbers[j - hi]) == IComparable.LESS)) {
//                    numbers[j] = numbers[j - hi];
//                    j -= hi;
//
//                    // count every inner loop swap
//                    count();
//                }
//                numbers[j] = x;
//
//                // count outer loop swaps
//                count();
//            }
//        }
//    }
}
