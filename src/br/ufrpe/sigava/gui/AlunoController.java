/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class AlunoController implements Initializable {

    @FXML
    private AnchorPane anchor_Prof;
    @FXML
    private JFXButton btn_AttCad;
    @FXML
    private JFXButton btn_Disciplina;
    @FXML
    private JFXButton btn_Logout;
    @FXML
    private Pane pane_Default;
    @FXML
    private JFXButton btn_NovaTarefa;
    @FXML
    private VBox vbox_Cronograma;
    @FXML
    private JFXButton btn_CadCrono;
    @FXML
    private JFXButton btn_RemoveCrono;
    @FXML
    private JFXButton btn_Cronograma;
    @FXML
    private TableView<?> table_DiscAluno;
    @FXML
    private VBox vbox_Disciplina;
    @FXML
    private TableView<?> table_Cronograma;
    @FXML
    private Pane pane_Pesquisar;
    @FXML
    private JFXTextField txt_ProcurarDisciplina;
    @FXML
    private Pane pane_DefaultDIsc;
    @FXML
    private StackPane stck_Disc;
    @FXML
    private StackPane stck_Crono;
    @FXML
    private Pane pane_PesqCrono;
    @FXML
    private JFXTextField txt_ProcurarCrono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_AttCad);
        Biblioteca.AlteracaoCorMouse(btn_CadCrono);
        Biblioteca.AlteracaoCorMouse(btn_Cronograma);
        Biblioteca.AlteracaoCorMouse(btn_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Logout);
        Biblioteca.AlteracaoCorMouse(btn_NovaTarefa);
        Biblioteca.AlteracaoCorMouse(btn_RemoveCrono);
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btn_Disciplina){
            pane_Pesquisar.toFront();
            stck_Disc.toFront();
            vbox_Disciplina.toFront();
        }
        if(event.getSource() == btn_Cronograma){
            vbox_Cronograma.toFront();
            stck_Crono.toFront();
            table_Cronograma.toFront();
            pane_PesqCrono.toFront();
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        SigavaGUI sigava = new SigavaGUI();
        Stage stage = (Stage) btn_Logout.getScene().getWindow();
        stage.close();
        try {
            sigava.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
