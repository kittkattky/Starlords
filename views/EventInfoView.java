package views;

/* Displays the events details, Title
 * image, description, etc.
 *
 * @author Kahlie
 * @date 5/5/20
 */

import controllers.EventsController;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

public class EventInfoView extends SetWindow {

    @FXML
    private Label titleLabel;
    @FXML
    private Label venueNameLabel;
    @FXML
    private Label venueAddressLabel;
    @FXML
    private Hyperlink venueUrlLink;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private GridPane gridPane;

    protected boolean hyperLink = true;

    public EventsController eventsController = new EventsController();

    protected EventHandlers handler = new EventHandlers();

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
        mainVBox.prefWidthProperty().bind(scrollPane.widthProperty());

        // Setup the backdrop image view
        imageView.setPreserveRatio(true);

        // Setup the gridpane column
        gridPane.prefWidthProperty().bind(scrollPane.widthProperty());
    }

    /**
     * This method will take the user back to the EventsMain view.
     * @param _event
     * @throws IOException
     * @throws org.json.JSONException
     */
    @FXML
    public void backToEventsButtonClick(ActionEvent _event) throws IOException, JSONException {
        EventsView view = this.handler.switchScenes(_event, "fxml/EventsMainView.fxml").getController();
        view.eventsController.uuidController.setUUID(this.eventsController.uuidController.getUUID());
        view.eventsController.setZipCode();
    }

    /**
     * This method loads the image, if the image is null uses a substitute image
     * and if image link is incorrect use the substitute image.
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
            imageView.setImage(eventImage);
        }
    }

    /**
     * Sets the event details to the labels.
     *
     * @throws IOException
     * @throws JSONException
     */
    public void Events() throws IOException, JSONException {
        titleLabel.setText(this.title);
        if (this.venueName != null) {
            venueNameLabel.setText(this.venueName);
        } else {
            venueNameLabel.setText("N/A");
        }
        venueUrlLink.setText(this.venueUrl);
        if (this.venueAddress != null) {
            venueAddressLabel.setText(this.venueAddress);
        } else {
            venueAddressLabel.setText("N/A");
        }
        if (this.description != null) {
            this.descriptionLabel.setText(this.description);
            loadImage();
        } else {
            this.descriptionLabel.setText("Description Not Avaialable");
        }
    }

    /**
     * When user clicks on the link it will open the link in the browser.
     *
     * @param _hyperLinkClicked
     * @throws URISyntaxException
     */
    public void openURL(ActionEvent _hyperLinkClicked) throws URISyntaxException {
        if (this.hyperLink) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(this.venueUrlLink.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(RestaurantListView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Sets the event details from the EventsView.
     *
     * @param _title
     * @param _image
     * @param _description
     * @param _venueName
     * @param _venueAddress
     * @param _venueUrl
     * @throws IOException
     * @throws JSONException
     */
    public void setEvent(String _title, String _image, String _description, String _venueName, String _venueAddress, String _venueUrl) throws IOException, JSONException {
        this.title = this.eventsView.title;
        this.title = _title;

        this.image = this.eventsView.image;
        this.image = _image;

        this.description = this.eventsView.description;
        this.description = _description;

        this.venueName = this.eventsView.venueName;
        this.venueName = _venueName;

        this.venueAddress = this.eventsView.venueAddress;
        this.venueAddress = _venueAddress;

        this.venueUrl = this.eventsView.venueUrl;
        this.venueUrl = _venueUrl;

        initialize();
    }
}
