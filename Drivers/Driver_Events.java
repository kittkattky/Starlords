package Drivers;

/**
 * Driver class for the events
 * @author Kahlie
 */

import controllers.AccountController;
import java.io.IOException;
import models.EventsModel;
import org.json.JSONException;

public class Driver_Events {
    
    public AccountController myAccountController = new AccountController(); 
    public static int zipcode = 28390;

    public static void main(String[] args) throws IOException, JSONException{
    setZipCode(); 
    }
    
    
     public static void setZipCode() throws IOException, JSONException {
         int zipCode = 74126;
         zipcode = zipCode;
         System.out.println(zipcode);
         EventsModel modelA = new EventsModel();
    
        //modelA = modelA.loadEventsByZipcode("music", zipcode);
        System.out.println(modelA.getEventTitleMap());

    }
}  






