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

import sortingalgoritms.ui.Bar;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Creates an array of ten bars with values in random, reversed or ascending
 * order based on the selected number set.
 *
 * @author Eric Canull
 */
public class RandomBars {
  
    public static final int MAX_SIZE = 10;
    
    public static Bar[] barsArray = null;
    
    /**
     * Gets the specified barsArray array based on the type.
     *
     * @param type a String representing the name of the data type 
     * @param values 
     */
    public static void setRandomSet(String type, int[] values) {
        loadArray();
       
        if (values == null) {
            switch (type) {
            case "Random"   : barsArray = randomTen();       break;
            case "Ordered"  : barsArray = inorderSet();      break;
            case "Reverse"  : barsArray = reverseSet();      break;
            case "Hundreds" : barsArray = randomHundreds();  break;
            case "Thousands": barsArray = randomThousands(); break;
            }
        } else {
            setManualSet(values);
        }
    }
    
    private static void setManualSet(int[] values) {
        barsArray = new Bar[MAX_SIZE];
        IntStream.range(0, 10).forEachOrdered(index -> {
            Bar bar = new Bar(index, values[index]);
            barsArray[index] = bar;
        });
    }

    public static void loadArray() {
        barsArray = new Bar[10];
        
        IntStream.range(0, barsArray.length).forEachOrdered(index -> {
            barsArray[index] = new Bar(index, index + 1);
        });
    }
    
     /**
     * Determines the highest value in the array.
     * 
     * @return Max value in the array
     */
    public static int getMaxValue() {
        int max = 0;
        for (Bar value : barsArray) {
            max = value.getValue() > max ? value.getValue() : max;
        }
        return max;
    }

    /**
     * Randomly distributes integers (1-10); no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] randomTen() {
        for (int index = 0; index < barsArray.length - 1; index++) {
            int randomIndex = (int) (Math.random() * (barsArray.length - index)) + index;
            int tempArray = barsArray[index].getValue();
            barsArray[index].setValue(barsArray[randomIndex].getValue());
            barsArray[randomIndex].setValue(tempArray);
        }
        return barsArray;
    }
    
    /**
     * Returns an array with barsArray (1-10) in ascending order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] inorderSet() {
        return barsArray;
    }
    
    /**
     * Returns an array with barsArray (1-10) in reverse order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] reverseSet() {
        int lastIndex = barsArray.length;
        for (Bar value : barsArray) {
            value.setValue(lastIndex);
            lastIndex--;
        }
        return barsArray;
    }

    /**
     * Returns an array with random barsArray between (1 - 10,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] randomHundreds() {
        for (Bar value : barsArray) {
            int randomInt = new Random().nextInt(1000) + 100;
            value.setValue(randomInt);
        }
        return barsArray;
    }

    /**
     * Returns an array with random barsArray between (1 - 999,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] randomThousands() {
        for (Bar value : barsArray) {
            int randomInt = new Random().nextInt(999000) + 1;
            value.setValue(randomInt);
        }
        return barsArray;
    }

    /**
     * The values in the array as a string.
     * 
     * @return A String of integer values
     */
    public static String getString() {
        return Arrays.asList(barsArray).toString()
                .replace("[", "")
                .replace("]", "");
    }
}
