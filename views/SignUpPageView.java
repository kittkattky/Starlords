package views;

/**
 * View for handling all events on sign up page
 *
 * @author Kahlie/Diego Last Updated: 4/25/2020 by Diego
 */
import controllers.AccountController;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utilities.Homepage.EventHandlers;

public class SignUpPageView implements Initializable {

    @FXML
    public TextField suFName;

    @FXML
    private TextField suLName;

    @FXML
    private TextField suStreet;

    @FXML
    private TextField suCity;

    @FXML
    private TextField suZipCode;

    @FXML
    private TextField suEmail;

    @FXML
    private TextField suPassword;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    protected Label errorLabel;

    protected AccountController signUpController = new AccountController();
    //EventHandlers has transition method for moving from one scene to the next.
    protected EventHandlers handler = new EventHandlers();
    boolean signUpSuccessful = false;

    /**
     * When button is clicked, check if sign up was successful, if yes then
     * return to login page scene.
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    public void handleSignUpButtonAction(ActionEvent _event) throws IOException {

        if (_event.getSource() == this.signUpBtn) {
            //send crendentials to DB and store result in signUpSuccessful
            this.signUpSuccessful = sendCredentialsToController();
            if (this.signUpSuccessful) {
                try {
                    this.handler.switchScenes(_event, "fxml/LogInPage.fxml");
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Returns to login page scene when button is clicked.
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    public void handleBackButtonAction(ActionEvent _event) throws IOException {

        if (_event.getSource() == this.backBtn) {

            try {
                this.handler.switchScenes(_event, "fxml/LogInPage.fxml");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     * Initializes the scene elements associated with this view class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> myList;
      try {
        myList = Files.lines(Paths.get("FiftyStates.txt")).collect(Collectors.toList());
        combobox.setItems(FXCollections.observableArrayList(myList));
    } catch (IOException e) {
    }
    }

    /**
     * Checks if credentials are valid using helper method. If valid, then put
     * credentials into map and send to controller. Returns true if successful,
     * and false if not.
     *
     * @return
     */
    private boolean sendCredentialsToController() {
        //If credentials are valid, attempt to insert into database
        if (checkCredentials()) {
            Map<String, String> userCredentials = new HashMap<>();
            //create UUID for the tuple to be inserted
            UUID uuid = UUID.randomUUID();

            userCredentials.put("uuid", uuid.toString());
            userCredentials.put("firstName", this.suFName.getText());
            userCredentials.put("lastName", this.suLName.getText());
            userCredentials.put("street", this.suStreet.getText());
            userCredentials.put("city", this.suCity.getText());
            userCredentials.put("state", this.combobox.getValue());
            userCredentials.put("zipcode", this.suZipCode.getText());
            userCredentials.put("email", this.suEmail.getText());
            userCredentials.put("password", this.suPassword.getText());

            //Returns a boolean value based on if the controller result
            return this.signUpController.sendInsertRequest(userCredentials);
        } else {
            return false;
        }

    }

    /**
     * This method performs checks to ensure the users data can be stored in the
     * database.
     */
    private boolean checkCredentials() {
        if (this.suEmail.getText().isEmpty() || this.suEmail.getText() == null) {
            errorLabel.setText("Must enter email");
            return false;
        } else if (this.suEmail.getText().length() > 50) {
            errorLabel.setText("Email cannot exceed 50 characters");
            return false;
        } else if (!this.suEmail.getText().contains("@")) {
            errorLabel.setText("Invalid Email");
            return false;
        } else if (this.signUpController.checkEmailRequest(this.suEmail.getText())) {
            errorLabel.setText("The email is already associated with an account");
            return false;
        } else if (this.suPassword.getText().isEmpty() || this.suPassword.getText() == null) {
            errorLabel.setText("Must enter password");
            return false;
        } else if (this.suPassword.getText().length() > 50) {
            errorLabel.setText("Password cannot exceed 50 characters");
            return false;
        } else if (this.suFName.getText().length() > 20) {
            errorLabel.setText("First name cannot exceed 20 characters");
            return false;
        } else if (this.suLName.getText().length() > 20) {
            errorLabel.setText("Last name cannot exceed 20 characters");
            return false;
        } else if (this.suStreet.getText().length() > 20) {
            errorLabel.setText("Street name cannot exceed 20 characters");
            return false;
        } else if (this.suCity.getText().length() > 20) {
            errorLabel.setText("City name cannot exceed 20 characters");
            return false;
        } else if (this.suZipCode.getText().length() > 5) {
            errorLabel.setText("Invalid zip code");
            return false;
        } else {
            return true;
        }
    }

}
