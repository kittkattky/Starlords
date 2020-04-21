/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.MovieController;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.MovieModel;
import org.json.JSONException;

/**
 *
 * @author Preston.Williamson
 */
public class MovieListView implements Initializable {
    
    @FXML
    protected Label lblWhy, lblWhyValue, lblMovieTitle, lblMovieTitleValue, lblSynopsis, lblCast, lblCastValue;
    
    @FXML
    protected TextArea lblSynopsisValue;
    
    @FXML
    protected ImageView imgMovieGraphic;
    
    @FXML
    protected ListView listMovies;
    
    protected MovieController control = new MovieController ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void setMovieLabels () {
        String selectedItem = this.listMovies.getSelectionModel().getSelectedItem().toString();
        this.control.setFocusedMovie(this.control.getMovieIDByTitle(selectedItem));        
        this.lblSynopsisValue.setText(this.control.getMovieSynopsis());
        System.out.println ("https://image.tmdb.org/t/p/original" + this.control.getImageGraphicURL());
        Image image = new Image ("https://image.tmdb.org/t/p/original" + this.control.getImageGraphicURL());
        this.imgMovieGraphic.setImage (image);
        this.imgMovieGraphic.autosize();
    }
    
    @FXML
    public void backToGenreScene () {
        
    }
    
    public void addMoviesToList(TreeMap <Integer, String> _map) throws JSONException {
        this.control.setGenreMap(_map);
        TreeMap <Integer, MovieModel> mapMovies = this.control.setMovieCollection();
        for (int key : mapMovies.keySet()) {
            this.listMovies.getItems().add(mapMovies.get(key).getMovieTitle());
        }
    }
    
    public void setGenreMap (TreeMap <Integer, String> _map) {
        this.control.setGenreMap(_map);
    }
    
    public void passUUIDToController (String _uuid) {
        this.control.setUUID(_uuid);
    }
    
    public TreeMap <Integer, String> getGenreMap () throws JSONException {
        return this.control.getGenreMap();
    }
}
