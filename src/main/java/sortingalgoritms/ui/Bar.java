package sortingalgoritms.ui;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import sortingalgoritms.MainController;
import sortingalgoritms.util.IComparable;
import sortingalgoritms.util.IPainter;

/**
 *  A rectangular bar with an index and given value.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class Bar extends Region implements IComparable, IPainter {
    
    private Color barColor; 
    
    private int index;
    private int value;
   
    /**
     * Creates a rectangular bar with a index and value.
     *
     * @param index
     * @param value an integer reference to a number
     */
    public Bar(int index, int value) {
        this.index = index;
        this.value = value;
        this.barColor = Color.rgb(48, 115, 180);

    }

    /**
     * Compares two values, changes their colors and returns -1, 0, or 1.
     *
     * @param comparable
     * @return An object with a value less than, greater than, or equals
     */
    @Override
    public int compare(IComparable comparable) {
        Bar compareValue = (Bar) comparable;
        Color mainColor = barColor;
        Color compareColor = compareValue.barColor;
        barColor = Color.rgb(160, 118, 23);

        compareValue.barColor = barColor;

        try {
            Thread.sleep(MainController.DELAY_PROPERTY.get());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        compareValue.barColor = compareColor;
        barColor = mainColor;

        return value < compareValue.value
                ? IComparable.LESS : value > compareValue.value
                        ? IComparable.GREATER : IComparable.EQUAL;
    }

    /**
     * Gets the index of the bar
     * @return 
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Sets the index of the bar
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the value of the bar.
     *
     * @return An integer value specifying the current number stored
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the bar.
     *
     * @param number a reference to the new integer value to be set
     */
    public void setValue(int number) {
        this.value = number;
    }

    /**
     * Sets the color of the bar
     * @param color 
     */
    @Override
    public void setColor(Color color) {
        this.barColor = color;
    }

    /**
     * Gets the color of the bar
     * @return 
     */
    @Override
    public Color getColor() {
        return this.barColor;
    }
    
    /**
     * Gets the value of the bar as a string
     * @return 
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
