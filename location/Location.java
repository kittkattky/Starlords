package location;

import LocationAPI.GeoCode;
import LocationAPI.GeoLocation;
import LocationAPI.LocationAPIAdapter;
import LocationAPI.LocationAPIInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Location {
    protected static final int GEO_CODE = 0;
    protected static final int GEO_LOCATION = 1;
    protected LocationAPIAdapter api;

    public Location(int _type) {
        if (_type == Location.GEO_CODE)
            this.api = new GeoCode();
        else if (_type == Location.GEO_LOCATION)
            this.api = new GeoLocation();
        else
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, "Invalid location type enum: [" + _type + "]");
    }

    public void submitRequest () {
        this.api.submitRequest();
    }

    public String getURL () {
        return this.api.getURL();
    }

    public String getAPIConfigParameters () {
        return this.api.getAPIConfigParameters();
    }

    public double getLatitude () {
        return this.api.getLatitude();
    }

    public double getLongitude () {
        return this.api.getLongitude();
    }

    public void setAPIConfigParameter (String _key, String _val) {
        this.api.setAPIConfigParameter(_key, _val);
    }
}
