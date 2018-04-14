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
 * Implementation of the Shell Sort algorithm.
 *
 * @author Eric Canull
 */
public final class CShellSort extends AbstractSort {

    public static final CShellSort SINGLETON = new CShellSort();

    /** Private constructor prevents direct instances of this class */
    private CShellSort() { } 

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
