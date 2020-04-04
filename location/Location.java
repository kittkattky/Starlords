package location;

import LocationAPI.LocationAPIAdapter;
import java.util.LinkedHashMap;

public class Location {
    protected static final boolean GEO_CODE = false;
    protected static final boolean GEO_LOCATION = true;
    
    private final boolean requestType;
    private LocationAPIAdapter api;
    private String GEO_LOCATION_INDICATOR = "api.geolocation.";
    private String GEO_CODE_INDICATOR = "api.geocode.";

    public Location(boolean _type) throws Exception {
        String indicator;       
        
        if (_type == Location.GEO_CODE)
            
            indicator = this.GEO_CODE_INDICATOR;
        else
            indicator = this.GEO_LOCATION_INDICATOR;
        
        this.requestType = _type;
        this.api = new LocationAPIAdapter (indicator);
    }
    
    public Location (boolean _type, LinkedHashMap <String, String> _paramMap) throws Exception {
        this (_type);
        for (String key : _paramMap.keySet())
            this.api.setAPIConfigParameter(key, _paramMap.get(key));
    }

    public void submitRequest () {
        this.api.submitRequest();
    }

    public double getLatitude () {
        return this.api.getLatitude();
    }

    public double getLongitude () {
        return this.api.getLongitude();
    }

    public void setAPIConfigParameter (String _key, String _val) throws Exception {
        boolean isGeoCode = (this.requestType == Location.GEO_CODE);
        boolean isGeoLocation = (this.requestType == Location.GEO_LOCATION);
        boolean isUserKey = (_key.compareTo(this.api.getUserKeyAttributeName()) == 0);
        
        //GeoCode object may have multiple parameters.
        if (isGeoCode)
            this.api.setAPIConfigParameter(_key, _val);
        
        //GeoLocation will have user key as its sole parameter (authentication). Do not allow other parameters.
        if (isGeoLocation) {
            if (isUserKey)
                this.api.setAPIConfigParameter(_key, _val);
            else
                throw new Exception ("Key-Value pair [key=" + _key + ", value=" + _val + "] cannot be added to " + this.GEO_LOCATION_INDICATOR);
        }        
    }
}
