package controllers;

/**
 * APIController class for facilitating interactions between the computer and the APIModel class.
 * @author Preston Williamson Last Updated Date: 02-MAY-2020
 */

import models.APIModel;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APIController {
    protected final int KEY = 0, VALUE = 1;
    protected APIModel model;

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a user model.
     * @param _url: Base API URL.
     * @param _userKey: API user key.
     * @param _paramArr: API parameters stored in a LinkedHashMap.
     */
    public APIController (String _url, String _userKey, LinkedHashMap <String, String> _paramArr) {
        this.model = new APIModel();

        if (_paramArr != null) {
            for (String key : _paramArr.keySet())
                this.model.setAPIConfigParameter(key, _paramArr.get(key));
        }

        this.model.setUserKey(_userKey);
        this.model.setURLString(_url);
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation with only the APIModel.
     * @param _model: Base API URL.
     */    
    public APIController (APIModel _model) {
        this (_model.getURLString(), _model.getUserKey(), _model.getConfigObject());
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a userKey.
     * @param _url: Base API URL.
     */
    public APIController (String _url) {
        this (_url, null, null);
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a user model or config map.
     * @param _url: Base API URL.
     * @param _userKey: API user key.
     */
    public APIController (String _url, String _userKey) {
        this (_url, _userKey, null);
    }

    /**
     * submitAPIRequest - a method that handles parsing from an API POST/GET request.
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attributes: a String representation of the desired attribute from which to parse.
     */
    public void submitAPIRequest (String _requestMethod, String _attributes) {
        this.model.submitAPIRequest(_requestMethod, _attributes);
    }

    /**
     * toMap: a method which converts the current JSON object model into a LinkedHashMap.
     * @return LinkedHashMap
     * @throws JSONException
     */
    public LinkedHashMap <String, Object> toMap () throws JSONException {
        this.model.setAPIParseObject();
        return this.model.toMap(this.model.getAPIParseObject());
    }

    //================= GETTERS ===============

    /**
     * getAPIResultString: a method which handles API request submission and extraction of API result string.
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attributes: a String representation of the desired attribute from which to parse.
     * @return String: String representation of API results.
     */
    public String getAPIResultString (String _requestMethod, String _attributes) {

        //submit request if result string is null.
        if (this.model.getAPIResultString() == null) {
            this.submitAPIRequest(_requestMethod, _attributes);
        }

        return this.getAPIResultString ();
    }

    /**
     * getAPIResultString: a method which returns the internally stored API result string value.
     * @return String: String representation of API results.
     */
    public String getAPIResultString () {
        return this.model.getAPIResultString();
    }

    /**
     * getAPIConfigParameters: helper method to return parameterized portion of API URL.
     * @return String
     */
    public String getAPIConfigParameters () {
        return this.model.getAPIConfigParameters();
    }

    //================= SETTERS ===============
    /**
     * setAPIConfigParameter: utility method that handles creating new key-value pairs into the config map.
     * @param _key: value of the key
     * @param _val: value that the key will store.
     */
    public void setAPIConfigParameter (String _key, String _val) {
        this.model.setAPIConfigParameter(_key, _val);
    }

    /**
     * getAPIParseObject: a method which returns the internally stored API parsing mechanism object reference.
     * @return JSONObject: JSONObject
     */
    public JSONObject getAPIParseObject() {
        return this.model.getAPIParseObject();
    }
}
