package api.adapters;

/**
 * MovieAPIAdapter class for facilitating communications between the application and external API data for movies.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import api.interfaces.MovieAPIInterface;
import api.translators.MovieAPITranslator;
import java.util.TreeMap;
import org.json.JSONException;

public class MovieAPIAdapter implements MovieAPIInterface {
    protected MovieAPIInterface api = new MovieAPITranslator ();
    
    @Override
    public void submitRequest () {
        this.api.submitRequest ();
    }

    @Override
    public TreeMap <Integer, String> getGenreMap () throws JSONException {
        return this.api.getGenreMap ();
    }

    @Override
    public String getAPIResultString () {
        return this.api.getAPIResultString();
    }
    
    @Override
    public TreeMap <Integer, TreeMap <String, String>> getMovieCollection (TreeMap <Integer, String> _map) {
        return this.api.getMovieCollection (_map);
    }

    @Override
    public void setAPIConfigParameter (String _key, String _value) {
        this.api.setAPIConfigParameter (_key, _value);
    }

    @Override
    public String getAPIIndicator () {
        return this.api.getAPIIndicator ();
    }

    @Override
    public String getConfigProperty (String _key) {
        return this.api.getConfigProperty (_key);
    }
}
