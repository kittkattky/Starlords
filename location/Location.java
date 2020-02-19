package location;

import API.*;
import java.io.*;
import java.net.MalformedURLException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.apache.poi.xssf.usermodel.*;

public class Location {
    public static void main (String [] a) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\test.xlsx";
        
        XSSFWorkbook wb = new XSSFWorkbook ();
        
        XSSFSheet sheet = wb.createSheet("Action1");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("testing123");
        
        FileOutputStream out = new FileOutputStream(new File(filePath));
        wb.write(out);
        out.close ();
        
        System.out.println (sheet.getSheetName());
        
        wb.close();
        
        wb = new XSSFWorkbook (filePath);
        wb.getSheetAt(0);
        wb.close ();
    }
            
    
     public static void main_(String[] args) throws JSONException {

        //parameters needed for urlString
        final int KEY = 0, VALUE = 1;
        
        String userKey, lat, ret;
        String geoCodeURL = "https://maps.googleapis.com/maps/api/geocode/json";
        String [] [] geoCodeParams = new String [] [] {
            {"?key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"},
            {"&address", "27370"}};

        String geoLocationURL = "https://www.googleapis.com/geolocation/v1/geolocate";
        String [] [] geoLocationParams = new String [] [] {
            {"?key", "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU"}};

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