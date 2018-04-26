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
package sortingalgoritms.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sortingalgoritms.MainController;

import sortingalgoritms.util.CompareValue;
import sortingalgoritms.util.RandomValues;
import sortingalgoritms.util.ISortOperator;

/**
 * FXML Controller class
 *
 * @author Eric Canull
 */
public class AnimationController extends AnchorPane implements ISortOperator {
    
    @FXML private GridPane barsGrid;
    @FXML private GridPane textFieldsGrid;
      
    public AnimationController() {
        initialize();
    }
    
    /**
     * Initializes the controller class.
     */
    private void initialize() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AnimationController.class.getResource("/fxml/FXMLAnimationPane.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AnimationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        barsGrid.widthProperty().addListener(evt -> addGridBars());
        barsGrid.heightProperty().addListener(evt -> addGridBars());
    }

    public void setPresetValues(String presetChoice) {
        RandomValues.setRandomSet(presetChoice, null);
     
        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGrid.getChildren().get(index);
            tf.setText(String.valueOf(RandomValues.getArray()[index].getValue()));
        });
        
        addGridBars();
    }

    public void addGridBars() {

        if (RandomValues.getArray() == null
                || Double.isNaN(barsGrid.getWidth())
                || Double.isNaN(barsGrid.getHeight())) {
            return;
        }
        if (barsGrid.getChildren().isEmpty()) {
            IntStream.range(0, 10).forEachOrdered((int index) -> {
                CompareValue compareValue = RandomValues.getArray()[index];

                double height = calculateHeight(compareValue.getValue());

                Bar rect = new Bar();
                rect.getStyleClass().add("rect");
                rect.setPrefHeight(height);
                rect.setMaxHeight(height);
                barsGrid.add(rect, index, 0);
            });
        } else {
            IntStream.range(0, 10).forEachOrdered((int index) -> {
                CompareValue compareValue = RandomValues.getArray()[index];
                double height = calculateHeight(compareValue.getValue());

                Bar bar = (Bar) barsGrid.getChildren().get(index);
                animate(bar, height, CompareValue.NORMAL_COLOR);
            });
        }
    }

    /**
     * Use slope and y-intercept formulas to calculate the bars height
     * for resizing.
     */
    private double calculateHeight(double value) {
        double x1 = RandomValues.getMaxValue(), 
               y2 = barsGrid.getHeight();

        // calculate the new height
        double height = y2 - ((-y2 / x1) * value + ((y2 * x1) / (x1)));

        return Math.round(height);
    }
    
    public void animate(Bar rect, double height, Color color) {
        final Timeline tl = new Timeline();
        tl.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(Math.max(MainController.DELAY_PROPERTY.get()/3, 20)),
                        new KeyValue(rect.colorProperty, color),
                        new KeyValue(rect.prefHeightProperty(), height),
                        new KeyValue(rect.maxHeightProperty(), height)));
        tl.play();
    }


    @Override
    public Object apply(Object object) {
        CompareValue[] objectArray = (CompareValue[]) object;

        for (int indexPos = 0; indexPos < objectArray.length; indexPos++) {

            CompareValue compareValue = objectArray[indexPos];

            String webColor = "#".concat(Integer.toHexString(compareValue.getColor().hashCode()));
            Color color = compareValue.getColor();
            int value = compareValue.getValue();

            Bar rect = (Bar) barsGrid.getChildren().get(indexPos);
            TextField textfield = (TextField) textFieldsGrid.getChildren().get(indexPos);
//            rect.setStyle("-fx-background-color: " + webColor + ";");
            textfield.setStyle("-fx-border-color: " + webColor + ";"
                    + "-fx-background-color: " + webColor.replace("ff", "33") + ";");

            textfield.setText(String.valueOf(value));

            final double height = calculateHeight(value);

            animate(rect, height, color);
        }

        return null;
    }
}
