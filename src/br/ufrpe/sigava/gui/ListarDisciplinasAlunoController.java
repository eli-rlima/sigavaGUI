/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteNaDisciplinaException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class ListarDisciplinasAlunoController implements Initializable {

    @FXML
    private TableView<Disciplina> table_AdmDisc;
    @FXML
    private TableColumn<Disciplina, String> tb_CellNameD;
    @FXML
    private TableColumn<Disciplina, String> tb_CellNameProf;
    @FXML
    private TableColumn<Disciplina, Integer> tb_CellCH;
    @FXML
    private TableColumn<Disciplina, Integer> tb_CellQnt;
    @FXML
    private TableColumn<Disciplina, LocalDate> tb_CellDataInicio;
    @FXML
    private TableColumn<Disciplina, LocalDate> tb_CellDataFim;
    @FXML
    private JFXTextField txt_ProcurarDisciplina;
    @FXML
    private JFXButton btn_Remover;
    @FXML
    private JFXButton btn_Cancel;
    
    private static ObservableList<Disciplina> masterDataD =
            FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        tb_CellQnt.setCellValueFactory(new PropertyValueFactory<>("duracaoAula"));
        tb_CellCH.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tb_CellDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        tb_CellDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tb_CellNameProf.setCellValueFactory(new PropertyValueFactory<>("professor"));
        tb_CellNameD.setCellValueFactory(new PropertyValueFactory("nome"));
        masterDataD.clear();
        masterDataD.addAll(ADMController.getAluno().getDisciplinas());
        FilteredList <Disciplina> filteredDataD = new FilteredList<>(masterDataD, d -> true);
            txt_ProcurarDisciplina.textProperty().addListener((observable, oldValue, newValue) ->{
                filteredDataD.setPredicate(d -> {
                    // Se não houver filtro, retorna toda a lista.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                        String lowerCaseFilter = newValue.toLowerCase();

                    if (d.getNome().toLowerCase().contains(lowerCaseFilter)) {
                        return true; //filtro no nome.
                    } else if (d.getProfessor() != null &&
                            d.getProfessor().getNome().toLowerCase().contains(lowerCaseFilter)) {
                        return true; //filtro pelo o nome do Professor.
                    }
                    return false; // Se não houve filtro.
                });
            });
        SortedList <Disciplina> sortedDataD = new SortedList<>(filteredDataD);
        table_AdmDisc.setItems(sortedDataD.sorted());
    }    

    @FXML
    private void remover(ActionEvent event) {
      if (event.getSource() == btn_Remover){
            Disciplina disciplina = table_AdmDisc.getSelectionModel().getSelectedItem();
            Aluno aluno = ADMController.getAluno();
            ServidorSigava servidor = ServidorSigava.getIstance();
                try{
                    boolean rt = servidor.removerAlunoDisciplina(disciplina, aluno) 
                            && servidor.RemoverAluno(disciplina, aluno);
                    if (rt == true){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setContentText("Aluno "+aluno.getNome()+" removido da disciplina "+disciplina.getNome()+"!");
                    alerta.show();
                    ADMController.listaDisciplinas();
                    ADMController.listaAlunos();
                    masterDataD.clear();
                    masterDataD.addAll(ADMController.getAluno().getDisciplinas());}
                }catch(DisciplinaNaoExisteException | AlunoNaoExisteException  e){
                    //Silent
                }catch (AlunoNaoExisteNaDisciplinaException e2){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setContentText("Aluno "+aluno.getNome()+" não existe na disciplina "+disciplina.getNome()+"!");
                    alerta.show();
                }                
                   
        }    
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
