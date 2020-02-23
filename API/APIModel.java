package API;

/**
 * APIModel class for facilitating communications between the computer and external APIs.
 * Authors: Preston Williamson
 * Last Updated Date: 17-FEB-2020
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 * 
 * @author preston.williamson
 */
public class APIModel {
    protected String urlSite, apiReturn;
    protected LinkedHashMap <String, String> config;
    protected URL url;
    protected HttpURLConnection connect;
    protected JSONObject json;
    
    /**
     * public APIModel constructor.
     */
    public APIModel () {     
        this.config = new LinkedHashMap <> ();
    }
    
    /**
     * getJSONAttribute: a method that works directly with JSON-formatted return strings. This does NOT attempt a new extraction of a POST/GET request.
     * @param _attribute: a String representation of the desired attribute from which to parse.
     * @return
     * @throws JSONException 
     */
    public String getJSONAttribute (String _attribute) throws JSONException {
        this.formatJSONString();
        this.json = new JSONObject (this.apiReturn);
        return this.json.getString(_attribute);
    }
    
    /**
     * formatJSONString: a helper method that ensures the prospect API return string matches the JSON formatting requirements.
     * i.e., a JSON return value must start with an opening curly bracket ('{').
     */
    public void formatJSONString () {
        //continue chopping off leading and trailing characters until the expected first char is obtained.
        while (this.apiReturn.charAt(0) != '{')
            this.apiReturn = this.apiReturn.substring(1, this.apiReturn.length () - 1);
    }
    
    /**
     * getAPIString - a method that handles extraction and parsing from an API POST/GET request.
     * @param _userKey: API token required for execution
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attribute: a String representation of the desired attribute from which to parse. 
     * @return: a String representation of the API output.
     */
    public String getAPIString(String _userKey, String _requestMethod, String _attribute) {
        String config = this.urlSite;
        String parameters = "";
        String ret = "";
        StringBuffer strBuff;
        Set <String> set = this.config.keySet();
        
        for (String key : set) {
            //if the parameter set is empty, the first parameter must have the '?' indicator; otherwise, set indicator to '&'.
            char delimiter = ( parameters.isEmpty() ? '?' : '&');
            parameters = parameters.concat( delimiter + key + "=" + this.config.get(key));
        }
        
        config = config.concat(parameters);
        
        try {
            //instantiate new URL object with config string and open connection.
            this.url = new URL (config);
            this.connect = (HttpURLConnection) url.openConnection();
            
            //set the request method.
            this.connect.setRequestMethod(_requestMethod);
            
            //switch statement dedicated to dictating next steps based on request method.
            switch (_requestMethod.toLowerCase()) {
                case "post":
                    //enable output.
                    this.connect.setDoOutput(true);
                    
                    //POST results to server.
                    DataOutputStream wr = new DataOutputStream (this.connect.getOutputStream());
                    wr.writeUTF(parameters);
                    break;
                    
                //no further action defined for other request methods.
                default:
                    break;
            }
            
            //verify response code is 200, indicating that request was successful. Otherwise, report results.
            if (this.connect.getResponseCode() != 200)
                throw new Exception ("API response code " + this.connect.getResponseCode() + ": " + this.connect.getResponseMessage());
            else {
                BufferedReader response = new BufferedReader ( new InputStreamReader (this.connect.getInputStream()) );
                String inputLine;

                strBuff = new StringBuffer ();

                while ((inputLine = response.readLine ()) != null)
                    strBuff.append(inputLine);
                
                //set the API return variable and format accordingly.
                this.apiReturn = strBuff.toString();
                this.formatJSONString ();
                
                //parse desired attribute.
                this.json = new JSONObject (this.apiReturn);
                ret = this.json.getString (_attribute);
            }
            
            //disconnect system resources.
            this.connect.disconnect();
            return ret;
        }
        catch (Exception ex) {
            Logger.getLogger(APIModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;            
        }            
    }
}
