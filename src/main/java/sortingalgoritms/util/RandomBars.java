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

import sortingalgoritms.ui.Bar;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Creates an array of ten bars with values in random, reversed or ascending
 * order based on the requested type.
 *
 * @author Eric Canull
 * @version 1.0
 *
 */
public class RandomBars {
        
    public static Bar[] barsArray = new Bar[10];
    
    /**
     * Gets the specified barsArray array based on the type.
     *
     * @param type a String representing the name of the data type 
     * @param values 
     */
    public static void setRandomSet(String type, int[] values) {
       
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
        barsArray = new Bar[10];
        IntStream.range(0, 10).forEachOrdered(index -> {
            Bar bar = new Bar(index, values[index]);
            barsArray[index] = bar;
        });
    }

    /**
     * Determines the highest value in the array.
     * @return max highest value in the array of entered numbers
     */
    public static int getMax() {
       int max = barsArray[0].getValue();

        for (int i = 1; i < barsArray.length; i++) {
            if (barsArray[i].getValue() > max) {
                max = barsArray[i].getValue();
            }
        }

        return max;
    }
    
    /**
     * Randomly distributes integers 1-10 without duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] randomTen() {
        barsArray = new Bar[10];

        int limit = barsArray.length;

        for (int i = 0; i < limit; i++) {
            barsArray[i] = new Bar(i, i + 1);
        }

        for (int i = 0; i < limit - 1; i++) {
            int randomIndex = (int) (Math.random() * (limit - i)) + i;
            int tempArray = barsArray[i].getValue();
            barsArray[i].setValue(barsArray[randomIndex].getValue());
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
        barsArray = new Bar[10];
        
        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }

        for (int index = 0; index < barsArray.length; index++) {
            barsArray[index].setValue(index + 1);
        }
        return barsArray;
    }
    
    /**
     * Returns an array with barsArray (1-10) in reverse order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private static Bar[] reverseSet() {
        barsArray = new Bar[10];

        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }

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
        barsArray = new Bar[10];

        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }
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
        barsArray = new Bar[10];

        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }

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
