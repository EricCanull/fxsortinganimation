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
package sortingalgoritms;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.Observable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import sortingalgoritms.sorts.AbstractSort;
import sortingalgoritms.sorts.SortOperatorList;
import sortingalgoritms.ui.GraphicsController;
import sortingalgoritms.util.Logger;
import sortingalgoritms.sorts.SortOperator;
import sortingalgoritms.util.RandomBars;

/**
 * FXML Controller class
 *
 * @author Eric Canull
 */
public class MainController implements Initializable {

    public static final SimpleIntegerProperty DELAY_PROPERTY = new SimpleIntegerProperty();

    private final SimpleBooleanProperty disableUI = new SimpleBooleanProperty(false);

    private final SortOperatorList sortOperators = new SortOperatorList();
    
    private GraphicsController graphicsController;

    private Timeline timeline;
       
    @FXML private AnchorPane anchorPane;
    @FXML private TextArea logTextArea;
    @FXML private Button startButton, clearButton;
    @FXML private ComboBox<String> algorithmsComboBox, presetsComboBox;
    @FXML private Spinner<Integer> delaySpinner;
    @FXML private Label algorithmLabel, countLabel, statusLabel;
    
    /**
     * Initializes the main controller class.
     * @param url 
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Add the graphics controller pane 
        graphicsController = new GraphicsController();
        AnchorPane.setTopAnchor(graphicsController, 50.0);
        AnchorPane.setBottomAnchor(graphicsController, 0.0);
        AnchorPane.setLeftAnchor(graphicsController, 0.0);
        AnchorPane.setRightAnchor(graphicsController, 0.0);
        anchorPane.getChildren().add(graphicsController);
        
        // Add algoritms list and select the first index in the combobox
        algorithmsComboBox.getItems().setAll(getAlgorithmsList());
        algorithmsComboBox.getSelectionModel().select(1);
        
        // Add preset values list and listener to combobox
        presetsComboBox.getItems().setAll(getPresetsList());
        presetsComboBox.valueProperty().addListener(this::presetComboBoxAction);
        presetsComboBox.getSelectionModel().select(0);

        // Create spinner factory with default min, max, current, step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 2000, 100, 10);

        // Set the spinner value factory
        delaySpinner.setValueFactory(valueFactory);
        DELAY_PROPERTY.bind(delaySpinner.valueProperty());

        // Create a timeline with animation delay and indefinite cycle count
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(
                delaySpinner.getValue()), ae -> updateViews()));
        timeline.setCycleCount(Animation.INDEFINITE);
      
        // Bind algorithm name to the label
        algorithmLabel.textProperty().set(algorithmsComboBox.getValue());

        // Bind the UI controls to the disableUI boolean property 
        statusLabel.getGraphic().visibleProperty().bind(disableUI);
        startButton.disableProperty().bind(disableUI);
        clearButton.disableProperty().bind(disableUI);
        delaySpinner.disableProperty().bind(disableUI);
        algorithmsComboBox.disableProperty().bind(disableUI);
        presetsComboBox.disableProperty().bind(disableUI);
    }
       
    /**
     * Observable helper method updates the preset values
     * on selection change. 
     */
    private void presetComboBoxAction(Observable observable) {
        graphicsController.setPresetValues(presetsComboBox.getValue());
    }
    
