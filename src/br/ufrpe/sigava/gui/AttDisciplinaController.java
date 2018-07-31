/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    }    

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_AttCancelDisc.getScene().getWindow();
        stage.close();
    }
    
}
