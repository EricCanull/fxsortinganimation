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
import sortingalgoritms.util.Bar;
import sortingalgoritms.util.IHandler;
import sortingalgoritms.util.RandomBars;

/**
 * FXML Controller class
 *
 * @author andje22
 */
public class AnimationPane extends AnchorPane implements IHandler {
   
    private final RandomBars randomData = new RandomBars();
    
    @FXML private GridPane barsGridPane;
    @FXML private GridPane textFieldsGridPane;
    
    private Bar[] bars = new Bar[10];
    private int labelIndex = 0;
   
    public AnimationPane() {
        initialize();
        
        barsGridPane.heightProperty().addListener((observable) -> {
            if (barsGridPane.getChildren().isEmpty()) {
                createBars();
                } else {
                IntStream.range(0, 10).forEachOrdered(index -> {
                    Bar bar = bars[index];
                    bar.resize(getResizeHeight(bar));
                });
            }
        });
    }
    
    /**
     * Initializes the controller class.
     */
    private void initialize() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AnimationPane.class.getResource("/fxml/FXMLAnimationPane.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AnimationPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void setPresetValues(String presetChoice) {
        randomData.getRandomSet(presetChoice);
        bars = (Bar[]) randomData.getBarList().toArray();
        
        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGridPane.getChildren().get(index);
            tf.setText(String.valueOf(bars[index].getValue()));
        });
    }
    
     public void createBars() {
       barsGridPane.getChildren().removeAll(barsGridPane.getChildren());

        IntStream.range(0, 10).forEachOrdered(index -> {
             Bar bar = bars[index];
             bar.resize(getResizeHeight(bar));
             bar.setBackground(new Background(new BackgroundFill(bar.getColor(),
                     CornerRadii.EMPTY, Insets.EMPTY)));
             barsGridPane.add(bar, index, 0);
         });
     }

    public void resetBars() {
        setBarArray((Bar[]) randomData.getBarList().toArray());
        createBars();
    }

    private double getResizeHeight(Bar bar) {
        double y1 = 0;
        double y2 = bar.getMaxY().get();
        
        double x1 = randomData.getMax();
        double x2 = 0;
        
        // First calculate the slope
        double slope = (y1 - y2) / (x1 - x2);
        
        // Second calculate the y-Intercept
        double yIntercept = (y2 * x1 - y1 * x2) / (x1 - x2);
       
        // Last calculate the new height
        double height = y2 - (slope * bar.getValue() + yIntercept);
        
        return height;
    }
    
    public void setBarArray(Bar[] bars) {
        this.bars = bars;
    }

    public Bar[] getBarArray() {
        return bars;
    }

    @Override
    public Object apply(Object number) {
        Bar bar = (Bar) number;
       
        
        if (labelIndex == 10) {
            labelIndex = 0;
             //System.out.println();
        }

        while (labelIndex <= 9) {
            String color = Integer.toHexString(bar.getColor().hashCode());
            TextField textfield = (TextField) textFieldsGridPane.getChildren().get(labelIndex);
            textfield.setText(""+bar.getValue());
            textfield.setStyle("-fx-border-color: #" + color + ";");
            Bar reg = (Bar) barsGridPane.getChildren().get(labelIndex);
            reg.setStyle("-fx-background-color: #" + color + ";");
            reg.resize(getResizeHeight(bar));
            
           // System.out.print(bar.getValue() + " ");
            
            labelIndex++;
            break;
        }
        return null;
    }
}
