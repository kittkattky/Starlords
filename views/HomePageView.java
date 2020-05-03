package views;

/**
 * This view handles all elements on the Homepage scene
 *
 * @author Diego Rodriguez Last Updated: 4/18/20
 */
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import utilities.Homepage.EventHandlers;

public class HomePageView implements Initializable {

    @FXML 
    private AnchorPane anchorPane;
    
    @FXML
    private ImageView eventPic;

    @FXML
    private ImageView restaurantPic;

    @FXML
    private ImageView moviePic;

    @FXML
    private ImageView calendarPic;

    @FXML
    private Label myAccountLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Rectangle accountRectangle;

    @FXML
    private Rectangle calendarRectangle;

    public EventHandlers handler = new EventHandlers();

    /**
     * This method is inherited from the Java interface "Initializable" and is
     * always called when Scene is created. It adds eventHandlers to all areas
     * of scene where the user can click, using helper method.
     * Also sets gradient for anchor pane.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.restaurantPic.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler.createCuisineEventHandler());
        this.moviePic.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler.createGenreEventHandler());
        this.myAccountLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler.createMyAccountEventHandler());

        Stop[] stops = new Stop[]{new Stop(0, Color.web("#5C258D")), new Stop(1, Color.web("#4389A2"))};
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill fillBackground = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        this.anchorPane.setBackground(new Background(fillBackground));

    }

    //=================  SETTERS =============== 
    public void setHomePageText(String _welcomeMessage) {
        this.nameLabel.setText(_welcomeMessage);
    }

    public void setLightAccountRectangle() {
        this.accountRectangle.setFill(Color.valueOf("95a1cf"));
    }

    public void setDarkAccountRectangle() {
        this.accountRectangle.setFill(Color.valueOf("6b7497"));
    }

    public void setLightCalendarRectangle() {
        this.calendarRectangle.setFill(Color.valueOf("95a1cf"));
    }

    public void setDarkCalendarRectangle() {
        this.calendarRectangle.setFill(Color.valueOf("6b7497"));
    }

}
