package models;

/**
 * MovieModel public class for housing individual movie data.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import api.adapters.MovieAPIAdapter;
import java.util.TreeMap;
import org.json.JSONException;


public class MovieModel {

    protected String movieID;
    protected String movieTitle;
    protected String movieSynopsis;
    protected String graphicURL;
    protected MovieAPIAdapter api = new MovieAPIAdapter ();

    public void submitRequest () {
        this.api.submitRequest();
    }


    //================= GETTERS ===============
    public TreeMap<Integer, String> getGenreMap () throws JSONException {
        return this.api.getGenreMap();
    }

    public String getMovieID () {
        return this.movieID;
    }

    public String getMovieTitle () {
        return this.movieTitle;
    }

    public String getMovieSynopsis () {
        return this.movieSynopsis;
    }

    public String getImageGraphicURL () {
        return this.graphicURL;
    }

    public String getAPIResultString () {
        return this.api.getAPIResultString();
    }

    //================= GETTERS ===============
    public void setMovieID (String _id) {
        this.movieID = _id;
    }

    public void setMovieTitle (String _title) {
        this.movieTitle = _title;
    }

    public void setMovieSynopsis (String _synops) {
        this.movieSynopsis = _synops;
    }

    public void setAPIConfigParameter (String _key, String _value) {
        this.api.setAPIConfigParameter (_key, _value);
    }

    public void setGraphicURL (String _url) {
        this.graphicURL = _url;
    }

    public TreeMap <Integer, TreeMap <String, String>> setMovieCollection (TreeMap <Integer, String> _map) {
        return this.api.getMovieCollection (_map);
    }
}
