package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class AddTeamController extends Controller<Teams> {


    @FXML
    private Label details;

    @FXML
    private TextField teamname;

    @FXML
    private Button add2;

    @FXML private Label team; 








    public Teams getTeamslistTeams(){


        return this.model;
    }


    @FXML
public void add() {
    ObservableList<Team> teamsList = getTeamslistTeams().currentTeams; 
   
    String input = teamname.getText();

   
    boolean found = teamsList.stream().anyMatch(team -> input.equalsIgnoreCase(team.getName()));

    if (found) {
        LinkedList<String> errors = new LinkedList<>();
        errors.add("Team name already exists."); 
        String errorMessage = errors.getFirst(); 
        displayErrorMessage(errorMessage); 
        
     
    }
    else {

        Team inputTeam = new Team(input);
        getTeamslistTeams().addTeam(inputTeam);
    }
}

private void displayErrorMessage(String errorMessage) {
    try {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/error.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
        Parent parent = loader.load();
        ErrorController controller = loader.getController();
        controller.display(errorMessage); 
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Error!");
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
}
    
    

