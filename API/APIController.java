package API;

/**
 * APIController class for facilitating interactions between the computer and the APIModel class.
 * Authors: Preston Williamson
 * Last Updated Date: 17-FEB-2020
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
        this.model = _model;
        for (int i = 0; i < _paramArr.length; i++)
            this.model.config.put(_paramArr [i] [this.KEY], _paramArr [i] [this.VALUE]);
        
        this.model.userKey = _userKey;
        this.model.urlSite = _url;
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
    
    //================= GETTERS ===============
    public String getAPIString (String _requestMethod, String _attribute) {
        return this.model.getAPIString(_requestMethod, _attribute);
    }
}
