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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author CamilaNunes
 */
public class AssociarProfessorController implements Initializable {

    @FXML
    private TableView<?> table_Aluno;
    @FXML
    private TableColumn<?, ?> tb_CellBoolean;
    @FXML
    private TableColumn<?, ?> tb_CellName;
    @FXML
    private TableColumn<?, ?> tb_CellCPF;
    @FXML
    private JFXTextField txt_ProcurarAluno;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel_Close(ActionEvent event) {
    }
    
}
