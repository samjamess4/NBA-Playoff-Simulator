package controller;

import java.util.LinkedList;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class ErrorController extends Controller<Validator> {


    @FXML private Label message; 


    @FXML private Text yellow; 

    @FXML private Button okay;

    @FXML private Text blue;

    private Validator geValidator(){


        return this.model;
    }

    public void display(String errorMessage) {
        yellow.setText(errorMessage);
        
    }

    public void setErrors(String name, String credit, String age, String no) {
      
        geValidator().generateErrors(name, credit, age, no);

       
        LinkedList<String> errors = geValidator().errors();
        StringBuilder errorMessage = new StringBuilder();
        for (String error : errors) {
            errorMessage.append(error).append("\n");
        }
        blue.setText(errorMessage.toString());
    }


    
}
