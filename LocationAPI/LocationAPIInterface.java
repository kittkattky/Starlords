/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationAPI;

/**
 *
 * @author prest
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
    public String [] [] getAPIConfigParams ();
    public double getLatitude ();
    public double getLongitude ();
    
    public void setURL (String _url);
    public void setUserKey (String _userKey);
    public void setRequestMethod (String _reqMethod);
    public void setLocationAttributeName (String _attr);
    public void setLatitudeAttributeName (String _attr);
    public void setLongitudeAttributeName (String _attr);
    public void setUserKeyAttributeName (String _attr);
    public void setAPIConfigParams (String [] [] _params);
    public void setLatitude (double _lat);
    public void setLongitude (double _lng);
    
}
