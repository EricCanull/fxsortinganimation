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
 * Implementation of the Bubble Sort algorithm.
 * @author Eric Canull
 * @version 1.0
 */
public final class CBubbleSorter extends ASorter {
   
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
