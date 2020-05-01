package views;

/* Main view class for the events. 
 * Displays the events and allows
 * the user to select an event
 *
 * @author Kahlie
 * @date 4/29/20
 */
import controllers.EventsController;
import controllers.UUIDController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.json.JSONException;
import javafx.scene.image.Image;
import main.Main;
import utilities.Homepage.EventHandlers;

public class EventsView extends SetWindow implements Initializable {

    @FXML
    protected ListView<String> categoriesListView;
    
    @FXML
    private ScrollPane eventsScrollPane;
    
    private FlowPane eventsFlowPane;
    
    protected EventHandlers handler = new EventHandlers();

    protected static String title;
    protected static String venueName;
    protected static String description;
    protected static String venueUrl;
    protected static String venueAddress;
    protected static String image;
    
    protected Map<Integer, String> categoriesMap;
    
    protected Map<Integer, String> TitleMap;
    protected Map<String, String> ImageMap;
    protected Map<String, String> VenueNameMap;
    protected Map<String, String> DescriptionMap;
    protected Map<String, String> VenueUrlMap;
    protected Map<String, String> VenueAddressMap;

    public EventsController eventsController = new EventsController();
    public UUIDController uuidController = new UUIDController();

    /**
     * Builds the flow pane by populating with image views
     * @throws JSONException
     * @throws IOException 
     */
    private void buildComedyEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getComedyEventTitleMap();
        this.ImageMap = eventsController.getComedyEventImageMap();
        this.VenueNameMap = eventsController.getComedyEventVenueNameMap();
        this.DescriptionMap = eventsController.getComedyEventDescriptionMap();
        this.VenueAddressMap = eventsController.getComedyEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getComedyEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }

    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildMusicEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getMusicEventTitleMap();
        this.ImageMap = eventsController.getMusicEventImageMap();
        this.DescriptionMap = eventsController.getMusicEventDescriptionMap();
        this.VenueNameMap = eventsController.getMusicEventVenueNameMap();
        this.VenueAddressMap = eventsController.getMusicEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getMusicEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }

    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildConferenceEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getConferenceEventTitleMap();
        this.ImageMap = eventsController.getConferenceEventImageMap();
        this.DescriptionMap = eventsController.getConferenceEventDescriptionMap();
        this.VenueNameMap = eventsController.getConferenceEventVenueNameMap();
        this.VenueAddressMap = eventsController.getConferenceEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getConferenceEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildEducationEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getEducationEventTitleMap();
        this.ImageMap = eventsController.getEducationEventImageMap();
        this.DescriptionMap = eventsController.getEducationEventDescriptionMap();
        this.VenueNameMap = eventsController.getEducationEventVenueNameMap();
        this.VenueAddressMap = eventsController.getEducationEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getEducationEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildFamilyEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getFamilyEventTitleMap();
        this.ImageMap = eventsController.getFamilyEventImageMap();
        this.DescriptionMap = eventsController.getFamilyEventDescriptionMap();
        this.VenueNameMap = eventsController.getFamilyEventVenueNameMap();
        this.VenueAddressMap = eventsController.getFamilyEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getFamilyEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
     /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildFestivalsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getFestivalsEventTitleMap();
        this.ImageMap = eventsController.getFestivalsEventImageMap();
        this.DescriptionMap = eventsController.getFestivalsEventDescriptionMap();
        this.VenueNameMap = eventsController.getFestivalsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getFestivalsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getFestivalsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
     /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildFilmEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getFilmEventTitleMap();
        this.ImageMap = eventsController.getFilmEventImageMap();
        this.DescriptionMap = eventsController.getFilmEventDescriptionMap();
        this.VenueNameMap = eventsController.getFilmEventVenueNameMap();
        this.VenueAddressMap = eventsController.getFilmEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getFilmEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
     /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildFoodandWineEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getFoodandWineEventTitleMap();
        this.ImageMap = eventsController.getFoodandWineEventImageMap();
        this.DescriptionMap = eventsController.getFoodandWineEventDescriptionMap();
        this.VenueNameMap = eventsController.getFoodandWineEventVenueNameMap();
        this.VenueAddressMap = eventsController.getFoodandWineEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getFoodandWineEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }           
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
     /**
     * Builds the poster flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildCharityEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getCharityEventTitleMap();
        this.ImageMap = eventsController.getCharityEventImageMap();
        this.DescriptionMap = eventsController.getCharityEventDescriptionMap();
        this.VenueNameMap = eventsController.getCharityEventVenueNameMap();
        this.VenueAddressMap = eventsController.getCharityEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getCharityEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
     /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildExhibitsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getExhibitsEventTitleMap();
        this.ImageMap = eventsController.getExhibitsEventImageMap();
        this.DescriptionMap = eventsController.getExhibitsEventDescriptionMap();
        this.VenueNameMap = eventsController.getExhibitsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getExhibitsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getExhibitsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane); 
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildHealthEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getHealthEventTitleMap();
        this.ImageMap = eventsController.getHealthEventImageMap();
        this.DescriptionMap = eventsController.getHealthEventDescriptionMap();
        this.VenueNameMap = eventsController.getHealthEventVenueNameMap();
        this.VenueAddressMap = eventsController.getHealthEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getHealthEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);    
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildHolidayEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getHolidayEventTitleMap();
        this.ImageMap = eventsController.getHolidayEventImageMap();
        this.DescriptionMap = eventsController.getHolidayEventDescriptionMap();
        this.VenueNameMap = eventsController.getHolidayEventVenueNameMap();
        this.VenueAddressMap = eventsController.getHolidayEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getHolidayEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildBooksEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getBooksEventTitleMap();
        this.ImageMap = eventsController.getBooksEventImageMap();
        this.DescriptionMap = eventsController.getBooksEventDescriptionMap();
        this.VenueNameMap = eventsController.getBooksEventVenueNameMap();
        this.VenueAddressMap = eventsController.getBooksEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getBooksEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);  
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildAttractionsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getAttractionsEventTitleMap();
        this.ImageMap = eventsController.getAttractionsEventImageMap();
        this.DescriptionMap = eventsController.getAttractionsEventDescriptionMap();
        this.VenueNameMap = eventsController.getAttractionsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getAttractionsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getAttractionsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildNeighborhoodEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getNeighborhoodEventTitleMap();
        this.ImageMap = eventsController.getNeighborhoodEventImageMap();
        this.DescriptionMap = eventsController.getNeighborhoodEventDescriptionMap();
        this.VenueNameMap = eventsController.getNeighborhoodEventVenueNameMap();
        this.VenueAddressMap = eventsController.getNeighborhoodEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getNeighborhoodEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildBusinessEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getBusinessEventTitleMap();
        this.ImageMap = eventsController.getBusinessEventImageMap();
        this.DescriptionMap = eventsController.getBusinessEventDescriptionMap();
        this.VenueNameMap = eventsController.getBusinessEventVenueNameMap();
        this.VenueAddressMap = eventsController.getBusinessEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getBusinessEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildNightlifeEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getNightlifeEventTitleMap();
        this.ImageMap = eventsController.getNightlifeEventImageMap();
        this.DescriptionMap = eventsController.getNightlifeEventDescriptionMap();
        this.VenueNameMap = eventsController.getNightlifeEventVenueNameMap();
        this.VenueAddressMap = eventsController.getNightlifeEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getNightlifeEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);    
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildUniversityEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getUniversityEventTitleMap();
        this.ImageMap = eventsController.getUniversityEventImageMap();
        this.DescriptionMap = eventsController.getUniversityEventDescriptionMap();
        this.VenueNameMap = eventsController.getUniversityEventVenueNameMap();
        this.VenueAddressMap = eventsController.getUniversityEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getUniversityEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);   
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildMeetupEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getMeetupEventTitleMap();
        this.ImageMap = eventsController.getMeetupEventImageMap();
        this.DescriptionMap = eventsController.getMeetupEventDescriptionMap();
        this.VenueNameMap = eventsController.getMeetupEventVenueNameMap();
        this.VenueAddressMap = eventsController.getMeetupEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getMeetupEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildRecreationEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getRecreationEventTitleMap();
        this.ImageMap = eventsController.getRecreationEventImageMap();
        this.DescriptionMap = eventsController.getRecreationEventDescriptionMap();
        this.VenueNameMap = eventsController.getRecreationEventVenueNameMap();
        this.VenueAddressMap = eventsController.getRecreationEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getRecreationEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);  
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildPerformingArtsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getPerformingArtsEventTitleMap();
        this.ImageMap = eventsController.getPerformingArtsEventImageMap();
        this.DescriptionMap = eventsController.getPerformingArtsEventDescriptionMap();
        this.VenueNameMap = eventsController.getPerformingArtsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getPerformingArtsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getPerformingArtsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildPetsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getPetsEventTitleMap();
        this.ImageMap = eventsController.getPetsEventImageMap();
        this.DescriptionMap = eventsController.getPetsEventDescriptionMap();
        this.VenueNameMap = eventsController.getPetsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getPetsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getPetsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildPoliticsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getPoliticsEventTitleMap();
        this.ImageMap = eventsController.getPoliticsEventImageMap();
        this.DescriptionMap = eventsController.getPoliticsEventDescriptionMap();
        this.VenueNameMap = eventsController.getPoliticsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getPoliticsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getPoliticsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);   
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildRetailEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getRetailEventTitleMap();
        this.ImageMap = eventsController.getRetailEventImageMap();
        this.DescriptionMap = eventsController.getRetailEventDescriptionMap();
        this.VenueNameMap = eventsController.getRetailEventVenueNameMap();
        this.VenueAddressMap = eventsController.getRetailEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getRetailEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildScienceEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getScienceEventTitleMap();
        this.ImageMap = eventsController.getScienceEventImageMap();
        this.DescriptionMap = eventsController.getScienceEventDescriptionMap();
        this.VenueNameMap = eventsController.getScienceEventVenueNameMap();
        this.VenueAddressMap = eventsController.getScienceEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getScienceEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildReligionEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getReligionEventTitleMap();
        this.ImageMap = eventsController.getReligionEventImageMap();
        this.DescriptionMap = eventsController.getReligionEventDescriptionMap();
        this.VenueNameMap = eventsController.getReligionEventVenueNameMap();
        this.VenueAddressMap = eventsController.getReligionEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getReligionEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);    
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildSportsEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getSportsEventTitleMap();
        this.ImageMap = eventsController.getSportsEventImageMap();
        this.DescriptionMap = eventsController.getSportsEventDescriptionMap();
        this.VenueNameMap = eventsController.getSportsEventVenueNameMap();
        this.VenueAddressMap = eventsController.getSportsEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getSportsEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);    
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildTechnologyEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getTechnologyEventTitleMap();
        this.ImageMap = eventsController.getTechnologyEventImageMap();
        this.DescriptionMap = eventsController.getTechnologyEventDescriptionMap();
        this.VenueNameMap = eventsController.getTechnologyEventVenueNameMap();
        this.VenueAddressMap = eventsController.getTechnologyEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getTechnologyEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane); 
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildOthersEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout with the width and size of the
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.TitleMap = eventsController.getOthersEventTitleMap();
        this.ImageMap = eventsController.getOthersEventImageMap();
        this.DescriptionMap = eventsController.getOthersEventDescriptionMap();
        this.VenueNameMap = eventsController.getOthersEventVenueNameMap();
        this.VenueAddressMap = eventsController.getOthersEventVenueAddressMap();
        this.VenueUrlMap = eventsController.getOthersEventUrlMap();
        for (int i = 0; i < this.TitleMap.size(); i++) {
            title = this.TitleMap.get(i);
            image = this.ImageMap.get(title);
            description = this.DescriptionMap.get(title);
            venueName = this.VenueNameMap.get(title);
            venueAddress = this.VenueAddressMap.get(title);
            venueUrl = this.VenueUrlMap.get(title);
            AnchorPane posterPane = buildEventPane(title, image, description, venueName, venueAddress, venueUrl, image);
            eventsFlowPane.getChildren().add(posterPane);  
        }
        eventsScrollPane.setContent(eventsFlowPane);
    }
    
    /**
     * loads the EventImageView and loads
     * the image views and labels
     * @param _category
     * @param _image
     */
    public AnchorPane buildEventPane(String _category, String _image, String _description, String _venueName, String _venueAddress, String _venueUrl, String _picture) throws JSONException, IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EventsView.class.getResource("/fxml/EventImageView.fxml"));
            AnchorPane posterView = loader.load();
            EventsImageView controller = loader.getController();
            controller.setEventName(_category);
            posterView.setOnMouseClicked((mouseEvent) -> {
                try {
                    EventImageClicked(_category, _picture, _description, _venueName, _venueAddress, _venueUrl);
                } catch (JSONException ex) {
                    Logger.getLogger(EventsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            if (_image == null) {
                _image = "https://i.imgur.com/QeEdrPk.jpg";
            }
            
            if(_image.startsWith("//")){
                _image = "https://i.imgur.com/QeEdrPk.jpg";
            }
            
            Image posterImage = new Image(_image);

            controller.setEventImage(posterImage);

            return posterView;
        } catch (IOException ex) {
            System.err.println("Failed to load");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * This method adds the categories to the listview
     */
    public void addCategoriesToList() throws IOException, JSONException {
        this.categoriesMap = eventsController.getCategoriesMap();
        for (int i = 0; i < this.categoriesMap.size(); i++) {
            this.categoriesListView.getItems().add((String) this.categoriesMap.get(i));
        }
        categoriesListView.getSelectionModel().select(0);
    }

    /**
     * When the user clicks on the image the scene transitions to the eventinfo scene
     * @param title
     * @param image
     * @param description
     * @param venueName
     * @param venueAddress
     * @param venueUrl
     * @throws JSONException 
     */
    private void EventImageClicked(String title, String image, String description, String venueName, String venueAddress, String venueUrl) throws JSONException {
        // Request main application to set new scene to hand over control to the event info controller
        Main.transitionToEventInfoView(title, image, description, venueName, venueAddress, venueUrl);
    }

    /**
     * When the user clicks on an event the events are added to the FlowPane
     * @param oldIndex
     * @param newIndex
     * @throws JSONException
     * @throws IOException
     */
    private void EventTypeSelectionChanged(int oldIndex, int newIndex) throws JSONException, IOException {
        if (newIndex >= 0 && (newIndex != oldIndex)) {

            if (eventsFlowPane != null) {
                eventsFlowPane.getChildren().clear();
            }

            // switch on the selected index and place a request to fetch that type of event data
            switch (newIndex) {
                case 0:
                    this.buildMusicEventsFlowPane();
                    break;

                case 1:
                    this.buildConferenceEventsFlowPane();
                    break;

                case 2:
                    this.buildComedyEventsFlowPane();
                    break;

                case 3:
                    this.buildEducationEventsFlowPane();;
                    break;
                case 4:
                    this.buildFamilyEventsFlowPane();
                    break;
                    
                case 5:
                    this.buildFestivalsEventsFlowPane();
                    break;
                    
                case 6:
                    this.buildFilmEventsFlowPane();
                    break;
                    
                case 7:
                    this.buildFoodandWineEventsFlowPane();
                    break;
                    
                case 8:
                    this.buildCharityEventsFlowPane();
                    break;
                    
                case 9:
                    this.buildExhibitsEventsFlowPane();
                    break;
                    
                case 10:
                    this.buildHealthEventsFlowPane();
                    break;
                    
                case 11:
                    this.buildHolidayEventsFlowPane();
                    break;
                    
                case 12:
                    this.buildBooksEventsFlowPane();
                    break;
                    
                case 13:
                    this.buildAttractionsEventsFlowPane();
                    break;
                    
                case 14:
                    this.buildNeighborhoodEventsFlowPane();
                    break;
                    
                case 15:
                    this.buildBusinessEventsFlowPane();
                    break;
                    
                case 16:
                    this.buildNightlifeEventsFlowPane();
                    break;
                    
                case 17:
                    this.buildUniversityEventsFlowPane();
                    break;
                    
                case 18:
                    this.buildMeetupEventsFlowPane();
                    break;
                    
                case 19:
                    this.buildRecreationEventsFlowPane();
                    break;
                    
                case 20:
                    this.buildPerformingArtsEventsFlowPane();
                    break;
                    
                case 21:
                    this.buildPetsEventsFlowPane();
                    break;
                    
                case 22:
                    this.buildPoliticsEventsFlowPane();
                    break;
                    
                case 23:
                    this.buildRetailEventsFlowPane();
                    break;
                    
                case 24:
                    this.buildScienceEventsFlowPane();
                    break;
                    
                case 25:
                    this.buildReligionEventsFlowPane();
                    break;
                    
                case 26:
                    this.buildSportsEventsFlowPane();
                    break;
                    
                case 27:
                    this.buildTechnologyEventsFlowPane();
                    break;
                 
                case 28:
                    this.buildOthersEventsFlowPane();
                    break;
                    
                default:
                    break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriesListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                EventTypeSelectionChanged(oldValue.intValue(), newValue.intValue());
            } catch (JSONException ex) {
                Logger.getLogger(EventsView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EventsView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            addCategoriesToList();
        } catch (IOException ex) {
            Logger.getLogger(EventsView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(EventsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Takes the user back to the HomePage view
     * @param _event
     * @throws IOException 
     */
    @FXML
    public void backToHomePageButtonClick(ActionEvent _event) throws IOException {
        HomePageView view = this.handler.switchScenes(_event, "fxml/HomePage.fxml").getController();
    }
}
