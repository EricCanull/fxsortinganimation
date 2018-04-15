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
package sortingalgoritms.util;

import sortingalgoritms.ui.CompareValue;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Creates an array of ten bars with values in random, reversed or ascending
 * order based on the selected number set.
 *
 * @author Eric Canull
 */
public class RandomValues {
  
    public static final int MAX_SIZE = 10; // max array indices
    
    private static CompareValue[] array = null;
    
    /**
     * Gets the array.
     * @return an array of values to be sorted
     */
    public static CompareValue[] getArray() {
        return array;
    }
    
    private static int maxValue; // array max value
    
    /**
     * Gets the max value in the array.
     * @return an array of values to be sorted
     */
    public static int getMaxValue() {
        return maxValue;
    }
    
    /**
     * Sets the values in the array
     * @param type a String representing the name of the data type
     * @param values
     */
    public static void setRandomSet(String type, int[] values) {
        resetArray();
       
        if (values == null) {
            switch (type) {
            case "Random"   : array = randomValues();    break;
            case "Ordered"  : array = inorderValues();   break;
            case "Reverse"  : array = reverseValues();   break;
            case "Hundreds" : array = randomHundreds();  break;
            case "Thousands": array = randomThousands(); break;
            }
        } else {
            setManualSet(values);
        }
        
        setMaxValue();
    }
    
    /** Sets the array with values manually entered by the user. */
    private static void setManualSet(int[] values) {
        array = new CompareValue[MAX_SIZE];
        IntStream.range(0, array.length).forEachOrdered(index -> {
            CompareValue bar = new CompareValue(values[index]);
            array[index] = bar;
        });
    }

    /** Resets the array. */
    public static void resetArray() {
        array = new CompareValue[MAX_SIZE];
        IntStream.range(0, array.length).forEachOrdered(index -> {
            array[index] = new CompareValue(index + 1);
        });
    }

     /** Determines the highest value in the array. */
    public static void setMaxValue() {
        int max = 0;
        for (CompareValue value : array) {
            max = value.getValue() > max ? value.getValue() : max;
        }
       RandomValues.maxValue = max;
    }

    /**
     * Randomly distributes values 1-10 without duplicates
     * @return an array of random values
     */
    private static CompareValue[] randomValues() {
        for (int index = 0; index < array.length - 1; index++) {
            int randomIndex = (int) (Math.random() * (array.length - index)) + index;
            int tempArray = array[index].getValue();
            array[index].setValue(array[randomIndex].getValue());
            array[randomIndex].setValue(tempArray);
        }
        return array;
    }
    
    /**
     * Gets the array with values 1-10 in ascending order without duplicates
     * @return An array of values in-order
     */
    private static CompareValue[] inorderValues() {
        return array;
    }
    
    /**
     * Gets an array with values 1-10 in reverse order without duplicates
     * @return An array of values in reverse order
     */
    private static CompareValue[] reverseValues() {
        int lastIndex = array.length;
        for (CompareValue value : array) {
            value.setValue(lastIndex);
            lastIndex--;
        }
        return array;
    }

    /**
     * Gets an array with random values between 1-10,000.
     * @return an array of random values
     */
    private static CompareValue[] randomHundreds() {
        for (CompareValue value : array) {
            int randomInt = new Random().nextInt(1000) + 100;
            value.setValue(randomInt);
        }
        return array;
    }

    /**
     * Gets an array with random array between 1-999,000.
     * @return an array of random values
     */
    private static CompareValue[] randomThousands() {
        for (CompareValue value : array) {
            int randomInt = new Random().nextInt(999000) + 1;
            value.setValue(randomInt);
        }
        return array;
    }

    /**
     * The values in the array as a string.
     * @return a String of the values stored in the array
     */
    public static String getString() {
        return Arrays.asList(array).toString()
                .replace("[", "")
                .replace("]", "");
    }
}
