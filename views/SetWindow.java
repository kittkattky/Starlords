package views;

/**
 * Abstract class that is used with the views.
 * 
 * @author Kahlie
 * @date 4/29/20
 */

import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Main;

public abstract class SetWindow {
    protected Stage window;
    protected Main mainApplication;

    // Sets the window for this controller
    public void setWindow(Stage window) {
        this.window = window;
        this.window.setTitle("Hey Friday!");
        Image icon = new Image(getClass().getResourceAsStream("/gui artifacts/HeyFriday.png"));
        this.window.getIcons().add(icon);
    }

    // Sets the main application for this controller
    public void setMainApplication(Main mainApplication) {
        this.mainApplication = mainApplication;
    }
}
