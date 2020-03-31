package restaurantui;

/**
 * This is the main class for executing the program.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RestaurantUI extends Application {

    @Override
    public void start(Stage _stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("CuisineListUI.fxml"));
        Scene scene = new Scene(root);
        _stage.setScene(scene);
        _stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
