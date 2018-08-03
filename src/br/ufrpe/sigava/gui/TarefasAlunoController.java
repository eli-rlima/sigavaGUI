/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class TarefasAlunoController implements Initializable {
    private static Disciplina disciplina;
    @FXML
    private Pane pane_Default;
    @FXML
    private JFXTextField txt_ProcurarDisciplina;
    @FXML
    private TableView<Tarefa> table_Tarefas;
    @FXML
    private TableColumn<Tarefa, String> tb_CellCdg;
    @FXML
    private TableColumn<Tarefa, String> tb_CellDesc;
    
    private static ObservableList<Tarefa> masterDataT =
            FXCollections.observableArrayList();
    
    public void listaTarefas(){
        masterDataT.clear();
        masterDataT.addAll(disciplina.ListarTarefas());
    }
    
    public static void setDisciplina(Disciplina disc){
        disciplina = disc;
    }
    
    public static Disciplina getDisciplina(){
        return disciplina;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDisciplina(AlunoController.getDisciplina());
        tb_CellCdg.setCellValueFactory(new PropertyValueFactory<>("codigoTarefa"));
        tb_CellDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        masterDataT.addAll(getDisciplina().ListarTarefas());
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
