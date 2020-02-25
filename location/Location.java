package location;

import Utilities.*;
import API.*;
import java.io.*;
import java.net.MalformedURLException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.apache.poi.xssf.usermodel.*;

public class Location {
     public static void main(String[] args) throws JSONException {        
        String lat;
        String geoCodeURL = "https://maps.googleapis.com/maps/api/geocode/json";
        String [] [] geoCodeParams = new String [] [] {{"key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"}, {"address", "27370"}};

        String geoLocationURL = "https://www.googleapis.com/geolocation/v1/geolocate";
        String [] [] geoLocationParams = new String [] [] {{"key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"}};

        APIModel geoCodeModel = new APIModel ();
        APIController geoCodeControl = new APIController (geoCodeModel, geoCodeURL, geoCodeParams);

        //get API return string.
        lat = geoCodeControl.getAPIString("GET", "results;geometry;location;lat");
        System.out.println (lat);

        //__________________________________

        APIModel geoLocationModel = new APIModel ();
        APIController geoLocationControl = new APIController (geoLocationModel, geoLocationURL, geoLocationParams);

        //get API return string.
        lat = geoLocationControl.getAPIString("POST", "location;lat");
        System.out.println (lat);
    }
}