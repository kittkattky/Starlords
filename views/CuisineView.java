package views;

/**
 * View that interacts with CuisineListUI.FXML and Restaurant Controller
 *
 * @author Diego Rodriguez Last Updated: 4/4/2020
 */
import controllers.RestaurantController;
import controllers.UUIDController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import utilities.Homepage.EventHandlers;

public class CuisineView implements Initializable {

    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected Label errorLabel;

    @FXML
    protected ListView<String> listViewCuisineList;

    protected boolean isSearched = false;
    protected boolean isSelected = false;
    public RestaurantController restaurantController = new RestaurantController();
    public UUIDController uuidController = new UUIDController();
    protected EventHandlers handler = new EventHandlers();

    protected Map cuisineMap;
    protected Map cuisineIDMap;

    /**
     * When the search button is clicked, this method handles the event.
     *
     * @param event
     */
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws Exception {
        this.isSearched = true;
        addCuisinesToList();
    }

    /**
     * This method is called when the user clicks the "Search for Cuisines"
     * button. A call to the api is made through the restaurant model, and the
     * information from the api is returned as a map. Each element from the map
     * is cycled through and added to the listView.
     *
     * @throws java.lang.Exception
     */
    public void addCuisinesToList() throws Exception {
        this.cuisineMap = restaurantController.getCuisineMap();
        for (int i = 0; i < this.cuisineMap.size(); i++) {
            this.listViewCuisineList.getItems().add((String) this.cuisineMap.get(i));
        }
    }

    /**
     * This method is called when the user clicks the confirm button. Checks are
     * run to ensure the correct sequence of user inputs is achieved. The
     * cuisine ID that corresponds to the cuisine selected by the user is saved.
     * Then that cuisine ID is used to pre load a information on the next scene.
     *
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    public void selectCuisine(ActionEvent _event) throws IOException, Exception {
        if (!this.isSearched) {
            this.errorLabel.setText("Please search for cuisines near you");
        } else {
            if (!this.isSelected) {
                this.errorLabel.setText("Please select a cuisine");
            } else {
                String cuisineSelected = this.listViewCuisineList.getSelectionModel().getSelectedItem();
                this.restaurantController.setCuisineID((Integer) this.restaurantController.getCuisineIDMap().get(cuisineSelected));

                /**
                 * These next lines are pre loading the restaurant list onto the
                 * next scene. We can create an instance of the view that
                 * corresponds with the scene we are switching to by using the
                 * loader. From there we can alter items on the next scene
                 * before its shown by calling methods from the view instance.
                 * This allows us to use information from this scene to set the
                 * next scene.
                 */
                RestaurantListView view = this.handler.switchScenes(_event, "fxml/RestaurantList.fxml").getController();
                //Set the UUID in the instance of the restaurant controller created in the RestaurantListView
                view.restaurantController.uuidController.setUUID(this.restaurantController.uuidController.getUUID());
                view.addRestaurantsToList(this.restaurantController.getCuisineID());
                view.setCuisineLabel(cuisineSelected);
            }
        }
    }

    /**
     * Switch back to home page when button is pressed. 
     * @param _event
     * @throws IOException 
     */
    public void homeButton(ActionEvent _event) throws IOException {
        HomePageView view = this.handler.switchScenes(_event, "fxml/HomePage.fxml").getController();
        view.handler.uuidController.setUUID(this.restaurantController.uuidController.getUUID());
        view.setHomePageText("Select an option");
        
    }


    /**
     * When an area of list view is clicked, meaning the user selects a cuisine,
     * this method sets isSelected to true.
     */
    public void isSelected() {
        if (!this.isSearched) {
            this.errorLabel.setText("Please search for cuisines near you");
        }
        if (this.isSearched) {
            this.isSelected = true;
        }
    }

    /**
     * This method sets the gradient for the anchor pane
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stop[] stops = new Stop[]{new Stop(0, Color.web("#5C258D")), new Stop(1, Color.web("#4389A2"))};
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill fillBackground = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        this.anchorPane.setBackground(new Background(fillBackground));

    }

}
