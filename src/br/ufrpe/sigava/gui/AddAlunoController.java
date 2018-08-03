/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.gui.ADMController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
        Biblioteca.MarcaraCPF(txt_CPFAluno);
    }    
    
    
    
    @FXML
    private void add_Aluno(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();        
        String nome, cpf, email;
        String senha = null;
        char sexo;
        if(event.getSource() == btn_Add){
            LocalDate dataAniversario = calendar_AddAluno.getValue();
            nome = txt_NomeAluno.getText();
            cpf = txt_CPFAluno.getText();
            email = txt_EmailAluno.getText();
            if(combobox_SexoAluno.getSelectionModel().getSelectedItem().getText().equalsIgnoreCase("Masculino")){
                sexo = 'm';
            }else sexo = 'f';
            try{
                if(!passfield_SenhaAluno.getText().equals(passfield_ConfSenhaAluno.getText())){
                    throw new IllegalAccessError("Senhas não conferem!");
                }else if(passfield_SenhaAluno.getText().equals("") || passfield_ConfSenhaAluno.getText().equals("")){
                    throw new IllegalAccessError("Senhas não informadas!");
                }else{
                    Optional<ButtonType> result = null;
                    if(passfield_SenhaAluno.getText().equals(passfield_ConfSenhaAluno.getText())){
                        Alert alertCadastro = new Alert(Alert.AlertType.CONFIRMATION);
                        alertCadastro.setTitle("CADASTRO");
                        alertCadastro.setContentText("Deseja cadastrar o aluno?");
                        result = alertCadastro.showAndWait();
                    }
                    if(result.get() == ButtonType.OK){
                        senha = passfield_SenhaAluno.getText();
                        servidor.cadastrarAluno(nome, email, sexo, dataAniversario, senha, cpf);
                        Alert alertCadastrado = new Alert(Alert.AlertType.INFORMATION);
                        alertCadastrado.setTitle("CONFIRMAÇÃO DE CADASTRO");
                        alertCadastrado.setContentText("Aluno cadastrado com sucesso!");
                        Optional<ButtonType> result1 = alertCadastrado.showAndWait();
                        calendar_AddAluno.setValue(null);
                        txt_CPFAluno.setText("");
                        txt_EmailAluno.setText("");
                        txt_NomeAluno.setText("");
                        passfield_SenhaAluno.setText("");
                        passfield_ConfSenhaAluno.setText("");
                        combobox_SexoAluno.setValue(null);
                        ADMController.listaAlunos();
                    }
                }
            }catch(AlunoJaExisteException e){
                Alert alertAlunoJaExiste = new Alert(Alert.AlertType.WARNING);
                alertAlunoJaExiste.setTitle("ALUNO JÁ EXISTE");
                alertAlunoJaExiste.setContentText(e.getMessage());
                Optional<ButtonType> result = alertAlunoJaExiste.showAndWait();
                if(result.get() == ButtonType.OK){
                    calendar_AddAluno.setValue(null);
                    txt_CPFAluno.setText("");
                    txt_EmailAluno.setText("");
                    txt_NomeAluno.setText("");
                    passfield_SenhaAluno.setText("");
                    passfield_ConfSenhaAluno.setText("");
                    combobox_SexoAluno.setValue(null);
                }
            }catch(IllegalArgumentException e1){
                Alert alertInvalido = new Alert(Alert.AlertType.ERROR);
                alertInvalido.setTitle("Erro no cadastro");
                alertInvalido.setContentText(e1.getMessage());
                Optional<ButtonType> result1 = alertInvalido.showAndWait();
                if(result1.get() == ButtonType.OK){
                    calendar_AddAluno.setValue(null);
                    txt_CPFAluno.setText("");
                    txt_EmailAluno.setText("");
                    txt_NomeAluno.setText("");
                    passfield_SenhaAluno.setText("");
                    passfield_ConfSenhaAluno.setText("");
                    combobox_SexoAluno.setValue(null);
                }
            }catch(IllegalAccessError e2){
                Alert alertSenhaIncorreta = new Alert(Alert.AlertType.ERROR);
                alertSenhaIncorreta.setTitle("SENHAS DIFERENTES");
                alertSenhaIncorreta.setContentText(e2.getMessage());
                alertSenhaIncorreta.show();
                passfield_SenhaAluno.setText("");
                passfield_ConfSenhaAluno.setText("");
            }
        } 
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
}
