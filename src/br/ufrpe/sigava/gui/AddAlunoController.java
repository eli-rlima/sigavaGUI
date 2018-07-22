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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class AddAlunoController implements Initializable {

    @FXML
    private DatePicker calendar_AddAluno;
    @FXML
    private JFXTextField txt_EmailAluno;
    @FXML
    private JFXTextField txt_NomeAluno;
    @FXML
    private JFXPasswordField passfield_SenhaAluno;
    @FXML
    private JFXPasswordField passfield_ConfSenhaAluno;
    @FXML
    private JFXComboBox<Label> combobox_SexoAluno;
    @FXML
    private JFXTextField txt_CPFAluno;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoAluno.getItems().add(new Label ("Masculino"));
        combobox_SexoAluno.getItems().add(new Label ("Feminino"));
 
    }    

    @FXML
    private void add_Aluno(ActionEvent event) {
        if(event.getSource() == btn_Cancel){ 
           System.exit(0);
        } 
        
        if(event.getSource() == btn_Add){ 
          
        } 
    
    }

    @FXML
    private void OnAction_SexoAluno(ActionEvent event) {

    }
    
}
