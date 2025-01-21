package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExploreTeamsController extends Controller<Teams> {

    @FXML 
    private Button teamsMenuButton; 

    @FXML 
    private Button viewPlayersButton; 

    @FXML 
    private Button closeButton; 

    @FXML
    private void initialize() {
        
    }

    @FXML
    private void teamsMenuAction() {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(new Teams(), "/view/TeamsTable.fxml", "Teams Menu", stage);
        } catch (IOException ex) {
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewPlayersAction() {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(new Teams(), "/view/PlayersView.fxml", "Players Menu", stage);
        } catch (IOException ex) {
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}



