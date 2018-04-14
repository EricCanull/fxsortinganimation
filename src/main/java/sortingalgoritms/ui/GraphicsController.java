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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sortingalgoritms.util.RandomBars;
import sortingalgoritms.util.ISortHandler;

/**
 * FXML Controller class
 *
 * @author Eric Canull
 */
public class GraphicsController extends AnchorPane implements ISortHandler {
    
    @FXML private GridPane barsGrid;
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
            loader.setLocation(GraphicsController.class.getResource("/fxml/FXMLGraphicsPane.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GraphicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        barsGrid.widthProperty().addListener(evt -> addGridBars());
        barsGrid.heightProperty().addListener(evt -> addGridBars());
    }
    
    public void setPresetValues(String presetChoice) {
        RandomBars.setRandomSet(presetChoice, null);
     
        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGrid.getChildren().get(index);
            tf.setText(String.valueOf(RandomBars.getArray()[index].getValue()));
        });
        
        addGridBars();
    }

    public void addGridBars() {

        if (RandomBars.getArray() == null
                || Double.isNaN(barsGrid.getWidth())
                || Double.isNaN(barsGrid.getHeight())) {
            return;
        }

        barsGrid.getChildren().removeAll(barsGrid.getChildren());

        IntStream.range(0, 10).forEachOrdered(index -> {
            Bar bar = RandomBars.getArray()[index];
            bar.getStyleClass().add("bar");

            double height = calculateHeight(bar.getValue());
            bar.setMaxHeight(height);
            bar.setPrefHeight(height);
            barsGrid.add(bar, index, 0);
        });
    }

    /**
     * Use slope and y-intercept formulas to calculate the bars height
     * for resizing.
     */
    private double calculateHeight(double value) {
        double y1 = 0;
        double y2 = barsGrid.getHeight();

        double x1 = RandomBars.getMaxValue();
        double x2 = 0;

        // 1st calculate the slope 
        double slope = (y1 - y2) / (x1 - x2);

        // 2nd calculate the y-Intercept
        double yIntercept = (y2 * x1 - y1 * x2) / (x1 - x2);

        // 3rd calculate the new height
        double height = y2 - (slope * value + yIntercept);

        return height;
    }

    @Override
    public Object apply(Object object) {
        if (indexPos == RandomBars.MAX_SIZE) {
            indexPos = 0;
        }

        while (indexPos < RandomBars.MAX_SIZE) {
            Bar bar = (Bar) object;   
            String color = Integer.toHexString(bar.getColor().hashCode());
            
            Bar gridBar = (Bar) barsGrid.getChildren().get(indexPos);   
            TextField textfield = (TextField) textFieldsGrid.getChildren().get(indexPos);
            
            gridBar.setStyle("-fx-background-color: #" + color + ";");
            
            textfield.setStyle("-fx-border-color: #" + color + ";" 
                             + "-fx-background-color: #" + color.replace("ff", "33") + ";");
          
            double height = calculateHeight(bar.getValue());
            gridBar.setMaxHeight(height);
            gridBar.setPrefHeight(height);
            textfield.setText(String.valueOf(bar.getValue()));

            indexPos++;
            break;
        }
        
        return null;
    }
}
