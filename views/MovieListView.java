package views;

/**
 * MovieListView public class for rendering movie data UI.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import controllers.MovieController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.MovieModel;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

public class MovieListView implements Initializable {
    
    @FXML
    protected Label lblMovieTitle, lblMovieTitleValue, lblSynopsis;
    
    @FXML
    protected TextArea lblSynopsisValue;
    
    @FXML
    protected ImageView imgMovieGraphic;
    
    @FXML
    protected ListView listMovies;
    
    public MovieController movieController = new MovieController ();
    public EventHandlers handler = new EventHandlers ();    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lblSynopsisValue.setWrapText(true);
    }
    
    @FXML
    public void backToGenreScene (ActionEvent _event) throws IOException {
        GenreView view = this.handler.switchScenes(_event, "fxml/GenreList.fxml").getController ();
        view.movieController.uuidController.setUUID(this.movieController.uuidController.getUUID());
    }
    
    public void addMoviesToList(TreeMap <Integer, String> _map) throws JSONException {
        this.movieController.setGenreMap(_map);
        TreeMap <Integer, MovieModel> mapMovies = this.movieController.setMovieCollection();
        for (int key : mapMovies.keySet()) {
            this.listMovies.getItems().add(mapMovies.get(key).getMovieTitle());
        }
    }
    
    public TreeMap <Integer, String> getGenreMap () throws JSONException {
        return this.movieController.getGenreMap();
    }    
    
    public void setGenreMap (TreeMap <Integer, String> _map) {
        this.movieController.setGenreMap(_map);
    }
    
    @FXML
    public void setMovieLabels () {
        String selectedItem = this.listMovies.getSelectionModel().getSelectedItem().toString();
        this.movieController.setFocusedMovie(this.movieController.getMovieIDByTitle(selectedItem)); 
        this.lblMovieTitleValue.setText (this.movieController.getMovieTitle());
        this.lblSynopsisValue.setText(this.movieController.getMovieSynopsis());
        this.lblSynopsisValue.autosize();
        Image image = new Image (this.movieController.getImageGraphicURL());
        this.imgMovieGraphic.setImage (image);
        this.imgMovieGraphic.autosize();
    }
}
