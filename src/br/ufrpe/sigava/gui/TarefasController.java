/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import static br.ufrpe.sigava.gui.TarefasAlunoController.getDisciplina;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    private static ObservableList<Tarefa> masterDataT =
            FXCollections.observableArrayList();
    
    public void listaTarefas(){
        masterDataT.clear();
        masterDataT.addAll(disciplina.ListarTarefas());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_RemoverTarefa);
        Biblioteca.AlteracaoCorMouse(btn_CadastrarTarefa);
        Biblioteca.AlteracaoCorMouse(btn_AtualizarTarefa);
        disciplina = ProfessorController.getDisciplina();
        tb_CellCdg.setCellValueFactory(new PropertyValueFactory<>("codigoTarefa"));
        tb_CellDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        masterDataT.addAll(disciplina.ListarTarefas());
        FilteredList <Tarefa> filteredDataT = new FilteredList<>(masterDataT, t -> true);
            txt_ProcurarDisciplina.textProperty().addListener((observable, oldValue, newValue) ->{
                filteredDataT.setPredicate(t -> {
                    // Se não houver filtro, retorna toda a lista.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                        String lowerCaseFilter = newValue.toLowerCase();
                    if (Integer.toString(t.getCodigoTarefa()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; //filtro no nome.
                    } 
                    return false; // Se não houve filtro.
                });
            });
        SortedList <Tarefa> sortedDataT = new SortedList<>(filteredDataT);
        table_Tarefas.setItems(sortedDataT.sorted());
    }    
    
}
