/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.CronogramaNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class CronogramaController implements Initializable {

    @FXML
    private JFXTextField txt_NomeCrono;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
    }    

    @FXML
    private void adicionar(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        String nome;
        if(event.getSource() == btn_Add){
            nome = txt_NomeCrono.getText();
            try{
                servidor.adicionarCronograma(Controller.getAluno(), nome);
                Alert alertCadastro = new Alert(Alert.AlertType.INFORMATION);
                alertCadastro.setContentText("Cronograma adicionado!");
                alertCadastro.show();
                AlunoController.listaCronogramas();
            }catch(AlunoNaoExisteException e){
                Alert alertErroAlun = new Alert(Alert.AlertType.ERROR);
                alertErroAlun.setContentText("Aluno não existe!");
                alertErroAlun.show();
            }catch(CronogramaNaoExisteException e1){
                Alert alertErroCron = new Alert(Alert.AlertType.ERROR);
                alertErroCron.setContentText("Cronograma não existe!");
                alertErroCron.show();
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
