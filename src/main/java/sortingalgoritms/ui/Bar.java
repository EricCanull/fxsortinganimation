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
    
    private final Color DEFAULT_COLOR = Color.rgb(48, 115, 180);
    private final Color SELECTED_COLOR = Color.rgb(160, 118, 23);
    
    private int index;
    private int value;
    
    private Color color;
   
    /**
     * Creates a rectangular bar with a index and value.
     *
     * @param index
     * @param value an integer reference to a number
     */
    public Bar(int index, int value) {
        this.index = index;
        this.value = value;
        this.color = DEFAULT_COLOR;
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
        
        color = SELECTED_COLOR;

        compareValue.color = SELECTED_COLOR;

        try {
            Thread.sleep(MainController.DELAY_PROPERTY.get());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        compareValue.color = DEFAULT_COLOR;
        
        color = DEFAULT_COLOR;

        return value < compareValue.value ? IComparable.LESS // -1 
                : value > compareValue.value ? IComparable.GREATER  // 1
                        : IComparable.EQUAL;  // 0                              
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
        this.color = color;
    }

    /**
     * Gets the color of the bar
     * @return 
     */
    @Override
    public Color getColor() {
        return this.color;
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
