package api.interfaces;

/**
 * LocationAPIInterface.
 * Authors: Preston Williamson
 * Last Updated Date: 02-APR-2020
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
    public String getAPIConfigParameters ();
    public String getAPIIndicator ();
    public double getLatitude ();
    public double getLongitude ();
    public String getConfigProperty (String _key);

    public void setURL (String _url);
    public void setUserKey (String _userKey);
    public void setRequestMethod (String _reqMethod);
    public void setLocationAttributeName (String _attr);
    public void setLatitudeAttributeName (String _attr);
    public void setLongitudeAttributeName (String _attr);
    public void setUserKeyAttributeName (String _attr);
    public void setAPIConfigParameter (String _key, String _val);
    public void setAPIIndicator (String _indicator);
    public void setLatitude (double _lat);
    public void setLongitude (double _lng);
}
