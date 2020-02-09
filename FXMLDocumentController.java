package login2;


/**
 *
 * @author Kahlie
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
    private Label lblError;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_password;

    @FXML
    private Button BtnSignin;

    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == BtnSignin) {
            if (logIn().equals("Success")) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (con == null) {
            lblError.setTextFill(Color.RED);
            lblError.setText("Server Error");
        } else {
            lblError.setTextFill(Color.GREEN);
            lblError.setText("Server Online");
        }
    }

    public FXMLDocumentController() {
        con = ConnectionUtil.conDB();
    }

    //status check
    private String logIn() {
        String status = "Success";
        String email = tf_email.getText();
        String password = tf_password.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Please Enter a Username and Password");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where username = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Wrong Username or Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }
}
