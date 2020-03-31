package restaurantui;

/**
 * Controller that interacts with view and model
 *
 * @author Diego Rodriguez Last Updated: 3/31/2020
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import RestaurantV3.RestaurantModel;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RestaurantViewController implements Initializable {

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<String> listViewCuisineList;
    
    @FXML 
    private ListView<String> listViewRestaurantList;

    protected boolean isSearched = false;
    protected boolean isSelected = false;
    protected RestaurantModel restaurantModel = new RestaurantModel();
    protected RestaurantListController restaurantListController = new RestaurantListController();
    private final double LAT = 36.066984;
    private final double LON = -79.800178;


    /**
     * When the search for cuisines button is clicked, this method handles that
     * action event
     *
     * @param event
     */
    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        System.out.println("The search button has been clicked");
        this.isSearched = true;
        addCuisinesToList();
    }

    /**
     * This method is called when the user clicks the "Search for Cuisines"
     * button. A call to the api is made through the restaurant model, and the
     * information from the api is returned as a map. Each element from the map
     * is cycled through and added to the listView.
     */
    public void addCuisinesToList() {
        this.restaurantModel = RestaurantModel.loadCuisinesByLocation(LAT, LON);
        for (int i = 0; i < this.restaurantModel.getCuisineMap().size(); i++) {
            this.listViewCuisineList.getItems().add((String) this.restaurantModel.getCuisineMap().get(i));
        }
    }
    
    public void addRestaurantsToList() {
        System.out.println(this.restaurantModel.getCuisineID());
        this.restaurantModel = this.restaurantModel.loadRestaurantsByID(this.restaurantModel.getCuisineID(), LAT, LON);
        System.out.println(this.restaurantModel.getRestaurantNameMap().get(0));
        for (int i = 0; i < this.restaurantModel.getRestaurantNameMap().size(); i++) {
            System.out.println("marker1");
            this.listViewRestaurantList.getItems().add((String) this.restaurantModel.getRestaurantNameMap().get(i));
        }
    }

    /**
     * This method is called when the user clicks the confirm button.
     *
     * The Cuisine ID Map within the restaurant model object was populated upon
     * the initial call of the "loadCusinesByLocation" method, it contains all
     * cuisines (key) and cuisine IDs (value).
     *
     * Thus the ID of the cuisine chosen by the user can be found by passing it
     * as a key to the cuisine ID Map.
     *
     * The id of the cuisine is set through the restaurant model using a set
     * method, then the a new scene is set on the stage.
     * @param _event
     * @param event
     * @throws java.io.IOException
     */
    @FXML
    public void selectCuisine(ActionEvent _event) throws IOException {
        if (!isSearched) {
            errorLabel.setText("Please search for cuisines near you");
        }
        else {
            if (!isSelected) {
                errorLabel.setText("Please select a cuisine");
            }
            else {
                String cuisineSelected = listViewCuisineList.getSelectionModel().getSelectedItem();
                this.restaurantModel.setID((int) this.restaurantModel.getCuisineIDMap().get(cuisineSelected));
                System.out.println("Cuisine: " + cuisineSelected + "\n" + "ID:" + this.restaurantModel.getCuisineID());
                
                //pass this instance to the next controller.
                //this.restaurantListController.setRestaurantModel(this.restaurantModel);

                //this.restaurantListController.changeScene(_event);
                switchScenes(_event, "RestaurantList.fxml");
                //addRestaurantsToList();

            }
        }
    }

    /**
     * When an area of list view is clicked, meaning the user selects a cuisine,
     * this method sets isSelected to true.
     */
    public void isSelected() {
        if (!isSearched) {
            errorLabel.setText("Please search for cuisines near you");
        }
        if (isSearched) {
            this.isSelected = true;
        }
    }

    
    /**
     * Helper method for switching scenes. Need to specify FXML file to create new scene
     * and the event from which to create a new stage from. 
     * @param event
     * @param fxmlFileName
     * @throws IOException 
     */
    public void switchScenes(ActionEvent event, String fxmlFileName) throws IOException {
        Parent parentUsingFXML = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene sceneToSwitchTo = new Scene(parentUsingFXML);
        
        //Get the reference to the window that the event is taking place in.
        Stage referenceStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        referenceStage.setScene(sceneToSwitchTo);
        referenceStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}


