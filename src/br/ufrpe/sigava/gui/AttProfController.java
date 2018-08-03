/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class AttProfController implements Initializable {
    
    private static Professor professor;

    @FXML
    private JFXTextField txt_NomeProf;
    @FXML
    private JFXTextField txt_EmailProf;
    @FXML
    private JFXComboBox<String> combobox_SexoProfessor;
    @FXML
    private DatePicker calendar_AddProf;
    @FXML
    private JFXTextField txt_CPFProf;
    @FXML
    private JFXPasswordField pass_Prof;
    @FXML
    private JFXPasswordField pass_ConfProf;
    @FXML
    private JFXButton btn_Att;
    @FXML
    private JFXButton btn_Cancel;
    
    public static Professor getProfessor(){
        return professor;
    }
    
    public static void setProfessor(Professor prof){
        professor = prof;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoProfessor.getItems().add(new String ("Masculino"));
        combobox_SexoProfessor.getItems().add(new String ("Feminino"));
        Biblioteca.AlteracaoCorMouse(btn_Att);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
        Biblioteca.MarcaraCPF(txt_CPFProf);
        professor = Controller.getProfessor();
        txt_NomeProf.setText(professor.getNome());
        txt_EmailProf.setText(professor.getEmail());
        txt_CPFProf.setText(professor.getCpf());
        calendar_AddProf.setValue(professor.getDataNascimento());
        if(professor.getSexo() == 'm'){
            combobox_SexoProfessor.setValue("Masculino");
        }else combobox_SexoProfessor.setValue("Feminino");
    }    

    @FXML
    private void add_Aluno(ActionEvent event) throws Exception {
        IServidorSigava servidor = ServidorSigava.getIstance();        
        String nome, cpf, email;
        String senha = null;
        char sexo;
        if(event.getSource() == btn_Att){
            LocalDate dataAniversario = calendar_AddProf.getValue();
            nome = txt_NomeProf.getText();
            cpf = txt_CPFProf.getText();
            email = txt_EmailProf.getText();
            if(combobox_SexoProfessor.getSelectionModel().getSelectedItem().equalsIgnoreCase("Masculino")){
                sexo = 'm';
            }else sexo = 'f';
            try{
                if(!pass_Prof.getText().equals(pass_ConfProf.getText())){
                    throw new IllegalAccessError("Senhas não conferem");
                }else if(pass_Prof == null && pass_ConfProf == null){
                    throw new IllegalAccessError("Senhas não informadas");
                }else{
                    Optional<ButtonType> result = null;
                    if(pass_Prof.getText().equals(pass_ConfProf.getText()) && pass_Prof != null && pass_ConfProf != null){
                        Alert alertCadastro = new Alert(Alert.AlertType.CONFIRMATION);
                        alertCadastro.setTitle("ATUALIZAÇÃO");
                        alertCadastro.setContentText("Deseja atualizar o professor?");
                        result = alertCadastro.showAndWait();
                    }
                    if(result.get() == ButtonType.OK){
                        senha = pass_Prof.getText();
                        servidor.atualizarProfessor(professor, cpf, dataAniversario, email, nome, senha, sexo);
                        setProfessor(professor);
                        Alert alertAtualizado = new Alert(Alert.AlertType.INFORMATION);
                        alertAtualizado.setTitle("CONFIRMAÇÃO DE ATUALIZAÇÃO");
                        alertAtualizado.setContentText("Professor atualizado com sucesso!");
                        alertAtualizado.show();
                        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
                        stage.close();
                        ADMController.listaProfessores();
                    }
                }
            }catch(ProfessorNaoExisteException e1){
                Alert alertProfNaoExiste = new Alert(Alert.AlertType.ERROR);
                alertProfNaoExiste.setTitle("Professor não existe");
                alertProfNaoExiste.setContentText(e1.getMessage());
                alertProfNaoExiste.show();
            }catch(IllegalAccessError e2){
                Alert alertSenhaIncorreta = new Alert(Alert.AlertType.ERROR);
                alertSenhaIncorreta.setContentText(e2.getMessage());
                alertSenhaIncorreta.show();
                pass_Prof.setText("");
                pass_ConfProf.setText("");
            }
            ProfessorTela.fechar();
            ProfessorTela profTela = new ProfessorTela();
            profTela.start(new Stage());
        }
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
