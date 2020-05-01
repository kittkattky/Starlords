package views;

/**
 * This view handles all events that occur on the Login page scene
 * All request to the database are routed through the Login_SignUpController
 *
 * @author Kahlie/Diego Rodriguez Updated: 4/24/2020
 */
import controllers.AccountController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInPageView implements Initializable {

 
    @FXML
    private Label labelError;
    
    @FXML
    private Label logOutLabel;

    @FXML
    private TextField email;


    @FXML
    private TextField password;


    @FXML
    private Button signIn;


    @FXML
    private Button signUp;
    

    protected AccountController loginController = new AccountController();

    /**
     * This method will switch scenes depending on the result of the logIn() method.
     * Also this method passes the UUID corresponding with a successful login attempt to the view of the next scene.
     * @param _event 
     */
    @FXML
    public void handleSignInButtonAction(ActionEvent _event) {

        if (_event.getSource() == signIn) {
            if (logIn()) {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/HomePage.fxml"));
                    //loader must be loaded before trying to use the view from the next scene.
                    Parent parent = loader.load();
                    //create referece to view being used in the next scene and set the uuid within that view's controller equal to the one that matches the users login.
                    HomePageView view = loader.getController();
                    view.handler.uuidController.setUUID(this.loginController.uuidController.getUUID());
                    String firstName = ((this.loginController.sendQueryRequest("firstname") == null) ? "" : this.loginController.sendQueryRequest("firstname"));
                    view.setHomePageText("Welcome " + firstName);
                    Stage stage = (Stage)((Node) _event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(parent);
                    
                    stage.setScene(scene);
                    stage.show();


                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
            else
                labelError.setText("Incorrect Username/Password");
        }
    }

    /**
     * If the user clicks the signUp button go the the sign up page
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    public void handleSignUpButtonAction(ActionEvent _event) throws IOException {

        if (_event.getSource() == signUp) {

            Node node = (Node) _event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SignUpPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Method checks if the username and password match with the data from the
     * database
     *
     * @return
     */
    private boolean logIn() {
        String userEmail = this.email.getText();
        String userPassword = this.password.getText();
        this.logOutLabel.setText("");
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            labelError.setText("Please enter email/password");
            return false;
        } else {
            this.loginController.uuidController.setUUID(this.loginController.sendVerificationRequest(userEmail, userPassword));
            if (this.loginController.uuidController.getUUID() == null) {
                return false;
            } else {
                return true;
            }
        }

    }
    
    public void setLogOutLabel(String _message) {
        this.logOutLabel.setText(_message);
    }
}
