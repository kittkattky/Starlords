package views;

/**
 * This class is the sign up page
 *
 * @author Kahlie Last Updated: 2/25/2020
 */
import api.adapters.DatabaseAdapter;
import controllers.Login_SignUpController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.DBConnectionUtil.ConnectionUtil;

public class SignUpPageView implements Initializable {

    @FXML
    private TextField suFName;

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

    ObservableList<String> list = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
            "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");

    protected Login_SignUpController signUpController = new Login_SignUpController();
    boolean signUpSuccessful = false;

    /**
     * When button is clicked, check if sign up was successful, if yes then return to login page scene.
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleSignUpButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == this.signUpBtn) {
            //send crendentials to DB and store result in signUpSuccessful
            this.signUpSuccessful = sendCredentialsToController();
            if (this.signUpSuccessful) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LogInPage.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Returns to login page scene when button is clicked.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void handleBackButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == this.backBtn) {

            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LogInPage.fxml")));
                stage.setScene(scene);
                stage.show();

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
        combobox.setItems(list);
    }

    /**
     * Checks if credentials are valid using helper method.
     * If valid, then put credentials into map and send to controller.
     * Returns true if successful, and false if not.
     * @return
     */
    private boolean sendCredentialsToController() {
        //If credentials are valid, attempt to insert into database
        if (checkCredentials()) {
            Map<String, String> userCredentials = new HashMap<>();
            //create UUID for the tuple to be inserted
            UUID uuid = UUID.randomUUID();

            userCredentials.put("uuid", uuid.toString());
            userCredentials.put("firstname", this.suFName.getText());
            userCredentials.put("lastname", this.suLName.getText());
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
