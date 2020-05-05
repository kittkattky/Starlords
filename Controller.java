/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author KYLIA
 */
public class Controller implements Initializable {

    @FXML
    //Event name
    private Label label;

//Date picker
    @FXML
    private JFXDatePicker jfxpDate;
//Time picker
    @FXML
    private JFXTimePicker jfxpTime;

    @FXML
    private void handleButtonAction(MouseEvent event) {

        String value = (jfxpDate.getValue() != null ? jfxpDate.getValue().toString() : "");

        if (!value.equals("")) {
            System.out.println("The selected date is: " + value);
        } else {
            System.out.println("Date not selected");
        }
        String valueT = (jfxpTime.getValue() != null ? jfxpTime.getValue().toString() : "");

        if (!value.equals("")) {
            System.out.println("The selected Time is: " + valueT);
        } else {
            System.out.println("Time not selected");
        }

    }

    /* this method will change the scene to the calendar view when the user hits
    create
     */
    public void changescreenButtonPushed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
