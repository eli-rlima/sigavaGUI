/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private static Aluno aluno;
    private static Disciplina disciplina;
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
    private VBox vbox_Cronograma;
    @FXML
    private JFXButton btn_CadCrono;
    @FXML
    private JFXButton btn_RemoveCrono;
    @FXML
    private JFXButton btn_Cronograma;
    @FXML
    private TableView<Disciplina> table_DiscAluno;
    @FXML
    private VBox vbox_Disciplina;
    @FXML
    private TableView<Cronograma> table_Cronograma;
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
    @FXML
    private TableColumn<Disciplina, String> tb_CellDisc;
    @FXML
    private TableColumn<Disciplina, String> tb_CellCH;
    @FXML
    private TableColumn<Disciplina, String> tb_CellCrono;
    @FXML
    private JFXButton btn_ListarTarefas;
    
    private static ObservableList<Disciplina> masterDataD =
            FXCollections.observableArrayList();
    
    public void listaDisciplinas(){
        masterDataD.clear();
        masterDataD.addAll(Controller.getAluno().getDisciplinas());
    }
    
    public static void setDisciplina(Disciplina disc){
        disciplina = disc;
    }
    
    public static Disciplina getDisciplina(){
        return disciplina;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_AttCad);
        Biblioteca.AlteracaoCorMouse(btn_CadCrono);
        Biblioteca.AlteracaoCorMouse(btn_Cronograma);
        Biblioteca.AlteracaoCorMouse(btn_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Logout);
        Biblioteca.AlteracaoCorMouse(btn_ListarTarefas);
        Biblioteca.AlteracaoCorMouse(btn_RemoveCrono);
        aluno = Controller.getAluno();        
        tb_CellDisc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellCrono.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellCH.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        
        masterDataD.addAll(aluno.getDisciplinas());
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
                    } 
                    return false; // Se não houve filtro.
                });
            });
        SortedList <Disciplina> sortedDataD = new SortedList<>(filteredDataD);
        table_DiscAluno.setItems(sortedDataD.sorted());
        
        btn_ListarTarefas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TarefasAluno tar = new TarefasAluno();
                setDisciplina(table_DiscAluno.getSelectionModel().getSelectedItem());
                if(getDisciplina() != null){
                    try {
                        tar.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btn_Disciplina){
            pane_Pesquisar.toFront();
            stck_Disc.toFront();
            vbox_Disciplina.toFront();
            listaDisciplinas();
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
