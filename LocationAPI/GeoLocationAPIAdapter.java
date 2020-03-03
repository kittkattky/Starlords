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
public class GeoLocationAPIAdapter implements LocationAPIInterface {

    protected static final LocationAPIInterface api = new GeoLocationAPI ();

    @Override
    public void submitRequest() {
        this.api.submitRequest();
    }
    
    @Override
    public double getLatitude() {
        return this.api.getLatitude();
    }

    @Override
    public double getLongitude() {
        return this.api.getLongitude();
    }
    
}
