/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.interfaces;

import java.util.TreeMap;
import org.json.JSONException;

/**
 *
 * @author Preston.Williamson
 */
public interface MovieAPIInterface {    
    public void submitRequest ();
    public String getRawMovieData ();
    public void setAPIConfigParameter (String _key, String _value);
    public TreeMap <Integer, TreeMap <String, String>> setMovieCollection(TreeMap<Integer, String> _map);
    
    public TreeMap <Integer, String> getGenreMap () throws JSONException;
}