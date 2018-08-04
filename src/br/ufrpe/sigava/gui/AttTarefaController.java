/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class AttTarefaController implements Initializable {

    //private Tarefa tarefa;
    private Disciplina disciplina;
    private Tarefa tarefa;
    @FXML
    private JFXTextField txt_AttDescricao;
    @FXML
    private DatePicker cal_AttDataInicioTar;
    @FXML
    private JFXTextField txt_AttCodigoTarefa;
    @FXML
    private JFXButton btn_AttTar;
    @FXML
    private JFXButton btn_AttCancelDisc;
    @FXML
    private DatePicker cal_AttDataTermino;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_AttTar);
        Biblioteca.AlteracaoCorMouse(btn_AttCancelDisc);
        tarefa = TarefasController.getTarefa();
        txt_AttCodigoTarefa.setText(Integer.toString(tarefa.getCodigoTarefa()));
        txt_AttDescricao.setText(tarefa.getDescricao());
        cal_AttDataInicioTar.setValue(tarefa.getDataInicio());
        cal_AttDataTermino.setValue(tarefa.getDataTermino());
    }

        
    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_AttCancelDisc.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void atualizar(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        if(event.getSource() == btn_AttTar){
            int codigoTarefa;
            String descricao;
            LocalDate dataInicio, dataTermino;
            descricao = txt_AttDescricao.getText();
            codigoTarefa = Integer.parseInt(txt_AttCodigoTarefa.getText());
            dataInicio = cal_AttDataInicioTar.getValue();
            dataTermino = cal_AttDataTermino.getValue();
            try{
                servidor.atualizarTarefa(tarefa, descricao, dataInicio, dataTermino, codigoTarefa);
                Alert alertAtt = new Alert(Alert.AlertType.INFORMATION);
                alertAtt.setContentText("Atualizado com sucesso!");
            }catch(TarefaNaoExisteException e){
                Alert alertAtt = new Alert(Alert.AlertType.ERROR);
                alertAtt.setContentText(e.getMessage());
            }
        }
    }
}