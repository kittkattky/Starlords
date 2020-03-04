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
public class LocationAPIAdapter implements LocationAPIInterface {
    protected final LocationAPIInterface api = new LocationAPI ();

    @Override
    public void submitRequest() {
        this.api.submitRequest();
    }

    @Override
    public String getURL() {
        return this.api.getUserKey();
    }

    @Override
    public String getUserKey() {
        return this.api.getUserKey();
    }

    @Override
    public String getRequestMethod() {
        return this.api.getRequestMethod();
    }

    @Override
    public String getLocationAttributeName() {
        return this.api.getLocationAttributeName();
    }

    @Override
    public String getLatitudeAttributeName() {
        return this.api.getLatitudeAttributeName();
    }

    @Override
    public String getLongitudeAttributeName() {
        return this.api.getLongitudeAttributeName();
    }

    @Override
    public String getUserKeyAttributeName() {
        return this.api.getUserKeyAttributeName();
    }

    @Override
    public String[][] getAPIConfigParams() {
        return this.api.getAPIConfigParams();
    }

    @Override
    public double getLatitude() {
        return this.api.getLatitude();
    }

    @Override
    public double getLongitude() {
        return this.api.getLongitude();
    }

    @Override
    public void setURL(String _url) {
        this.api.setURL(_url);
    }

    @Override
    public void setUserKey(String _userKey) {
        this.api.setUserKey(_userKey);
    }

    @Override
    public void setRequestMethod(String _reqMethod) {
        this.api.setRequestMethod(_reqMethod);
    }

    @Override
    public void setLocationAttributeName(String _attr) {
        this.api.setLocationAttributeName(_attr);
    }

    @Override
    public void setLatitudeAttributeName(String _attr) {
        this.api.setLatitudeAttributeName(_attr);
    }

    @Override
    public void setLongitudeAttributeName(String _attr) {
        this.api.setLongitudeAttributeName(_attr);
    }

    @Override
    public void setUserKeyAttributeName(String _attr) {
        this.api.setUserKeyAttributeName(_attr);
    }

    @Override
    public void setAPIConfigParams(String[][] _params) {
        this.api.setAPIConfigParams(_params);
    }

    @Override
    public void setLatitude(double _lat) {
        this.api.setLatitude(_lat);
    }

    @Override
    public void setLongitude(double _lng) {
        this.api.setLongitude(_lng);
    }
    
}
