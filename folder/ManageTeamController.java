package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Team;

public class ManageTeamController extends Controller<Team> {

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableView<Team> team;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, Double> creditColumn;

    @FXML
    private TableColumn<Player, Integer> ageColumn;

    @FXML
    private TableColumn<Player, Integer> numberColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveAndCloseButton;

    @FXML
    private TextField TEAMTXT;

    @FXML
    private Label teamlabel;

    private Team currentTeam;

    public void setCurrentTeam(Team team) {
        this.currentTeam = team;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public Button getAddButton() {
        return addButton;
    }

    boolean hasSelectedPlayer() {
        return playerTable.getSelectionModel().getSelectedItem() != null;
    }

    public Player getSelectedPlayer() {
        return playerTable.getSelectionModel().getSelectedItem();
    }

    public void displayTeam(Team selectedTeam) {
        playerTable.setItems(selectedTeam.getCurrentPlayers());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        creditColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asObject());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asObject());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());
        TEAMTXT.textProperty().bind(selectedTeam.nameProperty());
    }

    @FXML
    private void addPlayer() {
        if (!hasSelectedPlayer()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerUpdateView.fxml"));
                Parent root = loader.load();
                PlayerUpdateController playerUpdateController = loader.getController();
                playerUpdateController.handleAdd();

                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/nba.png"));
                stage.setTitle("Add Player");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void update() {
        if (hasSelectedPlayer()) {
            try {
                Player selectedPlayer = getSelectedPlayer();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerUpdateView.fxml"));
                Parent root = loader.load();

                PlayerUpdateController playerUpdateController = loader.getController();
                playerUpdateController.setPlayer(selectedPlayer);

                Stage stage = new Stage();
                stage.setTitle("Update Player");
                stage.getIcons().add(new Image("/view/nba.png"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
private void saveAndClose() {
    Stage stage = (Stage) saveAndCloseButton.getScene().getWindow();
    stage.close();
}

    @FXML
    private void deletePlayer() {
        Player selectedPlayer = getSelectedPlayer();
        if (selectedPlayer != null) {
            playerTable.getItems().remove(selectedPlayer);
        }
    }
}




     
   






