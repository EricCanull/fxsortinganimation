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
public class GraphicsController extends AnchorPane implements IHandler {
    
    @FXML private GridPane barsGridPane;
    @FXML private GridPane textFieldsGrid;
   
    private int indexPos;
   
    public GraphicsController() {
        initialize();
    }
    
    /**
     * Initializes the controller class.
     */
    private void initialize() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicsController.class.getResource("/fxml/FXMLAnimationPane.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GraphicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        widthProperty().addListener(evt -> addGridBars());
        heightProperty().addListener(evt -> addGridBars());
    }
    
    public void setPresetValues(String presetChoice) {
        RandomBars.setRandomSet(presetChoice, null);
     
        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGrid.getChildren().get(index);
            tf.setText(String.valueOf(RandomBars.barsArray[index].getValue()));
        });
        
        addGridBars();
    }
     public void addGridBars() {

         if (Double.isNaN(this.getWidth())
                 || Double.isNaN(this.getHeight())
                 || RandomBars.barsArray[0] == null) {
             return;
         }

        barsGridPane.getChildren().removeAll(barsGridPane.getChildren());

        IntStream.range(0, 10).forEachOrdered(index -> {
            Bar bar = RandomBars.barsArray[index];
            bar.getStyleClass().add("bar");
            final double height = calculateHeight(bar);
            bar.setMaxHeight(height);
            bar.setPrefHeight(height);

            bar.setBackground(new Background(new BackgroundFill(bar.getColor(),
                    CornerRadii.EMPTY, Insets.EMPTY)));
            barsGridPane.add(bar, index, 0);
        });
    }
     
    // Use some maths for resizing the bars dynamically
    private double calculateHeight(Bar bar) {
        double y1 = 0;
        double y2 = this.getHeight();

        double x1 = RandomBars.getMax();
        double x2 = 0;

        // 1st calculate the slope 
        double slope = (y1 - y2) / (x1 - x2);

        // 2nd calculate the y-Intercept
        double yIntercept = (y2 * x1 - y1 * x2) / (x1 - x2);

        // 3rd calculate the new height
        double height = y2 - (slope * bar.getValue() + yIntercept);

        return height;
    }

    @Override
    public Object apply(Object number) {
        if (indexPos == 10) {
            indexPos = 0;
        }

        while (indexPos <= 9) {
            Bar bar = (Bar) number;   
            String color = Integer.toHexString(bar.getColor().hashCode());
            TextField textfield = (TextField) textFieldsGrid.getChildren().get(indexPos);
            textfield.setText(String.valueOf(bar.getValue()));
            textfield.setStyle("-fx-border-color: #" + color + ";" 
                             + "-fx-background-color: #" + color.replace("ff", "33") + ";");
          
            Bar gridBar = (Bar) barsGridPane.getChildren().get(indexPos);           
            gridBar.setStyle("-fx-background-color: #" + color + ";");
           
            double height = calculateHeight(bar);
            gridBar.setMaxHeight(height);
            gridBar.setPrefHeight(height);

            indexPos++;
            break;
        }
        return null;
    }
}
