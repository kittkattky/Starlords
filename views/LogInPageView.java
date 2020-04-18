package views;

/**
 * This class is the log in page which can be used to access the home page and
 * sign up page
 *
 * @author Kahlie Last Updated: 2/25/2020
 */
import controllers.Login_SignUpController;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LogInPageView implements Initializable {

    //A label that appears if an error occurs
    @FXML
    private Label labelError;

    //Username/email text field
    @FXML
    private TextField email;

    //Password text field
    @FXML
    private TextField password;

    //Sign in button
    @FXML
    private Button signIn;

    //Sign up button
    @FXML
    private Button signUp;
    
    protected String uuid;

    protected Login_SignUpController loginController = new Login_SignUpController();

    /**
     * If the username and password match go to the home page
     *
     * @param event
     */
    @FXML
    public void handleSignInButtonAction(ActionEvent _event) {

        if (_event.getSource() == signIn) {
            if (logIn()) {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/HomePage.fxml"));
                    Parent parent = loader.load();
                    //create referece to view being used in the next scene and set the uuid from that view equal to the one that matches the users login.
                    HomePageView view = loader.getController();
                    System.out.println(this.loginController.getUUID());
                    view.setUUID(this.loginController.getUUID());
                    
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
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleSignUpButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == signUp) {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SignUpPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Checks if the application is connected to the database
     *
     * @param url
     * @param rb
     */
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
        String email = this.email.getText();
        String password = this.password.getText();
        
        if (email.isEmpty() || email.isEmpty()) {
            labelError.setText("Please enter email/password");
            return false;
        } else {
            this.loginController.setUUID(this.loginController.sendVerificationRequest(email, password));
            if (this.loginController.getUUID() == null) {
                return false;
            } else {
                return true;
            }
        }

    }

    /**
     * Label color and text
     *
     * @param color
     * @param text
     */
    private void setLabel(Color color, String text) {
        labelError.setTextFill(color);
        labelError.setText(text);
        System.out.println(text);
    }

}
