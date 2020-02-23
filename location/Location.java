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
    public static void main (String [] a) throws IOException, Exception {
        boolean setsecure = true;
        String filePath = System.getProperty("user.dir") + "\\datastore\\test.xlsx";
        String [] headers = new String []{"CUSTOMER_NUMER", "CUSTOMER_ADDRESS", "CUSTOMER_CITY", "CUSTOMER_STATE", "CUSTOMER_ZIP", "PASSWORD_HASH"};
        
        File file = new File (filePath);
        if (file.exists()) file.delete();
        
        ExcelUtil excel = new ExcelUtil ();
        excel.createWorkbook(filePath, setsecure);
        excel.createWorksheet("CUSTOMER", headers);        
        excel.closeWorkbook();
        excel.openWorkbook(filePath, setsecure);
        excel.setCellData("CUSTOMER", 3, 5, "test this");
        excel.closeWorkbook();
    }
    
     public static void main_(String[] args) throws JSONException {

        //parameters needed for urlString
        final int KEY = 0, VALUE = 1;
        
        String userKey, lat, ret;
        String geoCodeURL = "https://maps.googleapis.com/maps/api/geocode/json";
        String [] [] geoCodeParams = new String [] [] {
            {"key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"},
            {"address", "27370"}};

        String geoLocationURL = "https://www.googleapis.com/geolocation/v1/geolocate";
        String [] [] geoLocationParams = new String [] [] {
            {"key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"}};

        APIModel geoCodeModel = new APIModel ();
        APIController geoCodeControl = new APIController (geoCodeModel);
        geoCodeControl.setURLSite (geoCodeURL);

        //add API config parameters.
        for (int i = 0; i < geoCodeParams.length; i++)
            geoCodeControl.setAPIConfigParameter(geoCodeParams [i] [KEY], geoCodeParams [i] [VALUE]);

        userKey = geoCodeControl.getAPIConfigParameter ("&auth-token");

        //get API return string.
        ret = geoCodeControl.getAPIString(userKey, "GET", "results");
        geoCodeControl.setAPIString(ret);
        String geo = geoCodeControl.getJSONAttribute("geometry");
        geoCodeControl.setAPIString(geo);
        String loc = geoCodeControl.getJSONAttribute("location");
        geoCodeControl.setAPIString(loc);
        lat = geoCodeControl.getJSONAttribute("lat");
        System.out.println (lat);


        //__________________________________

        APIModel geoLocationModel = new APIModel ();
        APIController geoLocationControl = new APIController (geoLocationModel);
        geoLocationControl.setURLSite (geoLocationURL);

        //add API config parameters.
        for (int i = 0; i < geoLocationParams.length; i++)
            geoLocationControl.setAPIConfigParameter(geoLocationParams [i] [0], geoLocationParams [i] [1]);

        userKey = geoLocationControl.getAPIConfigParameter ("?key");

        //get API return string.
        ret = geoLocationControl.getAPIString(userKey, "POST", "location");
        geoLocationControl.setAPIString(ret);
        lat = geoLocationControl.getJSONAttribute("lat");
        System.out.println (lat);
    }
}