package sortingalgoritms;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        // Load custom fonts used in css stylesheet
        Font.loadFont(MainApp.class.getResource("/fonts/OpenSans-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(MainApp.class.getResource("/fonts/FiraCode-Regular.ttf").toExternalForm(), 10);
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainPane.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Sorting Demo");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
