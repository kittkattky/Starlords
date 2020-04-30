package api.translators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import api.interfaces.MovieAPIInterface;
import controllers.APIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.APIModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.AppConfigUtil.AppConfigUtil;

/**
 *
 * @author Preston.Williamson
 */
public class MovieAPI implements MovieAPIInterface {
    protected APIController control;
    protected APIModel model = new APIModel ();
    private final String MOVIE_API_INDICATOR = "api.movie.";
    protected final String API_DELIMITER = "%2C%20";
    private final AppConfigUtil config = new AppConfigUtil ("AppConfig.properties");
    protected String apiReturn;
    protected TreeMap <Integer, TreeMap <String, String>> mapMovieCollection = new TreeMap <> ();
    
    
    public MovieAPI () {
        this.model = new APIModel ();
        
        this.model.setURLString(this.config.getProperty(this.MOVIE_API_INDICATOR + "url"));
        this.model.setUserKey(this.config.getProperty (this.MOVIE_API_INDICATOR + "userKey"));
        this.model.setAPIConfigParameter(this.config.getProperty (this.MOVIE_API_INDICATOR + "userKeyAttribute"), this.config.getProperty (this.MOVIE_API_INDICATOR + "userKey"));
        this.model.setAPIConfigParameter(this.config.getProperty (this.MOVIE_API_INDICATOR + "languageAttribute"), this.config.getProperty (this.MOVIE_API_INDICATOR + "language"));
        this.model.setRequestMethod(this.config.getProperty (this.MOVIE_API_INDICATOR + "requestMethod"));
    }
    
    @Override
    public TreeMap <Integer, TreeMap <String, String>> setMovieCollection (TreeMap <Integer, String> _map) {
        this.convertAPIConfigParameter(this.config.getProperty(this.MOVIE_API_INDICATOR + "genreRequestKeyword"), _map);
        this.submitRequest ();
        String results = "{\"movies\": [ " + this.getRawMovieData() + "]}";
        System.out.println (results);
        try {
                JSONObject json = new JSONObject (results);
                JSONArray movieData = new JSONArray (json.getString("movies"));
                
                for (int i = 0; i < movieData.length (); i++) {
                    json = (JSONObject) movieData.get (i);
                    String name = json.getString("title");
                    int id = json.getInt("id");
                    String synops = json.getString("overview");
                    String posterPath = json.getString ("poster_path");
                    this.mapMovieCollection.put(id, new TreeMap <> ());
                    TreeMap <String, String> properties = this.mapMovieCollection.get (id);
                    properties.put("id", String.valueOf(id));
                    properties.put ("title", name);
                    properties.put ("overview", synops);
                    properties.put ("poster_path", posterPath);
                }
                
        } catch (JSONException ex) {
            Logger.getLogger(MovieAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.mapMovieCollection;
}
    
    public void convertAPIConfigParameter (String _key, TreeMap <Integer, String> _map) {
        String ret = "";
        
        for (int key : _map.keySet())
            ret = ret.concat(String.valueOf(key) + this.API_DELIMITER);
        
        int delimiterLen = this.API_DELIMITER.length();
        int resultLen = ret.length();
        
        int removeChar = (delimiterLen < resultLen) ? (resultLen - delimiterLen) : delimiterLen;
        this.model.setAPIConfigParameter(_key, ret.substring(0, removeChar));
    }
    
    @Override
    public void submitRequest () {
        this.model.setURLString (this.config.getProperty(this.MOVIE_API_INDICATOR + "url") + this.config.getProperty(this.MOVIE_API_INDICATOR + "movieRequestKeyword"));
        this.control = new APIController (this.model);
        this.control.submitAPIRequest(this.model.getRequestMethod(), this.config.getProperty (this.MOVIE_API_INDICATOR + "requestAttribute"));
        
    }
    
    public TreeMap <Integer,String> getGenreMap () throws JSONException {
        TreeMap <Integer, String> returnMap = new TreeMap <> ();
        String prev = this.model.getURLString();
        this.model.setURLString (prev + this.config.getProperty(this.MOVIE_API_INDICATOR + "listKeyword"));
        this.control = new APIController (this.model);
        
        
        //this.control.submitAPIRequest(this.model.getRequestMethod(), "genres");
        //JSONArray arr = (JSONArray) this.control.getAPIParseObject().get("genres");
        JSONObject json = new JSONObject ("{\"genres\": [{\"name\":\"Action\",\"id\":28},{\"name\":\"Adventure\",\"id\":12},{\"name\":\"Animation\",\"id\":16},{\"name\":\"Comedy\",\"id\":35},{\"name\":\"Crime\",\"id\":80},{\"name\":\"Documentary\",\"id\":99},{\"name\":\"Drama\",\"id\":18},{\"name\":\"Family\",\"id\":10751},{\"name\":\"Fantasy\",\"id\":14},{\"name\":\"History\",\"id\":36},{\"name\":\"Horror\",\"id\":27},{\"name\":\"Music\",\"id\":10402},{\"name\":\"Mystery\",\"id\":9648},{\"name\":\"Romance\",\"id\":10749},{\"name\":\"Science Fiction\",\"id\":878},{\"name\":\"TV Movie\",\"id\":10770},{\"name\":\"Thriller\",\"id\":53},{\"name\":\"War\",\"id\":10752},{\"name\":\"Western\",\"id\":37}]}");
        JSONArray arr = (JSONArray) json.get("genres");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject item = (JSONObject) arr.get(i);
            String name = (String) item.get("name");
            int id = (int) item.get("id");
            
            returnMap.put(id, name);
        }
        
        this.model.setURLString (prev);
        return returnMap;
    }
    
    @Override
    public String getRawMovieData () {
        return this.control.getAPIResultString();
    }
    
    @Override
    public void setAPIConfigParameter (String _key, String val) {
        this.model.setAPIConfigParameter(_key, val);
    }
}
