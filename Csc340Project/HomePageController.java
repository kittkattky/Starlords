package Csc340Project;

/**
 * This class is the home page
 *
 * @author Kahlie Last Updated: 2/21/2020
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class HomePageController implements Initializable {

    
    
    @FXML
    private Label test;
    
    
    
    
    ApiPractice api = new ApiPractice();
    JSONObject res = api.getRes();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        test.setText(res.toString());
        
    }    
    
    
    
    
}
