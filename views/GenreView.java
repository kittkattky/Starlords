package views;

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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 *
 * @author Preston.Williamson
 */
public class GenreView implements Initializable {
    
    @FXML
    protected Label errorLabel;
    
    @FXML
    protected AnchorPane AnchorPane;
    
    @FXML
    protected ListView checkListGenres;
    
    protected TreeMap <Integer, String> mapGenres;    
    protected MovieController control = new MovieController ();

    public void populateGenreList () {        
        for (int k : this.mapGenres.keySet ()) {            
            CheckBox checkBox = new CheckBox (this.mapGenres.get(k));
            this.checkListGenres.getItems().add(checkBox);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            MovieListView movieView = this.switchScenes(_event, "fxml/MovieList.fxml").getController();
            
            movieView.passUUIDToController(this.control.getUUID ());
            movieView.addMoviesToList (selectedItems);
        }
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
    
    public void passUUIDtoController(String _uuid) {
        this.control.setUUID(_uuid);
    }
}
