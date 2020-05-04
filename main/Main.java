package main;

/**
 * This class is the main class and loads the login screen
 *
 * @author Kahlie Last Updated: 2/17/2020
 */

import controllers.UUIDController;
import java.io.IOException;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONException;
import views.EventInfoView;
import views.LogInPageView;

public class Main extends Application {

    private static Stage window;
    private static Scene eventScene;
    private static AnchorPane rootLayout;


    /**
     * Starts the application
     * @param args 
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Called to start application
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;

        // Setup the main root layout
        initRootLayout();

        // Display window and the root scene
        this.eventScene = new Scene(this.rootLayout);
        this.window.setScene(eventScene);
        this.window.show();
    }

    /**
     * Initialises the main root layout node and sets the scene for the window
     */
    private static void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/LogInPage.fxml"));
            rootLayout = loader.load();

            // setup the views window and reference to this main application class
            LogInPageView view = loader.getController();
            view.setWindow(window);
        }
        catch (IOException ex) {
            System.err.println("Unable to init root layout from Main Application");
            ex.printStackTrace();
        }
    }

    /**
     * Transitions from the event view to the event info view 
     * @param title
     * @param image
     * @param description
     * @param venueName
     * @param venueAddress
     * @param venueUrl
     * @throws JSONException 
     */
    public static void transitionToEventInfoView(String uuid, String title, String image, String description, String venueName, String venueAddress, String venueUrl) throws JSONException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/EventInfoView.fxml"));
            Pane layout = loader.load();

            // setup view
            EventInfoView view = loader.getController();
            view.eventsController.uuidController.setUUID(uuid);
            view.setWindow(window);
            view.setEvent(title, image, description, venueName, venueAddress, venueUrl);

            window.setScene(new Scene(layout));
        }
        catch (IOException ex) {
            System.err.println("Unable to load event info layout from fxml file in main application.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
