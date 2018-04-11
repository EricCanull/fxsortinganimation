/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
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
import sortingalgoritms.sorts.SortOperatorList;
import sortingalgoritms.ui.GraphicsController;
import sortingalgoritms.sorts.BaseSortOperator;
import sortingalgoritms.util.Logger;
import sortingalgoritms.sorts.BaseSortHandler;
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
    @FXML private ComboBox<String> algorithmComboBox, presetValuesComboBox;
    @FXML private Spinner<Integer> delaySpinner;
    @FXML private Label algorithmLabel, countLabel, statusLabel, hourGlassLabel;

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
        algorithmComboBox.getItems().setAll(getAlgorithmsList());
        algorithmComboBox.getSelectionModel().select(1);
        
        // Add preset values list and listener to combobox
        presetValuesComboBox.getItems().setAll(getPresetValuesList());
        presetValuesComboBox.valueProperty().addListener(o -> presetValuesAction());
        presetValuesComboBox.getSelectionModel().select(0);

        // Create spinner factory with default min, max, current, step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 2000, 50, 10);

        // Set the spinner value factory
        delaySpinner.setValueFactory(valueFactory);
        DELAY_PROPERTY.bind(delaySpinner.valueProperty());

        // Create a timeline with animation delay and indefinite cycle count
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(
                delaySpinner.getValue()), ae -> updateViews()));
        timeline.setCycleCount(Animation.INDEFINITE);

        // Bind algorithm name to the label
        algorithmLabel.textProperty().set(algorithmComboBox.getValue());

        // Bind the UI controls disable property
        startButton.disableProperty().bind(disableUI);
        clearButton.disableProperty().bind(disableUI);
        delaySpinner.disableProperty().bind(disableUI);
        algorithmComboBox.disableProperty().bind(disableUI);
        presetValuesComboBox.disableProperty().bind(disableUI);
        hourGlassLabel.visibleProperty().bind(disableUI);
    }
    
    /**
     * Observable helper method updates the preset values
     * on selection change. 
     * 
     */
    private void presetValuesAction() {
        graphicsController.setPresetValues(presetValuesComboBox.getValue());
    }
    
    /**
     * Mouse listener increases or decreases the spinner value with
     * the mouse scroll wheel.
     * 
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
     * Start button method to run the sorting operation
     */
    @FXML
    private void startAction(ActionEvent event) {
        startSort();
    }
    
    /**
     * Starts the sorting operation
     */
    public void startSort() {
        
        disableUI.set(true); // Disable UI
        countLabel.setText("0"); // Reset count label
        
        // Load the selected algorithm and display the preset values in the text area
        int sortIndex = getAlgorithmIndex();
        appendTextArea(presetValuesComboBox.getValue(), " Values\n");
        appendTextArea(RandomBars.getString(), "\n\n");
        
        // Update the algorithm name to the labels and text area
        String sortName = algorithmComboBox.getSelectionModel().getSelectedItem() + " Sort\n";
        algorithmLabel.setText(sortName);
        logTextArea.appendText(sortName);
       
        // Start the timeline
        timeline.play();
        
        // Create a new thread to run the sorting algorithm
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            // Record the start time to measure efficency
           final long startTime = System.nanoTime();

            // Perform the sort at the position in the list
            new BaseSortOperator(sortOperators.getList().get(sortIndex))
                    .sort(RandomBars.barsArray,
                            0, RandomBars.barsArray.length - 1);

            // Record the end time to measure efficency
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
     * Shutdown running threads and update the status label
     */
    private void stop(ExecutorService executor) {
        try {
            setStatusText("Status: Stoping");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            setStatusText("Status: Interrupted");
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow(); 
            }

            timeline.stop(); // stop timeline 
            disableUI.set(false); // enable UI
            setStatusText("Status: Ready"); // Set status
        }
    }

    /**
     * Updates the animation pane bars and numbered text fields
     * with progressive sort data until the sorting is complete.
     */
    private void updateViews() {
        BaseSortHandler.SINGLETON.apply(RandomBars.barsArray, graphicsController);
     
         Platform.runLater(() -> {
            statusLabel.setText("Status: Sorting");
            countLabel.setText("" + Logger.getCount());
        });
    }
    
    /**
     * Sets the status label text
     * @param status 
     */
    private void setStatusText(String status) {
         Platform.runLater(() -> (statusLabel.setText(status)));
    }

    /**
     * Appends text area with logging data
     * @param text1
     * @param text2 
     */
    private void appendTextArea(String text1, String text2) {
        // Create a new string builder  
        final StringBuilder sb = new StringBuilder();
        sb.append(text1).append(" ").append(text2);
        
        logTextArea.appendText(sb.toString());
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
        sb.append("Delay: ").append(delaySpinner.getValue()).append(" ms").append("\n");
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
        return algorithmComboBox.getSelectionModel().getSelectedIndex();
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
    private static List<String> getPresetValuesList() {
        String[] presets
                = {"Random", "Ordered", "Reverse", "Hundreds", "Thousands"};
        return Arrays.asList(presets);
    }
}
