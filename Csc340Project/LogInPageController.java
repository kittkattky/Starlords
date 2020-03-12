package Csc340Project;

/**
 * This class is the log in page which can be used to access the home page and sign up page
 *
 * @author Kahlie Last Updated: 2/25/2020
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

public class LogInPageController implements Initializable {

    //A label that appears if an error occurs
    @FXML
    private Label labelError;

    //Username/email text field
    @FXML
    private TextField username;

    //Password text field
    @FXML
    private TextField password;

    //Sign in button
    @FXML
    private Button signIn;
    
    //Sign up button
    @FXML
    private Button signUp;
    
    //Connecting to database
    Connection Con = null;
    //Used to execute SQL queries 
    PreparedStatement PreparedStatement = null;
    //Contains results of the SQL query
    ResultSet ResultSet = null;

    /**
     * If the username and password match go to the home page
     * @param event 
     */
    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == signIn) {
            if (logIn().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/HomePage.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }
    
    /**
     * If the user clicks the signUp button go the the sign up page
     * @param event
     * @throws IOException 
     */
    @FXML
    public void handleButtonAction2(MouseEvent event) throws IOException{

        if (event.getSource() == signUp) {
                
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SignUpPage.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
    }
    
    /**
     * Checks if the application is connected to the database
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (Con == null) {
            labelError.setTextFill(Color.RED);
            labelError.setText("Server Error");
        } else {
            labelError.setTextFill(Color.GREEN);
            labelError.setText("Server Online");
        }
    }

    public LogInPageController() throws ClassNotFoundException {
       Con = ConnectionUtil.conDB();
    }
    
    /**
     * Method checks if the username and password match with the data from the database
     * @return 
     */
    private String logIn() {
        String status = "Success";
        String email1 = username.getText();
        String password1 = password.getText();
        if(email1.isEmpty() || password1.isEmpty()) {
            setLabel(Color.TOMATO, "Please Enter a Username and Password");
            status = "Error";
        } else {
            //If the username and password matches user logs in if not they get an error
            String sql = "SELECT * FROM USERS Where USERNAME = ? and PASSWORD = ?";
            try {
                PreparedStatement = Con.prepareStatement(sql);
                PreparedStatement.setString(1, email1);
                PreparedStatement.setString(2, password1);
                ResultSet = PreparedStatement.executeQuery();
                if (!ResultSet.next()) {
                    setLabel(Color.TOMATO, "Wrong Username or Password");
                    status = "Error";
                } else {
                    setLabel(Color.GREEN, "Login Successful");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    /**
     * Label color and text
     * @param color
     * @param text 
     */
    private void setLabel(Color color, String text) {
        labelError.setTextFill(color);
        labelError.setText(text);
        System.out.println(text);
    }
    
    
}
