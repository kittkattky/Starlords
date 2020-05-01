package views;

/**
 * Sets the Label and imageview
 * to be used in the EventsView
 * 
 * @author Kahlie
 * @date 4/2/20
 */

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class EventsImageView {
    
    @FXML
    private Label eventName;

    @FXML
    private ImageView eventImage;
    
    @FXML
    private AnchorPane eventAcPane;
    
    public AnchorPane ACP(){
        return eventAcPane;
    }

    public Label getEventName() {
        return eventName;
    }
    
    public void setEventName(String _category) {
       this.eventName.setText(_category);
    }

    public ImageView getEventImage() {
        return eventImage;
    }

    public void setEventImage(Image _image) {
        this.eventImage.setImage(_image);
    }
}