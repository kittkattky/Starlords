package views;

/**
 * View in charge of RestaurantList.FXML and interacts with controller and can
 * switch to CuisineView Author: Diego Rodriguez Last Updated: 4/4/2020
 */
import controllers.RestaurantController;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class RestaurantListView implements Initializable {

    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected Label cuisineLabel;
    @FXML
    protected Label restaurantNameLabel;
    @FXML
    protected Label restaurantRatingLabel;
    @FXML
    protected Label restaurantAddressLabel;
    @FXML
    protected Label errorLabel;
    @FXML
    protected Hyperlink restaurantURL;
    @FXML
    protected ListView<String> listViewRestaurantList;

    protected RestaurantController restaurantController = new RestaurantController();
    protected Map restaurantNameMap;
    protected Map restaurantAddressMap;
    protected Map restaurantURLMap;
    protected Map restaurantRatingMap;
    protected boolean isHyperLinkSet = false;

    /**
     * This add all the restaurants to the list view, as well as sets the maps
     * for all restaurant information that needs to be shown for each
     * restaurant.
     *
     * @param _cuisineID
     * @throws java.lang.Exception
     */
    public void addRestaurantsToList(int _cuisineID) throws Exception {
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
    public void backToCuisineScene(ActionEvent _event) throws IOException, Exception {
        //set to false when switching back to cuisine scene
        this.isHyperLinkSet = false;

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/CuisineListUI.fxml"));
        Parent parentUsingFXML = loader.load();

        //set UUID for restaurant controller in next view and preload cuisine list
        CuisineView view = loader.getController();
       
        view.restaurantController.setUUID(this.restaurantController.getUUID());
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
        this.isHyperLinkSet = true;
    }

    /**
     * This method allows the hyperlink to be opened using the desktop's default
     * web browser
     *
     * @param _hyperLinkedClicked
     * @throws URISyntaxException
     */
    public void openURL(ActionEvent _hyperLinkedClicked) throws URISyntaxException {
        //first check if a restaurant has been selected
        if (this.isHyperLinkSet) {
            //then check if this class AND if this action are supported on this the current platform.
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    //call the default desktop browser and display uri.
                    Desktop.getDesktop().browse(new URI(this.restaurantURL.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(RestaurantListView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            this.errorLabel.setText("Please select a restaurant");
        }

    }

    /**
     * This method sets the gradient for an anchor pane.
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
