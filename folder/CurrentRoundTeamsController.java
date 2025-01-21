package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;




public class CurrentRoundTeamsController extends Controller<Season> {

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private TableColumn<Game, String> name1;

    @FXML
    private TableColumn<Game, String> name2;

    @FXML
    private Button closeButton;


    public Season Getseason(){

        return this.model;
    }

    public TableView<Game> getGamesTable() {
        return gamesTable;
    }


    @FXML
    void initialize() {
        Season season = Getseason();
        if (season != null) {
            gamesTable.setItems(season.getCurrentSchedule());
            
            
        } 

    }





@FXML
public void close() {

    stage.close();
}




}



