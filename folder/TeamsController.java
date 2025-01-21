package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams> {

    @FXML
    private Button add;

    @FXML
    private Button manage;

    @FXML
    private Button close;

    @FXML
    private Button delete;

    @FXML
    private  TableView<Team> teamtv;

    @FXML
    private TableColumn<Team, String> name; 

    @FXML 
    private TableColumn<Team, String> numberofPlayers;

    @FXML

    private TableColumn<Team, String> avgPlayerCred;


    @FXML
    private TableColumn<Team, String>  avgAge;

    @FXML private Label allteams;


    public Teams getTeamslistTeams() {
        return this.model;
    }

    public  Team getSelectedTeam() {
        return teamtv.getSelectionModel().getSelectedItem();
    }


    boolean hasSelectedTeam() {
        return teamtv.getSelectionModel().getSelectedItem() != null;
    }
    

    
    @FXML 
    public void initialize() {
        teamtv.setItems(getTeamslistTeams().currentTeams());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        numberofPlayers.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asString());
        avgPlayerCred.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asString());
        avgAge.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asString());
        delete.disableProperty().bind(teamtv.getSelectionModel().selectedItemProperty().isNull());
        manage.disableProperty().bind(teamtv.getSelectionModel().selectedItemProperty().isNull());
        add.disableProperty().bind(teamtv.getSelectionModel().selectedItemProperty().isNotNull());

    }

   @FXML
   private void AddTeam() {
       try {
           Stage stage = new Stage();
           stage.getIcons().add(new Image("/view/nba.png"));
           ViewLoader.showStage(getTeamslistTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
       } catch (IOException ex) {
           Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }


   @FXML
   private void manage() {
       Team selectedTeam = getSelectedTeam();
      
       if (hasSelectedTeam()) {
           try {
               Stage stage = new Stage();
               stage.getIcons().add(new Image("/view/nba.png"));
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ManageTeamView.fxml"));
               Parent root = loader.load();
               
               ManageTeamController controller = loader.getController();
               controller.displayTeam(selectedTeam);
               

               
               Scene scene = new Scene(root);
               stage.setTitle("Teams Menu");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   


    @FXML 

    private void close(){
        stage.close();
    }


    @FXML
private void delete() {
    if (hasSelectedTeam()) {
        getTeamslistTeams().remove(getSelectedTeam());
    }
}


}

