package views;

/**
 * This view handles all elements on the Homepage scene
 *
 * @author Diego Rodriguez Last Updated: 4/18/20
 */
import controllers.AccountController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private ImageView calendarPic;

    @FXML
    private Label nameLabel;

    protected String uuid;

    protected AccountController homePageController = new AccountController();
    
    /**
     * Helper method for switching scenes
     * @param _event
     * @param fxml
     * @return
     * @throws IOException 
     */
    public FXMLLoader switchScenes(MouseEvent _event, String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        Parent parentUsingFXML = loader.load();
        Scene sceneToSwitchTo = new Scene(parentUsingFXML);
        Stage referenceStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        referenceStage.setScene(sceneToSwitchTo);
        referenceStage.show();
        return loader;

    }

    /**
     * This method is inherited from the Java interface "Initializable" and is always called when Scene is shown.
     * It adds eventHandlers to all areas of scene where the user can click, and handles the events accordingly.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.restaurantPic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent _event) {
                System.out.println("picture clicked");
                CuisineView view;
                try {
                    view = switchScenes(_event, "fxml/CuisineListUI.fxml").getController();
                    view.passUUIDtoController(getUUID());
                } catch (IOException ex) {
                    Logger.getLogger(HomePageView.class.getName()).log(Level.SEVERE, null, ex);
                }
                _event.consume();
            }
        });
    }

    //=================  GETTERS =============== 
    public String getUUID() {
        return this.uuid;
    }

    
    //=================  SETTERS ===============
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }

}
