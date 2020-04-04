package View;

/**
 * This class is the sign up page
 *
 * @author Kahlie Last Updated: 2/25/2020
 */

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.ConnectionUtil;

public class SignUpPageController implements Initializable {

    //First name text field
    @FXML
    private TextField suFName;

    //Last name text field
    @FXML
    private TextField suLName;

    //Email text field
    @FXML
    private TextField suEmail;

    //Password text field
    @FXML
    private TextField suPassword;

    //Sign in button
    @FXML
    private Button signInBtn;

    //Drop down box for the states in the USA
    @FXML
    private ComboBox<String> combobox;

    /**
     * When the user clicks the sign in button take the back to the home page
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleButtonAction3(MouseEvent event) throws IOException{

        if (event.getSource() == this.signInBtn) {
            if (signUp().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
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
     * Initializes the controller class.
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

    public SignUpPageController() throws ClassNotFoundException {
        Con = ConnectionUtil.conDB();
    }

    //Connecting to database
    Connection Con = null;
    //Used to execute queries
    PreparedStatement PreparedStatement = null;
    //Contains results of the query
    ResultSet ResultSet = null;

    /**
     * Inserts email and password into database
     * @return
     */
   private String signUp() {
        String status = "Success";
        String email = this.suEmail.getText();
        String password = this.suPassword.getText();

            String sql = "INSERT INTO users(USERNAME, PASSWORD) VALUES (?, ?)";
            try {
                PreparedStatement = Con.prepareStatement(sql);
                PreparedStatement.setString(1, email);
                PreparedStatement.setString(2, password);
                PreparedStatement.executeUpdate();

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        return status;
    }

}
