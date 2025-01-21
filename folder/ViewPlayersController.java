package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Teams;



public class ViewPlayersController extends Controller<Teams> {

    @FXML private TableView<Player> playersview;
    @FXML private TableColumn<Player, String> playername;
    @FXML private TableColumn<Player, String> tname; 
    @FXML private TableColumn<Player,String> pcredit;
    @FXML private TableColumn<Player,String> page;
    @FXML private TableColumn<Player,Integer> pno;
    @FXML private TableColumn<Player,String> plevel;
    @FXML private Label allplayers;
    @FXML private Label flevel;
    @FXML private Label fname;
    @FXML private Label ffrom;
    @FXML private Label ftoo;
    @FXML private TextField slevel;
    @FXML private Button close;
    @FXML private TextField sname;
    @FXML private TextField sfrom;
    @FXML private TextField stoo;
    @FXML private Label filter;

    private ObservableList<Player> filteredPlayers;

    @FXML 
public void initialize() {
    filteredPlayers = FXCollections.observableArrayList();
    playersview.setItems(getpPlayers().allPlayersList());
    tname.setCellValueFactory(cellData -> cellData.getValue().getTeamNameProperty());
    playername.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    pcredit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString());
    plevel.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
    page.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
    pno.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());
    sname.textProperty().addListener((observable, oldValue, newValue) -> handleFilter());
    slevel.textProperty().addListener((observable, oldValue, newValue) -> handleFilter());
    sfrom.textProperty().addListener((observable, oldValue, newValue) -> handleFilter());
    stoo.textProperty().addListener((observable, oldValue, newValue) -> handleFilter());
}


    private Teams getpPlayers() {
        return this.model;
    }

    @FXML
    public void handleFilter() {
        String name = sname.getText().toLowerCase(); 
        String level = slevel.getText().toLowerCase(); 
        int from = 0;
        int to = 0;


        if (!sfrom.getText().isEmpty()) {
            from = Integer.parseInt(sfrom.getText());
        }


        if (!stoo.getText().isEmpty()) {
            to = Integer.parseInt(stoo.getText());
        }

   
        filteredPlayers.clear();

        
        for (Player player : getpPlayers().allPlayersList()) {
            if (player.getName().toLowerCase().contains(name) && 
                player.getLevel().toLowerCase().contains(level) &&
                (from == 0 || player.getAge() >= from) &&
                (to == 0 || player.getAge() <= to)) {
                filteredPlayers.add(player);
            }
        }

        playersview.setItems(filteredPlayers);
    }


    @FXML
    public void closeplayers() {
        if (stage != null) {
            stage.close();
        } else {
            System.out.println("Stage is not initialized!");
        }
    }

}


