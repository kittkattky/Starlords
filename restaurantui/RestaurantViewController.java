package restaurantui;

/**
 * Controller that interacts with view and model
 *
 * @author Diego Rodriguez Last Updated: 3/26/2020
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
    private Label labelCuisinesNearYou;

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<String> listViewCuisineList;

    protected boolean isSearched = false;
    protected boolean isSelected = false;
    protected RestaurantModel restaurantModel = new RestaurantModel();

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
        double lat = 36.066984;
        double lon = -79.800178;
        this.restaurantModel = this.restaurantModel.loadCuisinesByLocation(lat, lon);
        for (int i = 0; i < this.restaurantModel.getCuisineMap().size(); i++) {
            this.listViewCuisineList.getItems().add((String) this.restaurantModel.getCuisineMap().get(i));
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
     */
    @FXML
    public void selectCuisine(ActionEvent event) throws IOException {
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
                System.out.println("Cuisine: " + cuisineSelected + "\n" + "ID:" + this.restaurantModel.getCusineID());

                Parent restaurantListParent = FXMLLoader.load(getClass().getResource("RestaurantList.fxml"));
                Scene restaurantListScene = new Scene(restaurantListParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(restaurantListScene);
                window.show();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
