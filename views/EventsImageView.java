package views;

/**
 * Sets the Label and imageview
 * to be used in the EventsView
 * 
 * @author Kahlie
 * @date 5/5/20
 */

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

public class EventsImageView {
    
    @FXML
    private Label eventName;

    @FXML
    private ImageView eventImage;

    //=================  GETTERS ===============
    public Label getEventName() {
        return eventName;
    }
    
    public ImageView getEventImage() {
        return eventImage;
    }
    
    //=================  SETTERS ===============
    public void setEventName(String _category) {
       this.eventName.setText(_category);
    }

    public void setEventImage(Image _image) {
        this.eventImage.setImage(_image);
    }
}