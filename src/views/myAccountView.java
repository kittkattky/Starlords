package views;

/**
 * This is the view associated with the myAccount scene.
 * @author: Diego Rodriguez
 * @date: 4/25/20
 */

import controllers.AccountController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import utilities.Homepage.EventHandlers;


public class myAccountView implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label fNameLabel;

    @FXML
    Label LNameLabel;

    @FXML
    Label streetLabel;

    @FXML
    Label cityLabel;

    @FXML
    Label stateLabel;

    @FXML
    Label zipCodeLabel;

    @FXML
    Label emailLabel;

    @FXML
    Label logOutLabel;

    @FXML
    Label homePageLabel;

    @FXML
    Label updateErrorLabel;

    @FXML
    Label deleteLabel;
    
    @FXML
    Label deleteLabel2;

    @FXML
    PasswordField password;

    @FXML
    PasswordField password2;

    @FXML
    PasswordField newPassword;
    
    @FXML
    Button sureButton;
    
    @FXML
    Button notSureButton;

    private Boolean areYouSure = false;
    protected EventHandlers handler = new EventHandlers();
    public AccountController myAccountController = new AccountController();

    /**
     * Method is called when scene is created, sets gradient for anchorpane.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stop[] stops = new Stop[]{new Stop(0, Color.web("#5C258D")), new Stop(1, Color.web("#4389A2"))};
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill fillBackground = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        this.anchorPane.setBackground(new Background(fillBackground));
    }

    /**
     * Sets all labels for displaying user data
     * @param fName
     * @param LName
     * @param street
     * @param city
     * @param state
     * @param zipCode
     * @param email 
     */
    public void setUserInfoLabels(String fName, String LName, String street, String city, String state, String zipCode, String email) {
        this.fNameLabel.setText(fName);
        this.LNameLabel.setText(LName);
        this.streetLabel.setText(street);
        this.cityLabel.setText(city);
        this.stateLabel.setText(state);
        this.zipCodeLabel.setText(zipCode);
        this.emailLabel.setText(email);
    }
    
    /**
     * Goes back to home page when home page label is mouse clicked. Sets necessary fields in home page.
     * @param _event
     * @throws IOException 
     */
    @FXML
    public void backToHomepage(MouseEvent _event) throws IOException {
        HomePageView view = this.handler.switchScenes(_event, "fxml/HomePage.fxml").getController();
        view.handler.uuidController.setUUID(this.myAccountController.uuidController.getUUID());
        view.setHomePageText("Please select an option");
    }

    /**
     * Returns to log in page when log out label is mouse clicked. Sets necessary fields on log in page
     * @param _event
     * @throws IOException 
     */
    @FXML
    public void logOut(MouseEvent _event) throws IOException {
        LogInPageView view = this.handler.switchScenes(_event, "fxml/LogInPage.fxml").getController();
        view.setLogOutLabel("Log Out Successful");
    }

    /**
     * Updates password when update button event is fired
     * If password is incorrect display message
     * Else perform update and display message
     * @param _event 
     */
    @FXML
    public void updatePassword(ActionEvent _event) {
        String currentPassword = this.password.getText();
        String changePassword = this.newPassword.getText();

        if (this.myAccountController.sendVerificationRequest(this.emailLabel.getText(), currentPassword) == null) {
            this.updateErrorLabel.setTextFill(Color.web("#f5d005"));
            this.updateErrorLabel.setText("Incorrect Password");
        } else {
            this.myAccountController.sendUpdateRequest("password", changePassword);
            this.updateErrorLabel.setTextFill(Color.web("#16dd48"));
            this.updateErrorLabel.setText("Update Successful");
        }
    }

    /**
     * Deletes account if buttons fire events.
     * If password is not correct, display message.
     * Else if the user has already click "I AM SURE!" button, then perform delete.
     * Else, ask user if they are sure.
     * @param _event
     * @throws IOException 
     */
    @FXML
    public void deleteAccount(ActionEvent _event) throws IOException {
        String currentPassword = this.password2.getText();

        if (this.myAccountController.sendVerificationRequest(this.emailLabel.getText(), currentPassword) == null) {
            this.deleteLabel.setText("Incorrect Password");
        } else if (this.areYouSure) {
            this.myAccountController.sendDeleteRequest();
            LogInPageView view = this.handler.switchScenes(_event, "fxml/LogInPage.fxml").getController();
            view.setLogOutLabel("Delete Successful");
        } else {   
            this.deleteLabel.setText("ARE YOU SURE?");
            this.sureButton.setVisible(true);
            this.notSureButton.setVisible(true);
            this.deleteLabel2.setText("Deleting your account will return you to the log in page");
            this.areYouSure = true;
        }
    }
    
    /**
     * Sets parameter when "I'm not sure!" button is clicked and makes buttons/text disappear.
     * @param _event 
     */
    @FXML
    public void notSure(ActionEvent _event) {
        this.areYouSure = false;
        this.sureButton.setVisible(false);
        this.notSureButton.setVisible(false);
        this.deleteLabel.setText("");
        this.deleteLabel2.setText("");
    }

}
