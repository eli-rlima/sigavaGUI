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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import br.ufrpe.sigava.negocio.beans.Tarefa;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class TarefasController implements Initializable {
    
    private Disciplina disciplina;

    @FXML
    private VBox vbox_Professor;
    @FXML
    private Pane pane_Default;
    @FXML
    private JFXTextField txt_ProcurarDisciplina;
    @FXML
    private JFXButton btn_CadastrarTarefa;
    @FXML
    private JFXButton btn_RemoverTarefa;
    @FXML
    private JFXButton btn_AtualizarTarefa;
    @FXML
    private TableColumn<Tarefa, String> tb_CellCdg;
    @FXML
    private TableColumn<Tarefa, String> tb_CellDesc;
    @FXML
    private TableView<Tarefa> table_Tarefas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_RemoverTarefa);
        Biblioteca.AlteracaoCorMouse(btn_CadastrarTarefa);
        Biblioteca.AlteracaoCorMouse(btn_AtualizarTarefa);
        disciplina = ProfessorController.getDisciplina();
    }    
    
}
