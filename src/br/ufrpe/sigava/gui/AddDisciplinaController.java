/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class AddDisciplinaController implements Initializable {

    @FXML
    private JFXTextField txt_NomeDisc;
    @FXML
    private DatePicker cal_DataInicioDisc;
    @FXML
    private JFXTextField txt_DuracaoAulaDisc;
    @FXML
    private JFXTextField txt_CargaHorariaDisc;
    @FXML
    private JFXButton btn_AddDisc;
    @FXML
    private JFXButton btn_AddCancelDisc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        Biblioteca.MascaraInteiro(txt_CargaHorariaDisc);
        Biblioteca.MascaraInteiro1(txt_DuracaoAulaDisc);
        Biblioteca.AlteracaoCorMouse(btn_AddDisc);
        Biblioteca.AlteracaoCorMouse(btn_AddCancelDisc);
        btn_AddDisc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IServidorSigava servidor = ServidorSigava.getIstance();
                String nome;
                int cargaHoraria, duracaoAula;
                DayOfWeek diaAula;
                LocalDate dataInicio;
                nome = txt_NomeDisc.getText();
                cargaHoraria = Integer.parseInt(txt_CargaHorariaDisc.getText());
                duracaoAula = Integer.parseInt(txt_DuracaoAulaDisc.getText());
                dataInicio = cal_DataInicioDisc.getValue();
                diaAula = dataInicio.getDayOfWeek();
                try{
                    servidor.cadastrarDisciplina(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);
                    Alert alertDiscCadastrada = new Alert(Alert.AlertType.INFORMATION);
                    alertDiscCadastrada.setContentText("Disciplina cadastrada com sucesso!");
                    alertDiscCadastrada.show();
                    Stage stage = (Stage) btn_AddCancelDisc.getScene().getWindow();
                    stage.close();
                    ADMController.listaDisciplinas();
                }catch(DisciplinaJaExisteException e){
                    Alert alertDiscJaExiste = new Alert(Alert.AlertType.ERROR);
                    alertDiscJaExiste.setContentText(e.getMessage());
                    alertDiscJaExiste.show();
                }
            }
        });
    }
    
    @FXML
      private void cancel_Close(ActionEvent event) {
          Stage stage = (Stage) btn_AddCancelDisc.getScene().getWindow();
          stage.close();
      }
    
}
