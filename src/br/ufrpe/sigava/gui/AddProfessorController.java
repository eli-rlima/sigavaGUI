/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import br.ufrpe.sigava.gui.Controller;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class AddProfessorController implements Initializable {

    @FXML
    private DatePicker calendar_AddProfessor;
    @FXML
    private JFXTextField txt_EmailProfessor;
    @FXML
    private JFXTextField txt_NomeProfessor;
    @FXML
    private JFXPasswordField passfield_PassProfessor;
    @FXML
    private JFXPasswordField passfield_ConfSenhaProfessor;
    @FXML
    private JFXComboBox<String> combobox_SexoProfessor;
    @FXML
    private JFXTextField txt_CPFProfessor;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoProfessor.getItems().add(new String ("Masculino"));
        combobox_SexoProfessor.getItems().add(new String ("Feminino"));
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
    }    

    @FXML
    private void OnAction_SexoProfessor(ActionEvent event) {
    }

    @FXML
    private void add_Aluno(ActionEvent event) {
         if(event.getSource() == btn_Add){ 
          
        } 
    }

    @FXML
    private void add_Professor(ActionEvent event) {
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
