package Csc340Project;

/**
 * This class is the SignUpPageController
 *
 * @author Kahlie Last Updated: 2/17/2020
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    private TextField suFName;

    @FXML
    private TextField suLName;
    
    @FXML
    private TextField suEmail;

    @FXML
    private TextField suPassword;
    
    @FXML
    private Button signInBtn;

    @FXML
    private ComboBox<String> combobox;
    
    ObservableList<String> list = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
            "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
    
    @FXML
    public void handleButtonAction3(MouseEvent event) throws IOException{

        if (event.getSource() == signInBtn) {
            if (SignUp().equals("Success")) {
                try {
                
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FXMLDocument.fxml")));
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
        combobox.setItems(list);
        
        // TODO
    }
    
    public SignUpPageController() {
        Con = ConnectionUtil.conDB();
    }

    //Connecting to database
    Connection Con = null;
    //Used to execute SQL queries 
    PreparedStatement PreparedStatement = null;
    //Contains results of the SQL query
    ResultSet ResultSet = null;

   private String SignUp() {
        String status = "Success";
        String email = suEmail.getText();
        String password = suPassword.getText();
         
            //query
            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
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
