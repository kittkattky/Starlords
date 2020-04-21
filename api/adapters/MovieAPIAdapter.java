/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.adapters;

import api.interfaces.MovieAPIInterface;
import api.translators.MovieAPI;
import java.util.TreeMap;
import org.json.JSONException;

/**
 *
 * @author Preston.Williamson
 */
public class MovieAPIAdapter implements MovieAPIInterface {
    protected MovieAPIInterface api = new MovieAPI ();

    @Override
    public TreeMap<Integer, String> getGenreMap() throws JSONException {
        return this.api.getGenreMap();
    }

    @Override
    public String getRawMovieData() {
        return this.api.getRawMovieData();
    }
    
    @Override
    public void submitRequest () {
        this.api.submitRequest();
    }

    @Override
    public TreeMap <Integer, TreeMap <String, String>> setMovieCollection(TreeMap<Integer, String> _map) {
        return this.api.setMovieCollection(_map);
    }

    @Override
    public void setAPIConfigParameter(String _key, String _value) {
        this.api.setAPIConfigParameter(_key, _value);
    }
}
