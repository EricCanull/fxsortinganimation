/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
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
            tf.setText(String.valueOf(RandomBars.barsArray[index].getValue()));
        });
        
        addGridBars();
    }

    public void addGridBars() {

        if (Double.isNaN(barsGrid.getWidth()) || Double.isNaN(barsGrid.getHeight())
                || RandomBars.barsArray == null) {
            return;
        }

        barsGrid.getChildren().removeAll(barsGrid.getChildren());

        IntStream.range(0, 10).forEachOrdered(index -> {
            Bar bar = RandomBars.barsArray[index];
            bar.getStyleClass().add("bar");

            double height = calculateHeight(bar.getValue());
            bar.setMaxHeight(height);
            bar.setPrefHeight(height);

            bar.setBackground(new Background(new BackgroundFill(bar.getColor(),
                    CornerRadii.EMPTY, Insets.EMPTY)));
            barsGrid.add(bar, index, 0);
        });
    }

    /**
     * Uses slope formulas to calculate the bars height in proportion to
     * the max value in the array and the bar value.
     * 
     */
    private double calculateHeight(double value) {
        double y1 = 0;
        double y2 = barsGrid.getHeight();

        double x1 = RandomBars.getMax();
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
        if (indexPos == 10) {
            indexPos = 0;
        }

        while (indexPos <= 9) {
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
