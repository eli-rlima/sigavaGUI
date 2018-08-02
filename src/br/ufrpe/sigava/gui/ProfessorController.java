
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class ProfessorController implements Initializable {
    
    private Professor professor;
    private static Disciplina disciplina;

    @FXML
    private JFXButton btn_Disciplina;
    @FXML
    private VBox vbox_Professor;
    @FXML
    private Pane pane_Professor;
    @FXML
    private Pane pane_Default;
    @FXML
    private JFXButton btn_Logout;
    @FXML
    private TableView<Disciplina> table_View_Disc;
    @FXML
    private JFXButton btn_ListarTarefas;
    @FXML
    private AnchorPane anchor_Prof;
    @FXML
    private JFXButton btn_AttCad;
    @FXML
    private JFXButton btn_ListarAlunos;
    @FXML
    private TableColumn<Disciplina, String> tb_CellDisc;
    @FXML
    private TableColumn<Disciplina, String> tb_CellCH;
    
    private ObservableList<Disciplina> masterDataD =
            FXCollections.observableArrayList();
    @FXML
    private JFXTextField txt_ProcurarDisciplina;
    @FXML
    private JFXButton btn_NovaTarefa;
    
    public void listaDisciplinas(){
        masterDataD.clear();
        masterDataD.addAll(ServidorSigava.getIstance().listarDisciplinas());
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
        Biblioteca.AlteracaoCorMouse(btn_Logout);
        Biblioteca.AlteracaoCorMouse(btn_ListarTarefas);
        Biblioteca.AlteracaoCorMouse(btn_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_AttCad);
        Biblioteca.AlteracaoCorMouse(btn_ListarAlunos);
        professor = Controller.getProfessor();
        setDisciplina(table_View_Disc.getSelectionModel().getSelectedItem());
        tb_CellCH.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tb_CellDisc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        masterDataD.addAll(professor.getDisciplinas());
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
        table_View_Disc.setItems(sortedDataD.sorted());
        
        btn_AttCad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AttProf attProf = new AttProf();
                try {
                    attProf.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btn_Disciplina){
            table_View_Disc.toFront();
            pane_Professor.toFront();
            vbox_Professor.toFront();
        }
        if(event.getSource() == btn_ListarTarefas){
            TelaTarefa tarefa = new TelaTarefa();
            try {
                tarefa.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
