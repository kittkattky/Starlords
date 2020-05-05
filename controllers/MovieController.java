package controllers;

/**
 * MovieAPITranslator public class for housing movie data.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import java.util.TreeMap;
import models.MovieModel;
import org.json.JSONException;

public class MovieController {
    protected TreeMap <Integer, MovieModel> movieCollection = new TreeMap <> ();
    protected TreeMap <Integer, String> mapSelectedGenres = new TreeMap <> ();
    protected MovieModel focusedMovie = new MovieModel ();
    public UUIDController uuidController = new UUIDController ();
    
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
        
        //get movie collection by genres.
        TreeMap <Integer, TreeMap <String, String>> mapCollection = this.setMovieCollection(this.getGenreSelection ());
        
        for (int key : mapCollection.keySet()) {
            MovieModel temp = new MovieModel ();
            TreeMap <String, String> mapMovies = mapCollection.get(key);
            
            //extract movie data.
            for (String keyStr : mapMovies.keySet()) {
                temp.setMovieID(mapMovies.get("id"));
                temp.setMovieTitle(mapMovies.get("title"));
                temp.setMovieSynopsis(mapMovies.get("overview"));
                temp.setGraphicURL(mapMovies.get ("poster_path"));
            }
            
            //insert movie into collection.
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
    
    public String getUUID() {
        return this.uuidController.getUUID();
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
}
