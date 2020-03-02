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
    public APIController (APIModel _model, String _url, String _userKey, String [] [] _paramArr) {
        this.setModel(_model);

        APIModel apiModel = this.getModel();
        for (int i = 0; i < _paramArr.length; i++)
            apiModel.setAPIConfigParameter(_paramArr [i] [this.KEY], _paramArr [i] [this.VALUE]);

        apiModel.setUserKey(_userKey);
        this.model.urlSite = _url;
    }

    /**
     * toMap: a method which converts the current JSON object model into a LinkedHashMap.
     * @return LinkedHashMap
     * @throws JSONException
     */
    public LinkedHashMap <String, Object> toMap () throws JSONException {
        APIModel apiModel = this.getModel();
        apiModel.setJSONObject();
        return apiModel.toMap(this.model.getJSONObject());
    }

    /**
     * public APIController constructor class.
     * @param _model: APIModel parameter that houses the internal APIModel configurations. Constructor allows model instantiation without providing a userKey.
     * @param _url: Base API URL.
     * @param _paramArr: API parameters stored in a two-dimensional array.
     */
    public APIController (APIModel _model, String _url, String [] [] _paramArr) {
        this (_model, _url, null, _paramArr);
    }

    /**
     * submitAPIRequest - a method that handles parsing from an API POST/GET request.
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attributes: a String representation of the desired attribute from which to parse.
     */
    public void submitAPIRequest (String _requestMethod, String _attributes) {
        this.getModel ().submitAPIRequest(_requestMethod, _attributes);
    }

    //================= GETTERS ===============
    /**
     * getModel: returns the pre-instantiated API model.
     * @return APIModel
     */
    public APIModel getModel () {
        return this.model;
    }

    /**
     * getAPIResultString: a method which handles API request submission and extraction of API result string.
     * @param _requestMethod: Request Method ("GET", "POST")
     * @param _attributes: a String representation of the desired attribute from which to parse.
     * @return String: String representation of API results.
     */
    public String getAPIResultString (String _requestMethod, String _attributes) {
        APIModel apiModel = this.getModel ();

        //submit request if result string is null.
        if (apiModel.getAPIResultString() == null)
            this.submitAPIRequest(_requestMethod, _attributes);

        return apiModel.getAPIResultString();
    }

    //================= SETTERS ===============
    /**
     * setModel: a method that defines the controller's model class.
     * @param _model
     */
    public void setModel (APIModel _model) {
        this.model = _model;
    }
}
