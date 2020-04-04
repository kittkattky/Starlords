package restaurantui;

/**
 * View in charge of RestaurantList.FXML and interacts with controller and can
 * switch to CuisineView 
 * Author: Diego Rodriguez 
 * Last Updated: 4/4/2020
 */
import java.io.IOException;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class RestaurantListView {

    @FXML
    protected Label cuisineLabel;
    @FXML
    protected Label restaurantNameLabel;
    @FXML
    protected Label restaurantRatingLabel;
    @FXML
    protected Label restaurantAddressLabel;
    @FXML
    protected Hyperlink restaurantURL;
    @FXML
    protected ListView<String> listViewRestaurantList;

    private RestaurantController restaurantController = new RestaurantController();
    protected Map restaurantNameMap;
    protected Map restaurantAddressMap;
    protected Map restaurantURLMap;
    protected Map restaurantRatingMap;

    /**
     * This add all the restaurants to the list view, as well as sets the maps
     * for all restaurant information that needs to be shown for each
     * restaurant.
     *
     * @param _cuisineID
     */
    public void addRestaurantsToList(int _cuisineID) {
        this.restaurantController.setCuisineID(_cuisineID);
        this.restaurantNameMap = this.restaurantController.getRestaurantNameMap();
        this.restaurantAddressMap = this.restaurantController.getRestaurantAddressMap();
        this.restaurantURLMap = this.restaurantController.getRestaurantURLMap();
        this.restaurantRatingMap = this.restaurantController.getRestaurantRatingMap();

        for (int i = 0; i < this.restaurantNameMap.size(); i++) {
            this.listViewRestaurantList.getItems().add((String) this.restaurantNameMap.get(i));
        }
    }

    /**
     * This method switches back to the cuisine list view. Before showing the
     * scene, the cuisine list is pre loaded so it doesn't need to be searched
     * again.
     *
     * @param _event
     * @throws IOException
     */
    public void backToCuisineScene(ActionEvent _event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CuisineListUI.FXML"));
        Parent parentUsingFXML = loader.load();

        //preload cuisine list
        CuisineView view = loader.getController();
        view.addCuisinesToList();
        view.isSearched = true;

        Scene sceneToSwitchTo = new Scene(parentUsingFXML);
        Stage referenceStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        referenceStage.setScene(sceneToSwitchTo);
        referenceStage.show();
    }

    
    //=================  SETTERS ===============
    
    public void setCuisineLabel(String _cuisineChoice) {
        this.cuisineLabel.setText(_cuisineChoice);
    }

    /**
     * This method sets all labels and the url when an item is selected from the
     * list view.
     */
    public void setRestaurantLabels() {
        String restaurantName = this.listViewRestaurantList.getSelectionModel().getSelectedItem();
        this.restaurantNameLabel.setText(restaurantName);

        this.restaurantRatingLabel.setText((String) restaurantRatingMap.get(restaurantName));
        this.restaurantAddressLabel.setText((String) restaurantAddressMap.get(restaurantName));
        this.restaurantURL.setText((String) restaurantURLMap.get(restaurantName));
    }

}
