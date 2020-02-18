package Csc340Project;

/**
 * This class is the FXMLDocumentController and displays and confingures the login page
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

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label labelError;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button signIn;
    
    @FXML
    private Button signUp;
    
    //Connecting to database
    Connection Con = null;
    //Used to execute SQL queries 
    PreparedStatement PreparedStatement = null;
    //Contains results of the SQL query
    ResultSet ResultSet = null;

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

    public FXMLDocumentController() {
        Con = ConnectionUtil.conDB();
    }
    
    //status check
    private String logIn() {
        String status = "Success";
        String email1 = email.getText();
        String password1 = password.getText();
        if(email1.isEmpty() || password1.isEmpty()) {
            setLabelError(Color.TOMATO, "Please Enter a Username and Password");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM users Where username = ? and password = ?";
            try {
                PreparedStatement = Con.prepareStatement(sql);
                PreparedStatement.setString(1, email1);
                PreparedStatement.setString(2, password1);
                ResultSet = PreparedStatement.executeQuery();
                if (!ResultSet.next()) {
                    setLabelError(Color.TOMATO, "Wrong Username or Password");
                    status = "Error";
                } else {
                    setLabelError(Color.GREEN, "Login Successful");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLabelError(Color color, String text) {
        labelError.setTextFill(color);
        labelError.setText(text);
        System.out.println(text);
    }
}
