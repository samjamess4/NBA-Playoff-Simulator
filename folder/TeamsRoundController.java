package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Game;
import model.Season;
import model.Team;
import model.Teams;

public class TeamsRoundController extends Controller<Season> {

    @FXML
    private TableColumn<Team, String> name;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private Button arrowButton;

    @FXML
    private Button arrangeSeasonButton;

    @FXML
    private Label teamsAddedLabel; 

    



    public TableView<Game> getGamesTable() {
        return gamesTable;
    }
    
    public ObservableList<Game> getGamesData() {
        return gamesTable.getItems();
    }

    public Team getSelectedTeam() {
        return teamsTable.getSelectionModel().getSelectedItem();
    }

  
    private void closeWindow() {
       
        Stage stage = (Stage) arrangeSeasonButton.getScene().getWindow();

        stage.close();
    }

     private void addGameToTable(Game game) {
        gamesTable.getItems().add(game);

        @SuppressWarnings("unchecked")
        TableColumn<Game, String> team1Column = (TableColumn<Game, String>) gamesTable.getColumns().get(1); 
        @SuppressWarnings("unchecked")
        TableColumn<Game, String> team2Column = (TableColumn<Game, String>) gamesTable.getColumns().get(2); 
        @SuppressWarnings("unchecked")
        TableColumn<Game, String> gameColumn = (TableColumn<Game, String>) gamesTable.getColumns().get(0); 
        
        team1Column.setCellValueFactory(cellData -> cellData.getValue().team1());
        team2Column.setCellValueFactory(cellData -> cellData.getValue().team2());

      
    gameColumn.setCellValueFactory(cellData -> {
        int rowIndex = gamesTable.getItems().indexOf(cellData.getValue()) + 1;
        return new SimpleStringProperty(Integer.toString(rowIndex));
    });

    
    

    }

    
    @FXML
    private void initialize() {
        teamsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    gamesTable.setPlaceholder(new Label("No teams added to round"));

  
    
        teamsTable.setItems(new Teams().currentTeams());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        arrowButton.setDisable(true);
        arrangeSeasonButton.setDisable(true);
        
        

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (teamsTable.getSelectionModel().getSelectedItems().size() == 2) {
                arrowButton.setDisable(false);
            } else {
                arrowButton.setDisable(true);
            } 

            handleArrangeSeasonButtonClick(); 
        });

  
        
    }

    @FXML
    private void handleArrowButtonClick() {
        ObservableList<Team> selectedTeams = teamsTable.getSelectionModel().getSelectedItems();
    
        if (selectedTeams.size() != 2) {
       
            return;
        }
    
        Game game = new Game(0); 
    
     
        for (Team team : selectedTeams) {
            game.add(team);
        }
  
        addGameToTable(game);
    
        teamsTable.getItems().removeAll(selectedTeams);
        
    

        teamsTable.getSelectionModel().clearSelection();
    

        arrowButton.setDisable(true);

    
    }



    @FXML
    private void handleArrangeSeasonButtonClick() {

        if (teamsTable.getItems().isEmpty()) {
     
            arrangeSeasonButton.setDisable(false);

        
        } else {
    
            arrangeSeasonButton.setDisable(true);
        }
  
        if (arrangeSeasonButton.isArmed()) {
    

      
        closeWindow();
    }
  }
  
  


}

