/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.TreeMap;
import java.util.TreeSet;
import models.MovieModel;
import org.json.JSONException;
import utilities.AppConfigUtil.AppConfigUtil;

/**
 *
 * @author Preston.Williamson
 */
public class MovieController {
    protected TreeMap <Integer, MovieModel> movieCollection = new TreeMap <> ();
    protected String uuid = "test";
    protected TreeMap <Integer, String> mapSelectedGenres = new TreeMap <> ();
    protected MovieModel focusedMovie = new MovieModel ();
    
    public TreeMap <Integer, String> getGenreMap () throws JSONException {
        return this.focusedMovie.getGenreMap ();
    }
    
    public TreeMap <Integer, String> getGenreSelection () {
        return this.mapSelectedGenres;
    }
    
    public int getMovieIDByTitle (String _movieTitle) {
        for (int key : this.movieCollection.keySet()) {
            if (movieCollection.get(key).getMovieTitle().compareToIgnoreCase(_movieTitle) == 0)
                return Integer.parseInt(movieCollection.get(key).getMovieID());
        }
        
        return -1;
    }
    
    public TreeMap <Integer, MovieModel> setMovieCollection () {
        this.movieCollection.clear();
        
        TreeMap <Integer, TreeMap <String, String>> mapCollection = this.setMovieCollection(this.getGenreSelection ());
        
        for (int key : mapCollection.keySet()) {
            MovieModel temp = new MovieModel ();
            TreeMap <String, String> mapMovies = mapCollection.get(key);
            
            for (String keyStr : mapMovies.keySet()) {
                temp.setMovieID(mapMovies.get("id"));
                temp.setMovieTitle(mapMovies.get("title"));
                temp.setMovieSynopsis(mapMovies.get("overview"));
                temp.setGraphicURL(mapMovies.get ("poster_path"));
            }
            
            this.movieCollection.put(key, temp);
        }
        
        return this.movieCollection;
    }
    
    public TreeMap <Integer, TreeMap <String, String>> setMovieCollection (TreeMap <Integer, String> _map) {
        return this.focusedMovie.setMovieCollection(_map);
    }
    
    public String getImageGraphicURL () {
        return this.focusedMovie.getImageGraphicURL();
    }
    
    public String getMovieID () {        
        return this.focusedMovie.getMovieID();
    }
    
    public String getMovieTitle () {
        return this.focusedMovie.getMovieTitle();
    }
    
    public String getMovieSynopsis () {
        return this.focusedMovie.getMovieSynopsis ();
    }
    
    public String getUUID () {
        return this.uuid;
    }
    
    public void setFocusedMovie (MovieModel _movieModel) {
        this.focusedMovie = _movieModel;
    }
    
    public void setFocusedMovie (int _movieID) {
        try {
            MovieModel toFocus = this.movieCollection.get(_movieID);
            this.focusedMovie = toFocus;
        } catch (Exception ex){
            
        }
    }
    
    public void setMovieTitle (String _title) {
        this.focusedMovie.setMovieTitle(_title);
    }

    public void setMovieSynopsis (String _synops) {
        this.focusedMovie.setMovieSynopsis(_synops);
    }
    
    public void setGenreMap (TreeMap <Integer, String> _map) {
        this.mapSelectedGenres = _map;
    }
    
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }
}
