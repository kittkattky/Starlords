package views;

/**
 *
 * @author Preston.Williamson
 */

import controllers.MovieController;
import controllers.UUIDController;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.MovieModel;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

public class MovieListView implements Initializable {
    
    @FXML
    protected Label lblWhy, lblWhyValue, lblMovieTitle, lblMovieTitleValue, lblSynopsis, lblCast, lblCastValue;
    
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
        System.out.println (this.movieController.uuidController.getUUID());
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
    
    @FXML
    public void backToGenreScene (ActionEvent _event) throws IOException {
        System.out.println (this.movieController.uuidController.getUUID());
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
    
    public void setGenreMap (TreeMap <Integer, String> _map) {
        this.movieController.setGenreMap(_map);
    }

    public TreeMap <Integer, String> getGenreMap () throws JSONException {
        return this.movieController.getGenreMap();
    }
}
