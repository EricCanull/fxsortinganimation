package sortingalgoritms.util;

import sortingalgoritms.ui.Bar;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates an array of barsArray that are in random order, ascending, or reverse
 order.
 *
 * @author Eric Canull
 * @version 1.0
 *
 */
public class RandomBars {
        
    private Bar[] barsArray = new Bar[10];
    
    private List<Bar> barList;
       
    /**
     * Gets the specified barsArray array based on the type.
     *
     * @param type a String representing the name of the data type
     * @return 
     */
    public Bar[] getRandomSet(String type) {
        switch (type) {
            case "Random":
                return randomTen();
            case "Ordered":
                return inorderSet();
            case "Reverse":
                return reverseSet();
            case "Hundreds":
                return randomHundreds();
            case "Thousands":
                return randomThousands();
        }
        return null;
    }
    
    public Bar[] getBarArray() {
        return barsArray;
    }
     
    //final int max = barList.stream().reduce(0, Math::max);
    public List<Bar> getBarList() {
        return this.barList;
    }

    private List<Bar> setBarlist(Bar[] bars) {
        barList = Collections.unmodifiableList(Arrays.asList(bars));
        return barList;
    }
    
    /**
     * Determines the highest value in the array.
     * @return max highest value in the array of entered numbers
     */
    public int getMax() {
        int max = barList.get(0).getValue();

        for (int i = 1; i < barList.size(); i++) {
            if (barList.get(i).getValue() > max) {
                max = barList.get(i).getValue();
            }
        }

        return max;
    }
    
    /**
     * Randomly distributes integers 1-10 without duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] randomTen() {
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
        setBarlist(barsArray);
        return barsArray;
    }

    
    /**
     * Returns an array with barsArray (1-10) in ascending order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] inorderSet() {
        barsArray = new Bar[10];
        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }

        for (int index = 0; index < barsArray.length; index++) {
            barsArray[index].setValue(index + 1);
        }
        setBarlist(barsArray);
        return barsArray;
    }
    
    /**
     * Returns an array with barsArray (1-10) in reverse order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] reverseSet() {
       barsArray = new Bar[10];
       for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }
        
        int lastIndex = barsArray.length;
        for (Bar value : barsArray) {
            value.setValue(lastIndex);
            lastIndex--;
        }
        
        setBarlist(barsArray);
        return barsArray;
    }
    
    /**
     * Returns an array with random barsArray between (1 - 10,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] randomHundreds() {
        barsArray = new Bar[10];
        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }
        for (Bar value : barsArray) {
            
            int randomInt = new Random().nextInt(1000) + 100;
            value.setValue(randomInt);
        }
         setBarlist(barsArray);
         return barsArray;
    }
    
    /**
     * Returns an array with random barsArray between (1 - 999,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] randomThousands() {
        barsArray = new Bar[10];
        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = new Bar(i, i);
        }

        for (Bar value : barsArray) {
            int randomInt = new Random().nextInt(999000) + 1;
            value.setValue(randomInt);
        }
        setBarlist(barsArray);
        return barsArray;
    }
    
    public String getString() {
        return getBarList().toString()
                .replace("[", "")
                .replace("]", "")
                .replaceAll(",", "\r") + "\n\n";
    }
}
