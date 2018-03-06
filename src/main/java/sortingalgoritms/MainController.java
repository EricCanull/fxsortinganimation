/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package sortingalgoritms;


import java.net.URL;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import java.util.ResourceBundle;
import java.util.stream.IntStream;
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
import javafx.scene.layout.AnchorPane;
import sortingalgoritms.sorts.SortOperatorList;
import sortingalgoritms.ui.AnimationPane;
import javafx.util.Duration;
import sortingalgoritms.sorts.BaseSortOperator;
import sortingalgoritms.util.Logger;
import sortingalgoritms.util.UtilSortHandler;

/**
 * FXML Controller class
 *
 * @author Eric Canull
 */
public class MainController implements Initializable {

    public static final SimpleIntegerProperty DELAY_PROPERTY = new SimpleIntegerProperty();

    private final SimpleBooleanProperty disableUI = new SimpleBooleanProperty(false);

    private final SortOperatorList sortOperators = new SortOperatorList();

    @FXML private AnchorPane anchorPane;
    @FXML private TextArea logTextArea;
    @FXML private Button startButton;
    @FXML private Button clearButton;
    @FXML private ComboBox<String> algorithmComboBox;
    @FXML private ComboBox<String> presetValuesComboBox;
    @FXML private Spinner<Integer> delaySpinner;
    @FXML private Label algorithmLabel, countLabel;

    private AnimationPane animationPane;

    private Thread thread;
    private Timeline timeline;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.animationPane = new AnimationPane();
        AnchorPane.setTopAnchor(animationPane,    50.0);
        AnchorPane.setBottomAnchor(animationPane,  0.0);
        AnchorPane.setLeftAnchor(animationPane,    0.0);
        AnchorPane.setRightAnchor(animationPane,   0.0);
        anchorPane.getChildren().add(animationPane);

        algorithmComboBox.getItems().setAll(getAlgorithmsList());
        algorithmComboBox.getSelectionModel().select(1);

        presetValuesComboBox.getItems().setAll(getPresetValuesList());
        presetValuesComboBox.valueProperty().addListener(o -> {
            if (disableUI.get()) {
                return;
            }
            animationPane.setPresetValues(presetValuesComboBox.getValue());
            animationPane.createBars();

        });
        presetValuesComboBox.getSelectionModel().select(2);

        // Create spinner factory with default min, max, current, step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2000, 50, 10);

        // Set the spinner value factory
        delaySpinner.setValueFactory(valueFactory);
        DELAY_PROPERTY.bind(delaySpinner.valueProperty());

        // Create a timeline with animation delay and infinite cycle count
        timeline = new Timeline(new KeyFrame(
                Duration.millis(delaySpinner.getValue()), ae -> updateViews()));
        timeline.setCycleCount(Animation.INDEFINITE);

        // Bind algorithm name to the label
        algorithmLabel.textProperty().set(algorithmComboBox.getValue());

