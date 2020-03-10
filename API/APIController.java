package API;

import java.util.LinkedHashMap;
import org.json.JSONException;

/**
 * APIController class for facilitating interactions between the computer and the APIModel class.
 * Authors: Preston Williamson
 * Last Updated Date: 27-FEB-2020
 */

public class APIController {
    protected final int KEY = 0, VALUE = 1;
    protected APIModel model;

    /**
     * public APIController constructor class.
     * @param _model: APIModel parameter that houses the internal APIModel configurations.
     * @param _url: Base API URL.
     * @param _userKey: API user key.
     * @param _paramArr: API parameters stored in a two-dimensional array.
     */
    public APIController (APIModel _model, String _url, String _userKey, LinkedHashMap <String, String> _paramArr) {
        this.model = _model;

        APIModel apiModel = this.model;

        if (_paramArr != null) {
            for (String key : _paramArr.keySet())
                apiModel.setAPIConfigParameter(key, _paramArr.get(key));
        }

        apiModel.setUserKey(_userKey);
        this.model.urlSite = _url;
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a userKey.
     * @param _model: APIModel parameter that houses the internal APIModel configurations.
     * @param _url: Base API URL.
     * @param _paramArr: API parameters stored in a LinkedHashMap.
     */
    public APIController (APIModel _model, String _url, LinkedHashMap <String, String> _paramArr) {
        this (_model, _url, null, _paramArr);
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a user model.
     * @param _url: Base API URL.
     * @param _userKey: API user key.
     * @param _paramArr: API parameters stored in a LinkedHashMap.
     */
    public APIController (String _url, String _userKey, LinkedHashMap <String, String> _paramArr) {
        this (new APIModel (), _url, _userKey, _paramArr);
    }

    /**
     * public APIController constructor class. Constructor allows model instantiation without providing a user model or config map.
     * @param _url: Base API URL.
     * @param _userKey: API user key.
     */
    public APIController (String _url, String _userKey) {
        this (new APIModel (), _url, _userKey, null);
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
        APIModel apiModel = this.model;
        apiModel.setAPIParseObject();
        return apiModel.toMap(this.model.getAPIParseObject());
    }

    //================= GETTERS ===============

    /**
     * getAPIResultString: a method which handles API request submission and extraction of API result string.
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attributes: a String representation of the desired attribute from which to parse.
     * @return String: String representation of API results.
     */
    public String getAPIResultString (String _requestMethod, String _attributes) {
        APIModel apiModel = this.model;

        //submit request if result string is null.
        if (apiModel.getAPIResultString() == null)
            this.submitAPIRequest(_requestMethod, _attributes);

        return apiModel.getAPIResultString();
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
}
