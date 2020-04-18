package views;

/**
 * This class is the home page
 *
 * @author Kahlie Last Updated: 3/20/2020
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageView implements Initializable {

    @FXML
    private ImageView eventPic;
    
    @FXML
    private ImageView restaurantPic;
    
    @FXML
    private ImageView moviePic;
    

    
    protected String uuid;
    
    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException{

        if (event.getSource() == eventPic) {
            
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Events.fxml")));
                    stage.setScene(scene);
                    stage.show();

                    } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }
        
        else if (event.getSource() == restaurantPic) {
            System.out.println(this.uuid);
            
        }
    }
    
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}