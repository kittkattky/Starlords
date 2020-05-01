package api.adapters;

/**
 * Adapter class for the events
 * 
 * @author Kahlie
 * @date 4/2/20
 */

import api.interfaces.EventsAPIInterface;
import api.translators.EventfulApi;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;

public class EventsAPIAdapter {
    
    protected static final EventsAPIInterface currentlyUsedApi = (EventsAPIInterface) new EventfulApi();
    
    public ArrayList<Map> loadEventsByZipcode(String category, int _zipcode) throws JSONException, ProtocolException, IOException{
        return EventsAPIAdapter.currentlyUsedApi.loadEventsByZipcode(category, _zipcode);
    }

  public ArrayList<Map> loadCategories() throws JSONException, ProtocolException, IOException{
        return EventsAPIAdapter.currentlyUsedApi.loadCategories();
    }
    
}
