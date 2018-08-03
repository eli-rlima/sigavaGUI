/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

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
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class AttTarefaController implements Initializable {

    //private Tarefa tarefa;
    private Disciplina disciplina;
    
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
        
        //disciplina = ADMController.getDisciplina();
        
        /*
        btn_AttTar.setOnAction(new EventHandler<ActionEvent>() { 
            @Override 
            public void handle(ActionEvent event) { 
              IServidorSigava servidor = ServidorSigava.getIstance();
               int codigoTarefa; String descricao;
               LocalDate dataInicio,dataTermino;
               
               descricao = txt_AttDescricao.getText();
               codigoTarefa = Integer.parseInt(txt_AttCodigoTarefa.getText());
               dataInicio = cal_AttDataInicioTar.getValue();
               dataTermino = cal_AttDataTermino.getValue();
                try{
                    ServidorSigava.getIstance().atualizarTarefa(disciplina, nome, duracaoAula, diaAula, dataInicio, cargaHoraria);
                    ADMController.listaTarefas();
                    Stage stage = (Stage) btn_AttCancelTar.getScene().getWindow();
                    stage.close();
                }catch(TarefaNaoExisteException e){
                    //
                }
                
            } 
        });  
        }

        
    @FXML
    private void cancel_Close(ActionEvent event) {
    }
    }    

    @FXML
    private void cancel_Close(ActionEvent event) {
    }
    
*/
    }