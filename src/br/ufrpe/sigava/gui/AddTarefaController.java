package br.ufrpe.sigava.gui; 
 
import br.ufrpe.sigava.negocio.IServidorSigava; 
import br.ufrpe.sigava.negocio.ServidorSigava; 
import br.ufrpe.sigava.negocio.beans.Disciplina; 
import com.jfoenix.controls.JFXButton; 
import com.jfoenix.controls.JFXTextField; 
import java.net.URL; 
import java.util.ResourceBundle; 
import javafx.event.ActionEvent; 
import javafx.fxml.FXML; 
import javafx.fxml.Initializable; 
import javafx.scene.control.DatePicker; 
import javafx.stage.Stage; 
 
/** 
 * FXML Controller class 
 * 
 * @author Thiago 
 */ 
public class AddTarefaController implements Initializable { 
    private Disciplina disciplina; 
    @FXML 
    private JFXTextField txt_CodigoTarefa; 
    @FXML 
    private JFXTextField txt_DescricaoTarefa; 
    @FXML 
    private DatePicker calendar_DataFim; 
    @FXML 
    private DatePicker calendar_DataInicio; 
    @FXML 
    private JFXTextField txt_DisciplinaTarefa; 
    @FXML 
    private JFXButton btn_Add; 
    @FXML 
    private JFXButton btn_Cancel; 
 
    /** 
     * Initializes the controller class. 
     */ 
    @Override 
    public void initialize(URL url, ResourceBundle rb) { 
        disciplina = ProfessorController.getDisciplina(); 
    }     
 
 
    @FXML 
    private void cancel_Close(ActionEvent event) { 
        Stage stage = (Stage) btn_Cancel.getScene().getWindow(); 
        stage.close(); 
    } 
 
    @FXML 
    private void add_Aluno(ActionEvent event) { 
        IServidorSigava servidor = ServidorSigava.getIstance(); 
    } 
     
} 