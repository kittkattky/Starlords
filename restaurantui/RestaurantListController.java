package restaurantui;

/**
 *
 * @author darod
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

public class RestaurantListController implements Initializable {
    
    protected RestaurantModel restaurantModel = new RestaurantModel();
    
    public void changeScene(ActionEvent _event) throws IOException {
        System.out.println("check1");
        Parent parentUsingFXML = FXMLLoader.load(getClass().getResource("RestaurantList.fxml"));
        System.out.println("check2");
        Scene sceneToSwitchTo = new Scene(parentUsingFXML);
        Stage referenceStage = (Stage)((Node)_event.getSource()).getScene().getWindow();
        referenceStage.setScene(sceneToSwitchTo);
        referenceStage.show();
    }
    
    public void setRestaurantModel(RestaurantModel _restaurantModel) {
        this.restaurantModel = _restaurantModel;
    }
    
    public void printCuisineID() {
        System.out.println(this.restaurantModel.getCuisineID());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
