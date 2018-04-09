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
 * Implementation of the Bubble Sort algorithm.
 * @author Eric Canull
 * @version 1.0
 */
public final class CBubbleSorter extends ASortOperator {
    public static final CBubbleSorter SINGLETON = new CBubbleSorter();
    
    /**
     * Implementation of the Bubble Sort algorithm.
     */
    private CBubbleSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }
    
    /**
     * Starts the Bubble Sort algorithm.
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the array
     * @param highIndex a integer representing the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        
        // Marker for the final swap's position
        int lastSwap = numbers.length - 1;
        
        // Start at position index 1 and iterate through the length of the array
        for (int i = 1; i < numbers.length; i++) {
            boolean isSorted = true; // Condition to stop the iteration
            int currentSwap = -1; // Keep track of the swaps
            count();
            
            // Starting at position index 0 and iterate until the last swap position
            for (int startIndex = 0; startIndex < lastSwap; startIndex++) {
                count();
                // If the start index is greater than the index it proceeds then swap the values
                if (numbers[startIndex].compare(numbers[startIndex + 1]) == IComparable.GREATER) {
                    IComparable temp = numbers[startIndex];
                    numbers[startIndex] = numbers[startIndex + 1];
                    numbers[startIndex + 1] = temp;
                    isSorted = false;
                    currentSwap = startIndex;
                    count();
                }
            }
            
            // Exits the loop if the sorting is complete
            if (isSorted)
                return;
            
            // Decrements lastSwap position until sorting has completed
            lastSwap = currentSwap; // -1
            count();
        }
    }
}
