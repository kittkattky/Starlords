package api.translators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import api.interfaces.MovieAPIInterface;
import controllers.APIController;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.AppConfigUtil.AppConfigUtil;

/**
 *
 * @author Preston.Williamson
 */
public class MovieAPITranslator implements MovieAPIInterface {
    private final String MOVIE_API_INDICATOR = "api.movie.";
    private final String API_DELIMITER = "%2C%20";
    private final AppConfigUtil config = new AppConfigUtil ("AppConfig.properties");
    
    protected APIController control;
    protected APITranslator model = new APITranslator ();
    protected TreeMap <Integer, TreeMap <String, String>> mapMovieCollection = new TreeMap <> ();
    
    protected String apiReturn;
    
    
    public MovieAPITranslator () {
        this.model = new APITranslator ();
        
        this.model.setURLString (this.getConfigProperty ("url"));
        this.model.setUserKey (this.getConfigProperty ("userKey"));
        this.model.setAPIConfigParameter (this.getConfigProperty ("userKeyAttribute"), this.getConfigProperty ("userKey"));
        this.model.setAPIConfigParameter(this.getConfigProperty ("languageAttribute"), this.getConfigProperty ("language"));
        this.model.setRequestMethod(this.config.getProperty (this.MOVIE_API_INDICATOR + "requestMethod"));
    }
    
    @Override
    public void submitRequest () {
        this.model.setURLString (this.config.getProperty(this.MOVIE_API_INDICATOR + "url") + this.config.getProperty(this.MOVIE_API_INDICATOR + "movieRequestKeyword"));
        this.control = new APIController (this.model);
        this.control.submitAPIRequest(this.model.getRequestMethod(), this.config.getProperty (this.MOVIE_API_INDICATOR + "requestAttribute"));
        
    }
    
    @Override
    public void setAPIConfigParameter (String _key, String val) {
        this.model.setAPIConfigParameter(_key, val);
    }

    @Override
    public TreeMap <Integer,String> getGenreMap () throws JSONException {
        TreeMap <Integer, String> returnMap = new TreeMap <> ();
        String prev = this.model.getURLString();
        String result;
        
        this.model.setURLString (prev + this.getConfigProperty ("genreListKeyword"));
        this.control = new APIController (this.model);
        
        
        this.control.submitAPIRequest(this.model.getRequestMethod(), this.getConfigProperty ("genreResultAttribute"));
        result = "{\"" + this.getConfigProperty ("genreResultAttribute") + "\": [ " + this.getAPIResultString() + "]}";
        JSONArray arr = (JSONArray) new JSONObject (result).get(this.getConfigProperty ("genreResultAttribute"));
        //JSONObject json = new JSONObject ("{\"genres\": [{\"name\":\"Action\",\"id\":28},{\"name\":\"Adventure\",\"id\":12},{\"name\":\"Animation\",\"id\":16},{\"name\":\"Comedy\",\"id\":35},{\"name\":\"Crime\",\"id\":80},{\"name\":\"Documentary\",\"id\":99},{\"name\":\"Drama\",\"id\":18},{\"name\":\"Family\",\"id\":10751},{\"name\":\"Fantasy\",\"id\":14},{\"name\":\"History\",\"id\":36},{\"name\":\"Horror\",\"id\":27},{\"name\":\"Music\",\"id\":10402},{\"name\":\"Mystery\",\"id\":9648},{\"name\":\"Romance\",\"id\":10749},{\"name\":\"Science Fiction\",\"id\":878},{\"name\":\"TV Movie\",\"id\":10770},{\"name\":\"Thriller\",\"id\":53},{\"name\":\"War\",\"id\":10752},{\"name\":\"Western\",\"id\":37}]}");
        
        for (int i = 0; i < arr.length(); i++) {
            JSONObject item = (JSONObject) arr.get(i);
            String name = (String) item.get(this.getConfigProperty ("genreNameAttribute"));
            int id = (int) item.get(this.getConfigProperty ("genreIDAttribute"));
            
            returnMap.put(id, name);
        }
        
        this.model.setURLString (prev);
        return returnMap;
    }
    
    @Override
    public TreeMap <Integer, TreeMap <String, String>> getMovieCollection (TreeMap <Integer, String> _map) {
        this.model.convertAPIConfigParameter(this.config.getProperty(this.MOVIE_API_INDICATOR + "genreRequestKeyword"), _map, this.API_DELIMITER);
        this.submitRequest ();
        
        String results = "{\"" + this.config.getProperty(this.MOVIE_API_INDICATOR + "movieRequestKeyword") + "\": [ " + this.getAPIResultString() + "]}";
        try {
                JSONObject json = new JSONObject (results);
                JSONArray movieData = new JSONArray (json.getString(this.config.getProperty(this.MOVIE_API_INDICATOR + "movieRequestKeyword")));
                
                for (int i = 0; i < movieData.length (); i++) {
                    json = (JSONObject) movieData.get (i);
                    
                    String name = json.getString(this.config.getProperty(this.MOVIE_API_INDICATOR + "titleAttribute"));
                    int id = json.getInt(this.config.getProperty(this.MOVIE_API_INDICATOR + "idAttribute"));
                    String synops = json.getString(this.config.getProperty(this.MOVIE_API_INDICATOR + "synopsisAttribute"));
                    String posterPath = json.getString (this.config.getProperty(this.MOVIE_API_INDICATOR + "posterPath"));
                    
                    this.mapMovieCollection.put(id, new TreeMap <> ());
                    TreeMap <String, String> properties = this.mapMovieCollection.get (id);
                    properties.put(this.config.getProperty(this.MOVIE_API_INDICATOR + "idAttribute"), String.valueOf(id));
                    properties.put (this.config.getProperty(this.MOVIE_API_INDICATOR + "titleAttribute"), name);
                    properties.put (this.config.getProperty(this.MOVIE_API_INDICATOR + "synopsisAttribute"), synops);
                    properties.put (this.config.getProperty(this.MOVIE_API_INDICATOR + "posterPath"), this.config.getProperty(this.MOVIE_API_INDICATOR + "posterDirectory") + posterPath);
                }
                
        } catch (JSONException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.mapMovieCollection;
    }

    @Override
    public String getAPIIndicator() {
        return this.MOVIE_API_INDICATOR;
    }

    @Override
    public String getConfigProperty(String _key) {
        return this.config.getProperty(this.getAPIIndicator() + _key);
    }
    
    @Override
    public String getAPIResultString () {
        return this.control.getAPIResultString();
    }
}