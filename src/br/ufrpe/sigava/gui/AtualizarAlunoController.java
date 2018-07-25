/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class AtualizarAlunoController implements Initializable {

    @FXML
    private JFXTextField txt_NomeAluno;
    @FXML
    private JFXTextField txt_EmailAluno;
    @FXML
    private JFXComboBox<String> combobox_SexoAluno;
    @FXML
    private DatePicker calendar_AddAluno;
    @FXML
    private JFXTextField txt_CPFAluno;
    @FXML
    private JFXTextField txt_SenhaAluno;
    @FXML
    private JFXButton btn_Att;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoAluno.getItems().add(new String ("Masculino"));
        combobox_SexoAluno.getItems().add(new String ("Feminino"));
        Biblioteca.AlteracaoCorMouse(btn_Att);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
    }    

    @FXML
    private void add_Aluno(ActionEvent event) {
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
