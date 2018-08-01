
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    private TableView<String> table_View_Disc;
    @FXML
    private JFXButton btn_NovaTarefa;
    @FXML
    private JFXButton btn_ListarTarefas;
    @FXML
    private AnchorPane anchor_Prof;
    @FXML
    private JFXButton btn_AttCad;
    @FXML
    private Text txt_NomeProf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca.AlteracaoCorMouse(btn_Logout);
        Biblioteca.AlteracaoCorMouse(btn_ListarTarefas);
        Biblioteca.AlteracaoCorMouse(btn_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_NovaTarefa);
        Biblioteca.AlteracaoCorMouse(btn_AttCad);
        professor = Controller.getProfessor();
        txt_NomeProf.setText("Bem vindo, " + professor.getNome());
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btn_Disciplina){
            table_View_Disc.toFront();
            pane_Professor.toFront();
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
