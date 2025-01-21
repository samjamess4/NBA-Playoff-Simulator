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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import model.Players;

public class PlayerUpdateController extends Controller<Player>  {

    @FXML private Label teamdeets; 

    @FXML private Button add; 

    @FXML private Button update; 
    
    @FXML private Button close; 

    @FXML private Label name; 

    @FXML private Label cred;

    @FXML private Label age;

    @FXML private Label no;
    
    @FXML private TextField pname; 

    @FXML private TextField page; 

    @FXML private TextField pcred; 

    @FXML private TextField pno; 

    private Validator validator = new Validator();

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
        pname.setText(player.getName());
        pcred.setText(String.valueOf(player.getCredit()));
        page.setText(String.valueOf(player.getAge()));
        pno.setText(String.valueOf(player.getNo().toString()));
    }

    @FXML
    public void handleAdd() {
        if (player != null) {
            String playerName = pname.getText();
            double playerCredit = Double.parseDouble(pcred.getText());
            int playerAge = Integer.parseInt(page.getText());
            int playerNo = Integer.parseInt(pno.getText());
    
            
            boolean isValid = validator.isValid(playerName, Double.toString(playerCredit), page.getText(), pno.getText());
            
            if (isValid) {
                Player newPlayer = new Player(playerName, playerCredit, playerAge, playerNo);
    
                Players players = new model.Players();
                players.addPlayer(newPlayer);
    
                Stage stage = (Stage) name.getScene().getWindow();
                stage.close();
            } else {
                // Load and display error view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ErrorView.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    ErrorController errorController = loader.getController();
    errorController.setErrors(playerName, Double.toString(playerCredit), Integer.toString(playerAge), Integer.toString(playerNo));
                    Stage errorStage = new Stage();
                    errorStage.setTitle("Validation Errors");
                    errorStage.setScene(new Scene(root));
                    errorStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}



   

