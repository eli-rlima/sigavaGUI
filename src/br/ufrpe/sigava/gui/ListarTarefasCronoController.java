/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.dados.RepositorioTarefa;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class ListarTarefasCronoController implements Initializable {

    @FXML
    private TableView<Tarefa> table_TarefaCrono;
    @FXML
    private TableColumn<Tarefa, String> tb_CellCdg;
    @FXML
    private TableColumn<Tarefa, String> tb_CellDesc;
    @FXML
    private TableColumn<Tarefa, String> tb_CellDataIni;
    @FXML
    private TableColumn<Marcacao, String> tb_CellDataFim;
    @FXML
    private JFXTextField txt_ProcurarTarefa;

    private static ObservableList<Tarefa> masterDataT =
            FXCollections.observableArrayList();
    @FXML
    private JFXButton btn_Fechar;
    
    public ArrayList<Tarefa> tarefas(){
        ArrayList<Tarefa> tarefas = new ArrayList();
        ArrayList<Marcacao> marcacoes = Controller.getAluno().buscarCronograma(AlunoController.getCronograma().getNome()).marcacoes();
        for(int i = 0; i < Controller.getAluno().getDisciplinas().size(); i++){
            for(int j = 0; j < marcacoes.size(); j++){
                if(Controller.getAluno().getDisciplinas().get(i).equals(RepositorioTarefa.getInstance().buscar(marcacoes.get(j).getCodigoTarefa()))){
                    tarefas.add(RepositorioTarefa.getInstance().buscar(marcacoes.get(j).getCodigoTarefa()));
                }
            }
        }
        return tarefas;
    }
    
    public void listaTarefas(){
        masterDataT.clear();
        masterDataT.addAll();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_Fechar);
        tb_CellCdg.setCellValueFactory(new PropertyValueFactory<>("codigoTarefa"));
        tb_CellDataFim.setCellValueFactory(new PropertyValueFactory<>("dataTermino"));
        tb_CellDataIni.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tb_CellDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        masterDataT.addAll(tarefas());
        FilteredList <Tarefa> filteredDataT = new FilteredList<>(masterDataT, t -> true);
            txt_ProcurarTarefa.textProperty().addListener((observable, oldValue, newValue) ->{
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
        table_TarefaCrono.setItems(sortedDataT.sorted());
        listaTarefas();
    }    


    @FXML
    private void cancel_Close(ActionEvent event) {
    }
    
}
