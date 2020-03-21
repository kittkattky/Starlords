/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import RestaurantV3.RestaurantModel;
/**
 *
 * @author darod
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ListView<String> listView;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("The button has been clicked");
        addItemsToList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void addItemsToList(){
        double lat = 36.066984;
        double lon = -79.800178;
        RestaurantModel modelA = new RestaurantModel();
        modelA = modelA.loadCusinesByLocation(lat, lon);

        for(int i = 0; i < modelA.getCuisineMap().size(); i++) {
            listView.getItems().add((String)modelA.getCuisineMap().get(i));
        }
    }
    
}
