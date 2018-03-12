package sortingalgoritms.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import sortingalgoritms.MainController;
import sortingalgoritms.util.IComparable;
import sortingalgoritms.util.IPainter;

/**
* Ordered and Colored Integers.
* @author Eric Canull
* @version 1.0
*/
public final class Bar extends Region implements IComparable, IPainter {
       
    private int index;
    private int value; 
   
    private Color color = Color.rgb(48, 115, 180);
           
    // The maximum height
    private final SimpleDoubleProperty maxY = new SimpleDoubleProperty(240);
   
    public SimpleDoubleProperty getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
         this.maxY.set(maxY);
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
       
    /**
     * Creates a UtilColorInteger instance with the given values.
     * @param index
     * @param value an integer reference to a number 
     */
    public Bar(int index, int value) {
        this.index = index;
        this.value = value;
        setBackground(new Background(new BackgroundFill(color,
                    CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Compares two values, changes their colors and returns -1, 0, or 1.
     * @param comparable
     * @return An object with a value less than, greater than, or equals
     */
    @Override
    public int compare(IComparable comparable) {
        Bar compareValue = (Bar) comparable;
        Color mainColor = color;
        Color compareColor = compareValue.color;
        color = Color.rgb(160, 118, 23);

        compareValue.color = color;

        try {
            Thread.sleep(MainController.DELAY_PROPERTY.get());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        compareValue.color = compareColor;
        color = mainColor;

        return value < compareValue.value
                ? IComparable.LESS : value > compareValue.value
                ? IComparable.GREATER : IComparable.EQUAL;
    }
    
    
    public int getIndex() {
        return this.index;
    }
    
     /**
     * Returns the value of this integer.
     * @return An integer value specifying the current number stored
     */
    public int getValue() {
        return value;
    }

    public void resize(double height) {
       setPrefHeight(height);
       setMaxHeight(height);
    }

    /**
     * Changes the value of this number.
     * @param number a reference to the new integer value to be set
     */
    public void setValue(int number) {
    	this.value = number;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
       return this.color;
    }
}

