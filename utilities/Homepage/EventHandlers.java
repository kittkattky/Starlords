package utilities.Homepage;

/**
 * Created to reduce redundancies on the homepage.
 * Also simplifies process of adding new transitions to homepage.
 * If a new transition needs to be added, create the handler here then simply
 * add it to an FX Object on the homepage view.
 *
 * @author Diego
 * @date 4/25/20
 */

import controllers.AccountController;
import controllers.EventsController;
import controllers.UUIDController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import views.*;


public class EventHandlers {

    private FXMLLoader loader;
    private Parent parent;
    private Scene sceneToSwitchTo;
    private Stage referenceStage;

    protected AccountController accountController = new AccountController();
    public UUIDController uuidController = new UUIDController();

    /**
     * Helper method for switching scenes
     *
     * @param _event
     * @param fxml
     * @return
     * @throws IOException
     */
    public FXMLLoader switchScenes(Event _event, String fxml) throws IOException {

        this.loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        this.parent = loader.load();
        this.sceneToSwitchTo = new Scene(this.parent);
        this.referenceStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        this.referenceStage.setScene(this.sceneToSwitchTo);
        this.referenceStage.show();
        return loader;

    }

    //================= HANDLERS =============== 
    public EventHandler<MouseEvent> createCuisineEventHandler() {
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent _event) {
                CuisineView view;
                try {
                    view = switchScenes(_event, "fxml/CuisineListUI.fxml").getController();
                    System.out.println("passing this UUID in cuisineEventhandler: " + getUUID());
                    view.restaurantController.uuidController.setUUID(getUUID());

                } catch (IOException ex) {
                    Logger.getLogger(HomePageView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return handler;
    }

    public EventHandler<MouseEvent> createMyAccountEventHandler() {
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent _event) {
                myAccountView view;
                try {
                    view = switchScenes(_event, "fxml/myAccount.fxml").getController();
                    view.myAccountController.uuidController.setUUID(getUUID());
                    loadUserAccountInfo(view);
                } catch (IOException ex) {
                    Logger.getLogger(HomePageView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return handler;
    }
    
    public EventHandler<MouseEvent> createGenreEventHandler() {
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent _event) {
                GenreView view;
                try {
                    view = switchScenes(_event, "fxml/GenreList.fxml").getController();
                    view.movieController.uuidController.setUUID(getUUID ());
                    System.out.println("passing this UUID in genreEventhandler: " + getUUID());
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass ().getName ()).log (Level.SEVERE, null, ex);
                }
            }
        };
        return handler;
    }

    public EventHandler<MouseEvent> createEventsEventHandler() {
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent _event) {
            EventsView view;
            try {
                view = switchScenes(_event, "fxml/EventsMainView.fxml").getController();
                System.out.println("passing this UUID in eventEventhandler: " + getUUID());
                view.eventsController.uuidController.setUUID(getUUID());
                view.eventsController.setZipCode();
            } catch (IOException ex) {
                Logger.getLogger(HomePageView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        };
        return handler;
    }   


    //================= GETTERS =============== 
    public String getUUID() {
        return this.uuidController.getUUID();
    }

    public void loadUserAccountInfo(myAccountView _view) {
        System.out.println("setting user info with: " + _view.myAccountController.uuidController.getUUID());
        String firstName = _view.myAccountController.sendQueryRequest("firstname");
        String lastName = _view.myAccountController.sendQueryRequest("lastname");
        String street = _view.myAccountController.sendQueryRequest("street");
        String city = _view.myAccountController.sendQueryRequest("city");
        String state = _view.myAccountController.sendQueryRequest("state");
        String zipCode = _view.myAccountController.sendQueryRequest("zipCode");
        String email = _view.myAccountController.sendQueryRequest("email");

        _view.setUserInfoLabels(firstName, lastName, street, city, state, zipCode, email);
    }   
}
