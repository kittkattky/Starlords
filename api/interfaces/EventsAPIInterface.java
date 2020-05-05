package api.interfaces;

/**
 * Interface for the events
 *
 * @author Kahlie
 * @date 4/2/20
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;

public interface EventsAPIInterface {
    
    public ArrayList<Map>  loadCategories() throws JSONException, MalformedURLException, ProtocolException, IOException;

    public ArrayList<Map>  loadEventsByZipcode(String _category, int _zipcode) throws JSONException, MalformedURLException, ProtocolException, IOException;
}
