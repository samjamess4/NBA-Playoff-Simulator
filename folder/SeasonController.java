package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Game;
import model.Season;

public class SeasonController extends Controller<Season> {

    @FXML
    private Button roundButton;

    @FXML
    private Button currentRoundButton;

    @FXML
    private Button playGamesButton;

    @FXML
    private Button resultButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane buttonGrid;

    private ObservableList<Game> currentSchedule = FXCollections.observableArrayList();

    
    @FXML
private CurrentRoundTeamsController teamsinround;

    private Season getSeason(){

        return this.model;
    }
    

    public ObservableList<Game> getCurrentSchedule() {
        return currentSchedule;
    }

    @FXML
    public void round() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getSeason().getCurrentTeams(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void currentRound() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CurrentRoundTeams.fxml"));
            Parent root = loader.load();
    
          
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
    
            ViewLoader.showStage(model, "/view/CurrentRoundTeams.fxml", "Tournament", stage, null);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    




    

    @FXML
    public void playGames() {
        
    }

    @FXML
    public void result() {
      try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(new Season(), "/view/RecordView.fxml", "Result", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
public void close(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}

}


