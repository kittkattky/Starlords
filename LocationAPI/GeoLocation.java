package LocationAPI;

import java.lang.reflect.Field;

/**
 * GeoCode public class for retrieving lat-lng properties by user's current location.
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public class GeoLocation extends LocationAPI {
    protected final String BASE_URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    protected final String USER_KEY = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
    protected final String REQUEST_METHOD = "POST";
    protected final String ATTRIBUTE = "location";
    protected final String LONG_ATTR = "lng";
    protected final String LAT_ATTR = "lat";
    protected final String AUTH_KEY_ATTR = "key";

    /**
     * GeoLocation constructor.
     */
    public GeoLocation () {
        super.setURL(this.BASE_URL);
        super.setUserKey(this.USER_KEY);
        super.setUserKeyAttributeName(this.AUTH_KEY_ATTR);
        super.setRequestMethod(this.REQUEST_METHOD);
        super.setLocationAttributeName(this.ATTRIBUTE);
        super.setLongitudeAttributeName(this.LONG_ATTR);
        super.setLatitudeAttributeName(this.LAT_ATTR);
        
        //set user key.
        super.setAPIConfigParameter(this.AUTH_KEY_ATTR, this.USER_KEY);
    }
}
