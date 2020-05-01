package views;

/* Displays the events details, Title
 * image, description, etc
 *
 * @author Kahlie
 * @date 4/27/20
 */

import controllers.EventsController;
import java.io.IOException;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import main.Main;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

public class EventInfoView extends SetWindow {

    @FXML
    private Label TitleLabel;
    @FXML
    private Label VenueNameLabel;
    @FXML
    private Label VenueAddressLabel;
    @FXML
    private Hyperlink VenueUrlLink;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private ImageView ImageView;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private VBox MainVBox;
    @FXML
    private GridPane GridPane;

    private EventsController eventsController = new EventsController();
    
    protected EventHandlers handler = new EventHandlers();

    protected Map<String, String> VenueNameMap;
    protected Map<String, String> TitleMap;
    protected Map<String, String> DescriptionMap;
    protected Map<String, String> ImageMap;
    protected Map<String, String> UrlMap;
    protected Map<String, String> VenueAddressMap;

    private EventsView eventsView;
    private String title;
    private String image;
    private String description;
    private String venueName;
    private String venueAddress;
    private String venueUrl;

    @FXML
    public void initialize() throws IOException, JSONException {
        Events();

        loadImage();

        // Bind main vbox layout width to scroll pane width so it grows in width with the scroll pane
        MainVBox.prefWidthProperty().bind(ScrollPane.widthProperty());

        // Setup the backdrop image view
        ImageView.setPreserveRatio(true);

        // Setup the gridpane column
        GridPane.prefWidthProperty().bind(ScrollPane.widthProperty());
    }

    /**
     * Takes the user back to the EventsMain view
     * @param _event
     * @throws IOException 
     */
    @FXML
    public void backToEventsButtonClick(ActionEvent _event) throws IOException {
        EventsView view = this.handler.switchScenes(_event, "fxml/EventsMainView.fxml").getController();
        view.handler.uuidController.setUUID(this.eventsController.uuidController.getUUID());
        System.out.println(this.eventsView.uuidController.getUUID());
    }

    /**
     * Loads the image if image is null returns substitute image
     */
    private void loadImage() {
        if (this.image != null) {
            String url = this.image;
            if (url == null) {
                url = "https://i.imgur.com/QeEdrPk.jpg";
            }

            if (url.startsWith("//")) {
                url = "https://i.imgur.com/QeEdrPk.jpg";
            }

            Image eventImage = new Image(url);

            ImageView.setImage(eventImage);
        }
    }

    /**
     * Sets the event details to the labels 
     *
     * @throws IOException
     * @throws JSONException
     */
    public void Events() throws IOException, JSONException {
        this.VenueNameMap = this.eventsController.getComedyEventVenueNameMap();
        this.TitleMap = this.eventsController.getComedyEventTitleMap();
        this.DescriptionMap = this.eventsController.getComedyEventDescriptionMap();
        this.ImageMap = eventsController.getComedyEventImageMap();
        this.UrlMap = this.eventsController.getComedyEventUrlMap();
        this.VenueAddressMap = this.eventsController.getComedyEventVenueAddressMap();

        TitleLabel.setText(this.title);

        if (this.venueName != null) {
            VenueNameLabel.setText(this.venueName);
        } else {
            VenueNameLabel.setText("N/A");
        }

        VenueUrlLink.setText(this.venueUrl);

        if (this.venueAddress != null) {
            VenueAddressLabel.setText(this.venueAddress);
        } else {
            VenueAddressLabel.setText("N/A");
        }

        if (this.description != null) {
            this.DescriptionLabel.setText(this.description);
            loadImage();
        } else {
            this.DescriptionLabel.setText("Description Not Avaialable");
        }
    }

    /**
     * Sets the event details from the EventsView
     *
     * @param title
     * @throws IOException
     * @throws JSONException
     */
    public void setEvent(String title, String image, String description, String venueName, String venueAddress, String venueUrl) throws IOException, JSONException {
        this.title = this.eventsView.title;
        this.title = title;

        this.image = this.eventsView.image;
        this.image = image;

        this.description = this.eventsView.description;
        this.description = description;

        this.venueName = this.eventsView.venueName;
        this.venueName = venueName;

        this.venueAddress = this.eventsView.venueAddress;
        this.venueAddress = venueAddress;

        this.venueUrl = this.eventsView.venueUrl;
        this.venueUrl = venueUrl;

        initialize();
    }
}
