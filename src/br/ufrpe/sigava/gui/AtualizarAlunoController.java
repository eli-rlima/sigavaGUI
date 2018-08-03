/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import com.jfoenix.controls.JFXPasswordField;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class AtualizarAlunoController implements Initializable {
    private Aluno aluno;
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
    private JFXButton btn_Att;
    @FXML
    private JFXButton btn_Cancel;
    @FXML
    private JFXPasswordField passfield_SenhaAluno;
    @FXML
    private JFXPasswordField passfield_ConfSenhaAluno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoAluno.getItems().add(new String ("Masculino"));
        combobox_SexoAluno.getItems().add(new String ("Feminino"));
        Biblioteca.AlteracaoCorMouse(btn_Att);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
        Biblioteca.MarcaraCPF(txt_CPFAluno);
        aluno = ADMController.getAluno();
        txt_CPFAluno.setText(aluno.getCpf());
        txt_EmailAluno.setText(aluno.getEmail());
        txt_NomeAluno.setText(aluno.getNome());
        if(aluno.getSexo() == 'm'){
            combobox_SexoAluno.setValue("Masculino");
        }else combobox_SexoAluno.setValue("Feminino");
        calendar_AddAluno.setValue(aluno.getDataNascimento());
    }    

    @FXML
    private void add_Aluno(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();        
        String nome, cpf, email;
        String senha = null;
        char sexo;
        if(event.getSource() == btn_Att){
            LocalDate dataAniversario = calendar_AddAluno.getValue();
            nome = txt_NomeAluno.getText();
            cpf = txt_CPFAluno.getText();
            email = txt_EmailAluno.getText();
            if(combobox_SexoAluno.getSelectionModel().getSelectedItem().equalsIgnoreCase("Masculino")){
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
                        Alert alertConfAtualizado = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfAtualizado.setTitle("ATUALIZAÇÃO");
                        alertConfAtualizado.setContentText("Deseja atualizar o aluno?");
                        result = alertConfAtualizado.showAndWait();
                    }
                    if(result.get() == ButtonType.OK){
                        senha = passfield_SenhaAluno.getText();
                        servidor.atualizarAluno(aluno, nome, email, cpf, senha, sexo, dataAniversario);                        
                        Alert alertAtualizado = new Alert(Alert.AlertType.INFORMATION);
                        alertAtualizado.setTitle("CONFIRMAÇÃO DE ATUALIZAÇÃO");
                        alertAtualizado.setContentText("Aluno atualizado com sucesso!");
                        Optional<ButtonType> result1 = alertAtualizado.showAndWait();
                        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
                        stage.close();
                        ADMController.listaAlunos();
                    }
                }
            }catch(AlunoNaoExisteException e1){
                Alert alertAlunoNaoExiste = new Alert(Alert.AlertType.ERROR);
                alertAlunoNaoExiste.setTitle("Aluno já existe");
                alertAlunoNaoExiste.setContentText(e1.getMessage());
                alertAlunoNaoExiste.show();
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
