/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.CronogramaNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class TarefasCronoController implements Initializable {
    private Aluno aluno;
    private static Tarefa tarefa;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;
    @FXML
    private TableView<Tarefa> table_TarefaCrono;
    @FXML
    private TableColumn<Tarefa, String> tb_CellCdg;
    @FXML
    private TableColumn<Tarefa, String> tb_CellDesc;
    @FXML
    private JFXTextField txt_ProcurarTarefa;
    
    private static ObservableList<Tarefa> masterDataT =
            FXCollections.observableArrayList();
    @FXML
    private JFXTextField cal_DataFimTar;
    @FXML
    private DatePicker cal_DataFimAluno;
    
    public ArrayList<Tarefa> tarefas(){
        ArrayList<Tarefa> tarefas = new ArrayList();
        for(int i = 0; i < Controller.getAluno().getDisciplinas().size(); i++){
            tarefas.addAll(Controller.getAluno().getDisciplinas().get(i).ListarTarefas());
        }
        return tarefas;
    }
    
    public void listaTarefas(){
        masterDataT.clear();
        masterDataT.addAll(tarefas());
    }
    
    public static void setTarefa(Tarefa tar){
        tarefa = tar;
    }
    public static Tarefa getTarefa(){
        return tarefa;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
        aluno = Controller.getAluno();
        tb_CellCdg.setCellValueFactory(new PropertyValueFactory<>("codigoTarefa"));
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
    private void adicionar(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        setTarefa(table_TarefaCrono.getSelectionModel().getSelectedItem());
        cal_DataFimTar.setText(getTarefa().getDataTermino().toString());
        String nomeDisc, nomeCrono;
        if(getTarefa() != null){
            try{
                nomeDisc = getTarefa().getDisciplina().getNome();
                nomeCrono = AlunoController.getCronograma().getNome();
                Aluno aluno = Controller.getAluno();
                int codigoTarefa = getTarefa().getCodigoTarefa();
                LocalDate dataFimAluno = cal_DataFimAluno.getValue();
                servidor.adicionarMarcacao(nomeDisc, nomeCrono, aluno, codigoTarefa, dataFimAluno);
                Alert alertCadastro = new Alert(Alert.AlertType.INFORMATION);
                alertCadastro.setContentText("Tarefa adicionada!");
                alertCadastro.show();
            }catch(AlunoNaoExisteException e){

            }catch(CronogramaNaoExisteException e1){

            }catch(DisciplinaNaoExisteException e2){

            }catch(TarefaNaoExisteException e3){

            }
            listaTarefas();
        }
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