        // Bind the buttons disable property to the disable UI property
        startButton.disableProperty().bind(disableUI);
        clearButton.disableProperty().bind(disableUI);
        algorithmComboBox.disableProperty().bind(disableUI);
        presetValuesComboBox.disableProperty().bind(disableUI);
    }

    @FXML
    private void startAction(ActionEvent event) {
        if (disableUI.get()) {
            return;
        }

        if (algorithmComboBox.getSelectionModel().getSelectedIndex() == 0) {
         //  performAllSortOperations();
        } else {
           performSingleOperation();
        }
    }
    
    // Initialize the sorting process for all sort operations
    private void performAllSortOperations() {

        disableUI.set(true);

        // Create a separate thread for the animation
        thread = new Thread() {
            @Override
            public void run() {
                // Loop through the entire sort list to perform all available sorts
                IntStream.range(0, sortOperators.getList().size() - 1).forEachOrdered(index -> {

                    String sortName = getAlgorithmsList().get(index + 1) + " Sort\n";

                    Platform.runLater(() -> {
                        logTextArea.appendText(Arrays.toString(animationPane.getBarArray()) + "\n\n");
                     //   String sortName =  algorithmComboBox.getSelectionModel().getSelectedItem() + " Sort\n";
                        algorithmLabel.setText(sortName);
                        logTextArea.appendText(sortName);
                    });

                    // Record the start time to measure efficency
                    LocalTime startTime = LocalTime.now();

                    new BaseSortOperator(sortOperators.getList().get(index)).sort(animationPane.getBarArray(),
                            0, animationPane.getBarArray().length - 1);

                    // Record the start time to measure efficency
                    LocalTime endTime = LocalTime.now();

                    updateViews();
                    
                    // Reload the original unsorted list into the array
                    Platform.runLater(() -> animationPane.resetBars());

                        // Update javafx components in a separate thread
                        Platform.runLater(() -> {
                            countLabel.setText(String.valueOf(0)); // Reset count iteration
                            appendMetricText(sortName, startTime, endTime);  // Log metric data
                        });
                });
                disableUI.set(false); // enable ui
                timeline.stop();
            }
        };
        timeline.play();
        thread.start();
    }

    // Initialize the sorting process for one specified sort operation
    private void performSingleOperation() {
        disableUI.set(true);
        
        logTextArea.appendText(Arrays.toString(animationPane.getBarArray()) + "\n\n");
        String sortName =  algorithmComboBox.getSelectionModel().getSelectedItem() + " Sort\n";
        algorithmLabel.setText(sortName);
        logTextArea.appendText(sortName);
    
        // Reset count iteration
        countLabel.setText(String.valueOf(0));

        // Create a separate thread for the animation
        thread = new Thread() {
            
            @Override
            public void run() {

                // Record the start time to measure efficency
                LocalTime startTime = LocalTime.now();

                // Perform the sort at the position in the list
                new BaseSortOperator(sortOperators.getList().get(
                        algorithmComboBox.getSelectionModel().getSelectedIndex() - 1))
                        .sort(animationPane.getBarArray(),
                                0, animationPane.getBarArray().length - 1);

                // Get the end time to measure efficiency
                LocalTime endTime = LocalTime.now();

                // Record the end time to measure efficency               
                Platform.runLater(() -> {
                    appendMetricText(sortName, startTime, endTime);
                });

                // Stop the clock 
                timeline.stop();
                
                Platform.runLater(() -> {
                    updateViews();
                    disableUI.set(false); 
                });
            }; 
        };
       
        timeline.play();
        thread.start();
        
        
    }

    /**
     * Updates the animation pane bars and numbered text fields
     */
    private void updateViews() {
         Platform.runLater(() -> countLabel.setText(""+Logger.getCount()));
        UtilSortHandler.SortClass.apply(animationPane.getBarArray(), animationPane);
       
    }

    /**
     * Appends the info text area with the metric data for the sorting routine.
     */
    private void appendMetricText(String sortName, LocalTime startTime, LocalTime endTime) {

        // Current time mark
        endTime = LocalTime.now();

        // Calculates the difference between start and end time
        LocalTime duration = endTime.minusNanos(startTime.getNano());
        
        StringBuilder sb = new StringBuilder();
        sb.append("Start:   ").append(startTime).append("\n");
        sb.append("End:     ").append(endTime).append("\n");
        sb.append("Delay:  ").append(delaySpinner.getValue()).append(" ms").append("\n");
        sb.append("Speed: ").append(duration.getNano()  / 1000000).append(" ms").append("\n\n");
        
        // Appends the time stamp to the text area on the left-side display
        logTextArea.appendText(sb.toString());
    }

    @FXML
    private void clearTextArea(ActionEvent event) {
        logTextArea.clear();
    }

    // The list of sort algorithms to choose in the combobox
    private static List<String> getAlgorithmsList() {
        String[] algorithms
                = {"All", "Bubble", "Selection", "Insertion", "Merge", "Quick",
                    "Shell", "Pancake", "Cocktail", "Heap", "Exchange"};
        return Arrays.asList(algorithms);
    }

    // The list of preset values to choose in the combobox
    private static List<String> getPresetValuesList() {
        String[] presets
                = {"Random", "Ordered", "Reverse", "Hundreds", "Thousands"};
        return Arrays.asList(presets);
    }
}
