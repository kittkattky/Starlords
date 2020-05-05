package views;

/**
 * GenreView public class for rendering genre list UI into view.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import controllers.MovieController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

public class GenreView implements Initializable {
    
    @FXML
    protected Label errorLabel;
    
    @FXML
    protected AnchorPane anchorPane;
    
    @FXML
    protected ListView checkListGenres;
    
    protected TreeMap <Integer, String> mapGenres;    
    public MovieController movieController = new MovieController ();
    protected EventHandlers handler = new EventHandlers ();

    public void populateGenreList () {        
        for (int k : this.mapGenres.keySet ()) {            
            CheckBox checkBox = new CheckBox (this.mapGenres.get(k));
            this.checkListGenres.getItems().add(checkBox);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.mapGenres = this.movieController.getGenreMap();
        } catch (JSONException ex) {
            Logger.getLogger(GenreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.populateGenreList();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent _event) throws IOException, JSONException {
        TreeMap <Integer, String> selectedItems = this.getGenreSelection ();
        
        if (selectedItems.isEmpty()) {
            //error handling if user did not select anything.
            this.errorLabel.setTextFill(Paint.valueOf("#FF0000"));
            this.errorLabel.setText ("Please check at least one genre");
            this.errorLabel.setAlignment(Pos.CENTER);
        }
        else{
            this.errorLabel.setText ("");
            
            //switch to movie list.
            MovieListView movieView = this.handler.switchScenes(_event, "fxml/MovieList.fxml").getController();            
            movieView.movieController.uuidController.setUUID(this.movieController.uuidController.getUUID ());
            movieView.addMoviesToList (selectedItems);
        }
    }
    
    @FXML
    private void handleBackButtonAction (ActionEvent _event) throws IOException {
        HomePageView view = this.handler.switchScenes (_event, "fxml/HomePage.fxml").getController ();
        view.handler.uuidController.setUUID(this.movieController.uuidController.getUUID());
    }
    
    public TreeMap <Integer, String> getGenreSelection () {
        TreeMap <Integer, String> selectedItems = new TreeMap <> ();
        
        TreeSet <String> selectedGenres = new TreeSet <> ();
        ObservableList <Node> components = this.checkListGenres.getItems();
        
        //for loop to create checkboxes on the grid.
        for (Node component : components) {
            if (component instanceof CheckBox) {
                if (((CheckBox) component).isSelected())
                    selectedGenres.add(((CheckBox) component).getText());
            }                    
        }
        
        //for loop to extract selected genres.
        for (String item : selectedGenres) {
            for (int key : this.mapGenres.keySet()) {
                String curr = this.mapGenres.get(key);
                if (item.compareToIgnoreCase(curr) == 0) {
                    selectedItems.put(key, item);
                    break;
                }
            }
        }
        
        return selectedItems;
    }
}
