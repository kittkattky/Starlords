package LocationAPI;

import java.util.LinkedHashMap;

/**
 * LocationAPIInterface.
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public interface LocationAPIInterface {
    public void submitRequest ();

    public String getURL ();
    public String getUserKey ();
    public String getRequestMethod ();
    public String getLocationAttributeName ();
    public String getLatitudeAttributeName ();
    public String getLongitudeAttributeName ();
    public String getUserKeyAttributeName ();
    public LinkedHashMap <String, String> getAPIConfigParamObject ();
    public String getAPIConfigParameters ();
    public double getLatitude ();
    public double getLongitude ();

    public void setURL (String _url);
    public void setUserKey (String _userKey);
    public void setRequestMethod (String _reqMethod);
    public void setLocationAttributeName (String _attr);
    public void setLatitudeAttributeName (String _attr);
    public void setLongitudeAttributeName (String _attr);
    public void setUserKeyAttributeName (String _attr);
    public void setAPIConfigParameter (String _key, String _val);
    public void setLatitude (double _lat);
    public void setLongitude (double _lng);
}
