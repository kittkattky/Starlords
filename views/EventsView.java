package views;

/* Main view class for the events. 
 * Displays the events and allows
 * the user to select an event
 *
 * @author Kahlie
 * @date 5/5/20
 */
import controllers.EventsController;
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
import javafx.event.Event;
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
    
    @FXML
    private FlowPane eventsFlowPane;
    
    protected EventHandlers handler = new EventHandlers();

    protected static String title;
    protected static String venueName;
    protected static String description;
    protected static String venueUrl;
    protected static String venueAddress;
    protected static String image;
    
    protected Map<Integer, String> categoriesMap;
    
    protected Map<Integer, String> titleMap;
    protected Map<String, String> imageMap;
    protected Map<String, String> venueNameMap;
    protected Map<String, String> descriptionMap;
    protected Map<String, String> venueUrlMap;
    protected Map<String, String> venueAddressMap;

    public EventsController eventsController = new EventsController();

    /**
     * Builds the flow pane by populating with image views.
     * @throws JSONException
     * @throws IOException 
     */
    private void buildComedyEventsFlowPane() throws JSONException, IOException {

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = this.eventsController.getComedyEventTitleMap();
        this.imageMap = this.eventsController.getComedyEventImageMap();
        this.venueNameMap = this.eventsController.getComedyEventVenueNameMap();
        this.descriptionMap = this.eventsController.getComedyEventDescriptionMap();
        this.venueAddressMap = this.eventsController.getComedyEventVenueAddressMap();
        this.venueUrlMap = this.eventsController.getComedyEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = this.eventsController.getMusicEventTitleMap();
        this.imageMap = this.eventsController.getMusicEventImageMap();
        this.descriptionMap = this.eventsController.getMusicEventDescriptionMap();
        this.venueNameMap = this.eventsController.getMusicEventVenueNameMap();
        this.venueAddressMap = this.eventsController.getMusicEventVenueAddressMap();
        this.venueUrlMap = this.eventsController.getMusicEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getConferenceEventTitleMap();
        this.imageMap = eventsController.getConferenceEventImageMap();
        this.descriptionMap = eventsController.getConferenceEventDescriptionMap();
        this.venueNameMap = eventsController.getConferenceEventVenueNameMap();
        this.venueAddressMap = eventsController.getConferenceEventVenueAddressMap();
        this.venueUrlMap = eventsController.getConferenceEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getEducationEventTitleMap();
        this.imageMap = eventsController.getEducationEventImageMap();
        this.descriptionMap = eventsController.getEducationEventDescriptionMap();
        this.venueNameMap = eventsController.getEducationEventVenueNameMap();
        this.venueAddressMap = eventsController.getEducationEventVenueAddressMap();
        this.venueUrlMap = eventsController.getEducationEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getFamilyEventTitleMap();
        this.imageMap = eventsController.getFamilyEventImageMap();
        this.descriptionMap = eventsController.getFamilyEventDescriptionMap();
        this.venueNameMap = eventsController.getFamilyEventVenueNameMap();
        this.venueAddressMap = eventsController.getFamilyEventVenueAddressMap();
        this.venueUrlMap = eventsController.getFamilyEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getFestivalsEventTitleMap();
        this.imageMap = eventsController.getFestivalsEventImageMap();
        this.descriptionMap = eventsController.getFestivalsEventDescriptionMap();
        this.venueNameMap = eventsController.getFestivalsEventVenueNameMap();
        this.venueAddressMap = eventsController.getFestivalsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getFestivalsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getFilmEventTitleMap();
        this.imageMap = eventsController.getFilmEventImageMap();
        this.descriptionMap = eventsController.getFilmEventDescriptionMap();
        this.venueNameMap = eventsController.getFilmEventVenueNameMap();
        this.venueAddressMap = eventsController.getFilmEventVenueAddressMap();
        this.venueUrlMap = eventsController.getFilmEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getFoodandWineEventTitleMap();
        this.imageMap = eventsController.getFoodandWineEventImageMap();
        this.descriptionMap = eventsController.getFoodandWineEventDescriptionMap();
        this.venueNameMap = eventsController.getFoodandWineEventVenueNameMap();
        this.venueAddressMap = eventsController.getFoodandWineEventVenueAddressMap();
        this.venueUrlMap = eventsController.getFoodandWineEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getCharityEventTitleMap();
        this.imageMap = eventsController.getCharityEventImageMap();
        this.descriptionMap = eventsController.getCharityEventDescriptionMap();
        this.venueNameMap = eventsController.getCharityEventVenueNameMap();
        this.venueAddressMap = eventsController.getCharityEventVenueAddressMap();
        this.venueUrlMap = eventsController.getCharityEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getExhibitsEventTitleMap();
        this.imageMap = eventsController.getExhibitsEventImageMap();
        this.descriptionMap = eventsController.getExhibitsEventDescriptionMap();
        this.venueNameMap = eventsController.getExhibitsEventVenueNameMap();
        this.venueAddressMap = eventsController.getExhibitsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getExhibitsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout 
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getHealthEventTitleMap();
        this.imageMap = eventsController.getHealthEventImageMap();
        this.descriptionMap = eventsController.getHealthEventDescriptionMap();
        this.venueNameMap = eventsController.getHealthEventVenueNameMap();
        this.venueAddressMap = eventsController.getHealthEventVenueAddressMap();
        this.venueUrlMap = eventsController.getHealthEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout 
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getHolidayEventTitleMap();
        this.imageMap = eventsController.getHolidayEventImageMap();
        this.descriptionMap = eventsController.getHolidayEventDescriptionMap();
        this.venueNameMap = eventsController.getHolidayEventVenueNameMap();
        this.venueAddressMap = eventsController.getHolidayEventVenueAddressMap();
        this.venueUrlMap = eventsController.getHolidayEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout 
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getBooksEventTitleMap();
        this.imageMap = eventsController.getBooksEventImageMap();
        this.descriptionMap = eventsController.getBooksEventDescriptionMap();
        this.venueNameMap = eventsController.getBooksEventVenueNameMap();
        this.venueAddressMap = eventsController.getBooksEventVenueAddressMap();
        this.venueUrlMap = eventsController.getBooksEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getAttractionsEventTitleMap();
        this.imageMap = eventsController.getAttractionsEventImageMap();
        this.descriptionMap = eventsController.getAttractionsEventDescriptionMap();
        this.venueNameMap = eventsController.getAttractionsEventVenueNameMap();
        this.venueAddressMap = eventsController.getAttractionsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getAttractionsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getNeighborhoodEventTitleMap();
        this.imageMap = eventsController.getNeighborhoodEventImageMap();
        this.descriptionMap = eventsController.getNeighborhoodEventDescriptionMap();
        this.venueNameMap = eventsController.getNeighborhoodEventVenueNameMap();
        this.venueAddressMap = eventsController.getNeighborhoodEventVenueAddressMap();
        this.venueUrlMap = eventsController.getNeighborhoodEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getBusinessEventTitleMap();
        this.imageMap = eventsController.getBusinessEventImageMap();
        this.descriptionMap = eventsController.getBusinessEventDescriptionMap();
        this.venueNameMap = eventsController.getBusinessEventVenueNameMap();
        this.venueAddressMap = eventsController.getBusinessEventVenueAddressMap();
        this.venueUrlMap = eventsController.getBusinessEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getNightlifeEventTitleMap();
        this.imageMap = eventsController.getNightlifeEventImageMap();
        this.descriptionMap = eventsController.getNightlifeEventDescriptionMap();
        this.venueNameMap = eventsController.getNightlifeEventVenueNameMap();
        this.venueAddressMap = eventsController.getNightlifeEventVenueAddressMap();
        this.venueUrlMap = eventsController.getNightlifeEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getUniversityEventTitleMap();
        this.imageMap = eventsController.getUniversityEventImageMap();
        this.descriptionMap = eventsController.getUniversityEventDescriptionMap();
        this.venueNameMap = eventsController.getUniversityEventVenueNameMap();
        this.venueAddressMap = eventsController.getUniversityEventVenueAddressMap();
        this.venueUrlMap = eventsController.getUniversityEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getMeetupEventTitleMap();
        this.imageMap = eventsController.getMeetupEventImageMap();
        this.descriptionMap = eventsController.getMeetupEventDescriptionMap();
        this.venueNameMap = eventsController.getMeetupEventVenueNameMap();
        this.venueAddressMap = eventsController.getMeetupEventVenueAddressMap();
        this.venueUrlMap = eventsController.getMeetupEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getRecreationEventTitleMap();
        this.imageMap = eventsController.getRecreationEventImageMap();
        this.descriptionMap = eventsController.getRecreationEventDescriptionMap();
        this.venueNameMap = eventsController.getRecreationEventVenueNameMap();
        this.venueAddressMap = eventsController.getRecreationEventVenueAddressMap();
        this.venueUrlMap = eventsController.getRecreationEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getPerformingArtsEventTitleMap();
        this.imageMap = eventsController.getPerformingArtsEventImageMap();
        this.descriptionMap = eventsController.getPerformingArtsEventDescriptionMap();
        this.venueNameMap = eventsController.getPerformingArtsEventVenueNameMap();
        this.venueAddressMap = eventsController.getPerformingArtsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getPerformingArtsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getPetsEventTitleMap();
        this.imageMap = eventsController.getPetsEventImageMap();
        this.descriptionMap = eventsController.getPetsEventDescriptionMap();
        this.venueNameMap = eventsController.getPetsEventVenueNameMap();
        this.venueAddressMap = eventsController.getPetsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getPetsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getPoliticsEventTitleMap();
        this.imageMap = eventsController.getPoliticsEventImageMap();
        this.descriptionMap = eventsController.getPoliticsEventDescriptionMap();
        this.venueNameMap = eventsController.getPoliticsEventVenueNameMap();
        this.venueAddressMap = eventsController.getPoliticsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getPoliticsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getRetailEventTitleMap();
        this.imageMap = eventsController.getRetailEventImageMap();
        this.descriptionMap = eventsController.getRetailEventDescriptionMap();
        this.venueNameMap = eventsController.getRetailEventVenueNameMap();
        this.venueAddressMap = eventsController.getRetailEventVenueAddressMap();
        this.venueUrlMap = eventsController.getRetailEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getScienceEventTitleMap();
        this.imageMap = eventsController.getScienceEventImageMap();
        this.descriptionMap = eventsController.getScienceEventDescriptionMap();
        this.venueNameMap = eventsController.getScienceEventVenueNameMap();
        this.venueAddressMap = eventsController.getScienceEventVenueAddressMap();
        this.venueUrlMap = eventsController.getScienceEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getReligionEventTitleMap();
        this.imageMap = eventsController.getReligionEventImageMap();
        this.descriptionMap = eventsController.getReligionEventDescriptionMap();
        this.venueNameMap = eventsController.getReligionEventVenueNameMap();
        this.venueAddressMap = eventsController.getReligionEventVenueAddressMap();
        this.venueUrlMap = eventsController.getReligionEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getSportsEventTitleMap();
        this.imageMap = eventsController.getSportsEventImageMap();
        this.descriptionMap = eventsController.getSportsEventDescriptionMap();
        this.venueNameMap = eventsController.getSportsEventVenueNameMap();
        this.venueAddressMap = eventsController.getSportsEventVenueAddressMap();
        this.venueUrlMap = eventsController.getSportsEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getTechnologyEventTitleMap();
        this.imageMap = eventsController.getTechnologyEventImageMap();
        this.descriptionMap = eventsController.getTechnologyEventDescriptionMap();
        this.venueNameMap = eventsController.getTechnologyEventVenueNameMap();
        this.venueAddressMap = eventsController.getTechnologyEventVenueAddressMap();
        this.venueUrlMap = eventsController.getTechnologyEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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

        // Build a flow pane layout
        eventsFlowPane = new FlowPane(Orientation.HORIZONTAL);
        eventsFlowPane.setHgap(50);
        eventsFlowPane.setVgap(10);
        eventsFlowPane.setPadding(new Insets(10, 8, 4, 8));
        // bind to scroll pane width
        eventsFlowPane.prefWrapLengthProperty().bind(eventsScrollPane.widthProperty());

        this.titleMap = eventsController.getOthersEventTitleMap();
        this.imageMap = eventsController.getOthersEventImageMap();
        this.descriptionMap = eventsController.getOthersEventDescriptionMap();
        this.venueNameMap = eventsController.getOthersEventVenueNameMap();
        this.venueAddressMap = eventsController.getOthersEventVenueAddressMap();
        this.venueUrlMap = eventsController.getOthersEventUrlMap();
        for (int i = 0; i < this.titleMap.size(); i++) {
            title = this.titleMap.get(i);
            image = this.imageMap.get(title);
            description = this.descriptionMap.get(title);
            venueName = this.venueNameMap.get(title);
            venueAddress = this.venueAddressMap.get(title);
            venueUrl = this.venueUrlMap.get(title);
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
            posterView.setOnMouseClicked((_mouseEvent) -> {
                try {
                    EventImageClicked(_category, _picture, _description, _venueName, _venueAddress, _venueUrl, _mouseEvent);
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
        this.categoriesMap = this.eventsController.getCategoriesMap();
        for (int i = 0; i < this.categoriesMap.size(); i++) {
            this.categoriesListView.getItems().add((String) this.categoriesMap.get(i));
        }
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
    private void EventImageClicked(String _title, String _image, String _description, String _venueName, String _venueAddress, String _venueUrl, Event _event) throws JSONException {
        // Request main application to set new scene to hand over control to the event info controller
        Main.transitionToEventInfoView(this.eventsController.uuidController.getUUID(), _title, _image, _description, _venueName, _venueAddress, _venueUrl);
    }

    /**
     * When the user clicks on an event the events are added to the FlowPane
     * @param oldIndex
     * @param newIndex
     * @throws JSONException
     * @throws IOException
     */
    private void EventTypeSelectionChanged(int _oldIndex, int _newIndex) throws JSONException, IOException {
        if (_newIndex >= 0 && (_newIndex != _oldIndex)) {

            if (eventsFlowPane != null) {
                eventsFlowPane.getChildren().clear();
            }

            // switch on the selected index and place a request to fetch that type of event data
            switch (_newIndex) {
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
    public void initialize(URL _url, ResourceBundle _rb) {
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
        view.handler.uuidController.setUUID(this.eventsController.uuidController.getUUID());
    }
}
