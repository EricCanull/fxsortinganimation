/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingalgoritms.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import sortingalgoritms.util.IHandler;
import sortingalgoritms.util.RandomBars;

/**
 * FXML Controller class
 *
 * @author andje22
 */
public class AnimationPane extends AnchorPane implements IHandler {
   
    private final RandomBars randomBars = new RandomBars();
    
    @FXML private GridPane barsGridPane;
    @FXML private GridPane textFieldsGridPane;
    
    private int indexPos = 0;
   
    public AnimationPane() {
        initializeController();
    }
    
    /**
     * Initializes the controller class.
     */
    private void initializeController() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AnimationPane.class.getResource("/fxml/FXMLAnimationPane.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AnimationPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeBars();
    }

    private void initializeBars() {
        barsGridPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            barsGridPane.getChildren().forEach((t) -> {
                Bar bar = (Bar) t;
                bar.setMaxY(newValue.doubleValue());
                bar.resize(getResizeHeight(bar));
             // System.out.println("value: " + ((Bar) t).getValue());
            });
        });
    }

     public void setPresetValues(String presetChoice) {
        randomBars.getRandomSet(presetChoice);
        
        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGridPane.getChildren().get(index);
            tf.setText(String.valueOf(randomBars.getBarArray()[index].getValue()));
        });
    }
    
     public void createBars() {
       barsGridPane.getChildren().removeAll(barsGridPane.getChildren());

        IntStream.range(0, 10).forEachOrdered(index -> {
             Bar bar = randomBars.getBarArray()[index];
             bar.getStyleClass().add("bar");
             bar.resize(getResizeHeight(bar));
             
             bar.setBackground(new Background(new BackgroundFill(bar.getColor(),
                     CornerRadii.EMPTY, Insets.EMPTY)));
             barsGridPane.add(bar, index, 0);
         });
     }

    // Use some maths for resizing the bars dynamically
    private double getResizeHeight(Bar bar) {
        double y1 = 0;
        double y2 = bar.getMaxY().get();
        
        double x1 = randomBars.getMax();
        double x2 = 0;
        
        // First calculate the slope
        double slope = (y1 - y2) / (x1 - x2);
        
        // Second calculate the y-Intercept
        double yIntercept = (y2 * x1 - y1 * x2) / (x1 - x2);
       
        // Last calculate the new height
        double height = y2 - (slope * bar.getValue() + yIntercept);
        
        return height;
    }
    
    public Bar[] getBarArray() {
        return randomBars.getBarArray();
    }
    
    @Override
    public Object apply(Object number) {
        if (indexPos == 10) {
            indexPos = 0;
             //System.out.println();
        }

        while (indexPos <= 9) {
            Bar bar = (Bar) number;   
            String color = Integer.toHexString(bar.getColor().hashCode());
            TextField textfield = (TextField) textFieldsGridPane.getChildren().get(indexPos);
            textfield.setText(String.valueOf(bar.getValue()));
            textfield.setStyle("-fx-border-color: #" + color + ";" 
                             + "-fx-background-color: #" + color.replace("ff", "33") + ";");
            
            Bar gridBar = (Bar) barsGridPane.getChildren().get(indexPos);
            gridBar.setStyle("-fx-background-color: #" + color + ";");
            gridBar.resize(getResizeHeight(bar));
           // System.out.print(bar.getValue() + " ");
            
            indexPos++;
            break;
        }
        return null;
    }
}
