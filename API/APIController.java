package API;

/**
 * APIController class for facilitating interactions between the computer and the APIModel class.
 * Authors: Preston Williamson
 * Last Updated Date: 17-FEB-2020
 */

import org.json.*;

public class APIController {
    protected APIModel model;
    
    /**
     * public APIController constructor class.
     * @param _model: APIModel parameter that houses the internal APIModel configurations.
     */
    public APIController (APIModel _model) {
        this.model = _model;
    }
    
    //================= GETTERS ===============
    public String getAPIConfigParameter (String _key) {
        return this.model.config.get(_key);
    }
    
    public String getAPIString (String _userKey, String _requestMethod, String _attribute) {
        return this.model.getAPIString(_userKey, _requestMethod, _attribute);
    }
    
    public String getJSONAttribute (String _attribute) throws JSONException {
        return this.model.getJSONAttribute(_attribute);
    }
    
    public String getURLSite () {
        return this.model.urlSite;
    }
    
    //================= SETTERS ===============
    public void setAPIConfigParameter (String _key, String _value) {
        this.model.config.put(_key, _value);
    }
    
    public void setAPIString (String _apiReturn) {
        this.model.apiReturn = _apiReturn;
    }
    
    public void setURLSite (String _url) {
        this.model.urlSite = _url;
    }
}
