/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.MovieController;
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
        this.lblSynopsisValue.setWrapText(true);
    }
    
    @FXML
    public void setMovieLabels () {
        String selectedItem = this.listMovies.getSelectionModel().getSelectedItem().toString();
        this.control.setFocusedMovie(this.control.getMovieIDByTitle(selectedItem));        
        this.lblSynopsisValue.setText(this.control.getMovieSynopsis());
        this.lblSynopsisValue.autosize();
        Image image = new Image (this.control.getImageGraphicURL());
        this.imgMovieGraphic.setImage (image);
        this.imgMovieGraphic.autosize();
    }
    
    @FXML
    public void backToGenreScene (ActionEvent _event) throws IOException {
        this.switchScenes(_event, "fxml/GenreList.fxml");
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
    
    /**
     * Helper method for switching scenes based on an ActionEvent
     * @param _event
     * @param fxml
     * @return
     * @throws IOException 
     */
    public FXMLLoader switchScenes(ActionEvent _event, String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        //Create parent object based off of loader that knows which fxml file to create a scene of.
        Parent parentUsingFXML = loader.load();
 
        //create a refernce to the stage that the event is coming from.
        Stage referenceStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        
        //create scene using parent object
        Scene sceneToSwitchTo = new Scene(parentUsingFXML);
        
        //set the scene onto our referenced stage and show it.
        referenceStage.setScene(sceneToSwitchTo);
        referenceStage.show();
        return loader;
    }    
}
