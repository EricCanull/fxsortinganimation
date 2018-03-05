package sortingalgoritms.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates an array of values that are in random order, ascending, or reverse
 order.
 *
 * @author Eric Canull
 * @version 1.0
 *
 */
public class RandomBars {
        
    private Bar[] values = new Bar[10];
    
    private List<Bar> barList;
       
    /**
     * Gets the specified values array based on the type.
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
    
    public Bar[] getValues() {
        return values;
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
        values = new Bar[10];

        int limit = values.length;

        for (int i = 0; i < limit; i++) {
            values[i] = new Bar(i, i + 1);
        }

        for (int i = 0; i < limit - 1; i++) {
            int randomIndex = (int) (Math.random() * (limit - i)) + i;
            int tempArray = values[i].getValue();
            values[i].setValue(values[randomIndex].getValue());
            values[randomIndex].setValue(tempArray);
        }
        setBarlist(values);
        return values;
    }

    
    /**
     * Returns an array with values (1-10) in ascending order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] inorderSet() {
        values = new Bar[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = new Bar(i, i);
        }

        for (int index = 0; index < values.length; index++) {
            values[index].setValue(index + 1);
        }
        setBarlist(values);
        return values;
    }
    
    /**
     * Returns an array with values (1-10) in reverse order; no duplicates
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] reverseSet() {
       values = new Bar[10];
       for (int i = 0; i < values.length; i++) {
            values[i] = new Bar(i, i);
        }
        
        int lastIndex = values.length;
        for (Bar value : values) {
            value.setValue(lastIndex);
            lastIndex--;
        }
        
        setBarlist(values);
        return values;
    }
    
    /**
     * Returns an array with random values between (1 - 10,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] randomHundreds() {
        values = new Bar[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = new Bar(i, i);
        }
        for (Bar value : values) {
            
            int randomInt = new Random().nextInt(1000) + 100;
            value.setValue(randomInt);
        }
         setBarlist(values);
         return values;
    }
    
    /**
     * Returns an array with random values between (1 - 999,000).
     *
     * @return An array of integers arranged in a specified order
     */
    private Bar[] randomThousands() {
        values = new Bar[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = new Bar(i, i);
        }

        for (Bar value : values) {
            int randomInt = new Random().nextInt(999000) + 1;
            value.setValue(randomInt);
        }
        setBarlist(values);
        return values;
    }
    
    public String getString() {
        return getBarList().toString()
                .replace("[", "")
                .replace("]", "")
                .replaceAll(",", "\r") + "\n\n";
    }
}
