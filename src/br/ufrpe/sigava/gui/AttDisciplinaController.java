/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.DayOfWeek;
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
 * @author CamilaNunes
 */
public class AttDisciplinaController implements Initializable {
    
    private Disciplina disciplina;

    @FXML
    private JFXTextField txt_AttNomeDisc;
    @FXML
    private DatePicker cal_AttDataInicioDisc;
    @FXML
    private JFXTextField txt_AttDuracaoAulaDisc;
    @FXML
    private JFXTextField txt_AttCargaHorariaDisc;
    @FXML
    private JFXButton btn_AttDisc;
    @FXML
    private JFXButton btn_AttCancelDisc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.MascaraInteiro1(txt_AttDuracaoAulaDisc);
        Biblioteca.MascaraInteiro(txt_AttCargaHorariaDisc);
        disciplina = ADMController.getDisciplina();
        txt_AttNomeDisc.setText(disciplina.getNome());
        cal_AttDataInicioDisc.setValue(disciplina.getDataInicio());
        txt_AttDuracaoAulaDisc.setText(Integer.toString(disciplina.getDuracaoAula()));
        txt_AttCargaHorariaDisc.setText(Integer.toString(disciplina.getCargaHoraria()));
        
        btn_AttDisc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IServidorSigava servidor = ServidorSigava.getIstance();
                String nome;
                int cargaHoraria, duracaoAula;
                DayOfWeek diaAula;
                LocalDate dataInicio;
                nome = txt_AttNomeDisc.getText();
                cargaHoraria = Integer.parseInt(txt_AttCargaHorariaDisc.getText());
                duracaoAula = Integer.parseInt(txt_AttDuracaoAulaDisc.getText());
                dataInicio = cal_AttDataInicioDisc.getValue();
                diaAula = dataInicio.getDayOfWeek();
                try{
                    ServidorSigava.getIstance().atualizarDisciplina(disciplina, nome, duracaoAula, diaAula, dataInicio, cargaHoraria);
                    ADMController.listaDisciplinas();
                    Stage stage = (Stage) btn_AttCancelDisc.getScene().getWindow();
                    stage.close();
                }catch(DisciplinaNaoExisteException e){
                    //
                }
                  
            }     
        });
    }    

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_AttCancelDisc.getScene().getWindow();
        stage.close();
    }
    
}
