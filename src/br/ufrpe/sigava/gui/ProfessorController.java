
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class ProfessorController implements Initializable {

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
    private Label label_NomeProfessor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