    /**
     * Mouse listener increases or decreases the spinner value with
     * the mouse scroll wheel.
     * @param event 
     */
    @FXML
    private void spinnerScrollAction(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            delaySpinner.increment();
        } else if (event.getDeltaY() < 0) {
            delaySpinner.decrement();
        }
    }
    
    /**
     * Start button method to startSort the sorting operation
     */
    @FXML
    private void startAction(ActionEvent event) {
        startSort();
    }
    
    /**
     * Starts the sorting operation
     */
    public void startSort() {
        
        disableUI.set(true);     // Disable UI
        countLabel.setText("0"); // Reset count label

        // Load the selected algorithm 
        int sortIndex = getAlgorithmIndex();
       
        //display the preset values in the text area
        logTextArea.appendText("Preset Values\n");
        logTextArea.appendText(RandomBars.getString()
                .concat("\n\n"));

        // Update the algorithm name to the labels and text area
        String sortName = algorithmsComboBox.getValue().concat(" Sort\n");
        algorithmLabel.setText(sortName);
        logTextArea.appendText(sortName);
       
        // Start the timeline
        timeline.play();

        // Create a new thread to startSort the sorting algorithm
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            // Mark start time
            final long startTime = System.nanoTime();

            // Perform the sort at the position in the list
           AbstractSort sorter = sortOperators.getList().get(sortIndex);
           sorter.sort(RandomBars.getArray(), 0, RandomBars.MAX_SIZE-1);

            // Mark end time
            final long endTime = System.nanoTime();

            // Append text area with metric data
            Platform.runLater(() -> {
                updateViews();
                appendMetricText(startTime, endTime);
            });

            // Sort completed 
            stop(executor);
        });
    }
    
    /**
     * Shutdown running tasks and update the status label
     */
    private void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            setStatusText("Status: Interrupted");
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
            timeline.stop();
            setDisableUI(false); // enable UI
            setStatusText("Status: Ready"); // Set status
        }
    }

    /**
     * Updates the animation pane bars and numbered text fields
     * with progressive sort data until the sorting is complete.
     */
    private void updateViews() {
        SortOperator.getInstance().apply(
                RandomBars.getArray(), graphicsController);
         Platform.runLater(() -> {
            statusLabel.setText("Status: Sorting");
            countLabel.setText(Long.toString(Logger.getCount()));
        });
    }
    
    /**
     * Sets the disable UI flag in the FX thread
     * @param status 
     */
    private void setDisableUI(Boolean isDisable) {
         Platform.runLater(() -> (this.disableUI.set(isDisable)));
    }
    
    /**
     * Sets the status label text in the FX thread
     * @param status 
     */
    private void setStatusText(String status) {
         Platform.runLater(() -> (statusLabel.setText(status)));
    }

    /**
     * Appends the info text area with the metric data for the sorting routine.
     */
    private void appendMetricText(long startTime, long endTime) {

        // Calculates the difference between start and end time
        long delta = endTime - startTime;
        
        // Create a new string builder with metric data
        final StringBuilder sb = new StringBuilder();
        sb.append("Start: ").append(startTime).append(" ns \n");
        sb.append("Ended: ").append(endTime).append(" ns \n");
        sb.append("Delay: ").append(delaySpinner.getValue()).append(" ms\n");
        sb.append("Speed: ").append(delta).append(" ns").append("\n");
        sb.append("Steps: ").append(Logger.getCount()).append("\n\n");
        
        // Appends the time stamp to the text area on the left-side display
        logTextArea.appendText(sb.toString());
    }
    
    /**
     * Gets the current index selected for the algorithm combo box
     * @return 
     */
    private int getAlgorithmIndex() {
        return algorithmsComboBox.getSelectionModel().getSelectedIndex();
    }

    /**
     * Clears the text area
     * @param event 
     */
    @FXML
    private void clearTextArea(ActionEvent event) {
        logTextArea.clear();
    }

    /**
     * The list of sort algorithms to choose in the combobox
     */
    private static List<String> getAlgorithmsList() {
        String[] algorithms
                = {"Bubble", "Selection", "Insertion", "Merge", "Quick",
                    "Shell", "Pancake", "Cocktail", "Heap", "Exchange"};
        return Arrays.asList(algorithms);
    }

    /**
     * The list of preset values to choose in the combobox
     */
    private static List<String> getPresetsList() {
        String[] presets
                = {"Random", "Ordered", "Reverse", "Hundreds", "Thousands"};
        return Arrays.asList(presets);
    }
}
