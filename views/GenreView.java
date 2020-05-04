package views;

import controllers.MovieController;
import controllers.UUIDController;
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
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import utilities.Homepage.EventHandlers;

/**
 *
 * @author Preston.Williamson
 */
public class GenreView implements Initializable {
    
    @FXML
    protected Label errorLabel;
    
    @FXML
    protected AnchorPane anchorPane;
    
    @FXML
    protected ListView checkListGenres;
    
    protected TreeMap <Integer, String> mapGenres;    
    protected MovieController control = new MovieController ();
    public UUIDController uuidControl = new UUIDController ();
    protected EventHandlers handler = new EventHandlers ();

    public void populateGenreList () {        
        for (int k : this.mapGenres.keySet ()) {            
            CheckBox checkBox = new CheckBox (this.mapGenres.get(k));
            this.checkListGenres.getItems().add(checkBox);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.handler.setBackground(this.anchorPane);
        try {
            this.mapGenres = this.control.getGenreMap();
        } catch (JSONException ex) {
            Logger.getLogger(GenreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.populateGenreList();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent _event) throws IOException, JSONException {
        TreeMap <Integer, String> selectedItems = this.getGenreSelection ();
        
        if (selectedItems.isEmpty()) {
            this.errorLabel.setText ("Please check at least one genre");
        }
        else{
            this.errorLabel.setText ("");
            MovieListView movieView = this.handler.switchScenes(_event, "fxml/MovieList.fxml").getController();
            
            movieView.passUUIDToController(this.control.getUUID ());
            movieView.addMoviesToList (selectedItems);
        }
    }
    
    @FXML
    private void handleBackButtonAction (ActionEvent _event) throws IOException {
        this.handler.switchScenes (_event, "fxml/HomePage.fxml");
    }
    
    public TreeMap <Integer, String> getGenreSelection () {
        TreeMap <Integer, String> selectedItems = new TreeMap <> ();
        
        TreeSet <String> selectedGenres = new TreeSet <> ();
        ObservableList <Node> components = this.checkListGenres.getItems();
        
        for (Node component : components) {
            if (component instanceof CheckBox) {
                if (((CheckBox) component).isSelected())
                    selectedGenres.add(((CheckBox) component).getText());
            }                    
        }
        
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
    
    public void passUUIDtoController(String _uuid) {
        this.control.setUUID(_uuid);
    }
}
