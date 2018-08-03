package br.ufrpe.sigava.gui; 
 
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaJaExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava; 
import br.ufrpe.sigava.negocio.ServidorSigava; 
import br.ufrpe.sigava.negocio.beans.Disciplina; 
import com.jfoenix.controls.JFXButton; 
import com.jfoenix.controls.JFXTextField; 
import java.net.URL; 
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle; 
import javafx.event.ActionEvent; 
import javafx.fxml.FXML; 
import javafx.fxml.Initializable; 
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker; 
import javafx.stage.Stage; 
 
/** 
 * FXML Controller class 
 * 
 * @author Thiago 
 */ 
public class AddTarefaController implements Initializable { 
    private static Disciplina disciplina; 
    @FXML
    private JFXTextField txt_CodigoTarefa; 
    @FXML 
    private JFXTextField txt_DescricaoTarefa; 
    @FXML 
    private DatePicker calendar_DataFim; 
    @FXML 
    private DatePicker calendar_DataInicio; 
    @FXML 
    private JFXButton btn_Add; 
    @FXML 
    private JFXButton btn_Cancel; 
 
     public static Disciplina getDisciplina(){
        return disciplina;
    }
    
    public void setDisciplina(Disciplina d){
        disciplina = d;
    } 
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        if (ProfessorController.getDisciplina() != null){
            setDisciplina(ProfessorController.getDisciplina());
        }else setDisciplina(TarefasController.getDisciplina());
        
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
        Biblioteca.MascaraInteiro(txt_CodigoTarefa);
    }     
 
    @FXML 
    private void cancel_Close(ActionEvent event) { 
        Stage stage = (Stage) btn_Cancel.getScene().getWindow(); 
        stage.close(); 
    } 
 
    @FXML
    private void add_Tarefa(ActionEvent event) { 
        IServidorSigava servidor = ServidorSigava.getIstance(); 
        String descricao;
        LocalDate dataInicio, dataFim;
        int codigoTarefa;
        Disciplina disciplina;
        if(event.getSource() == btn_Add){
            dataInicio = calendar_DataInicio.getValue();
            dataFim = calendar_DataFim.getValue();
            descricao = txt_DescricaoTarefa.getText();
            codigoTarefa = Integer.parseInt(txt_CodigoTarefa.getText());
            disciplina = AddTarefaController.getDisciplina();
            try{
                Optional<ButtonType> result = null;
                Alert alertCadastro = new Alert(Alert.AlertType.CONFIRMATION);
                alertCadastro.setTitle("CADASTRO");
                alertCadastro.setContentText("Deseja cadastrar a tarefa?");
                result = alertCadastro.showAndWait();
                if(result.get() == ButtonType.OK){
                    servidor.cadastrarTarefa(descricao, dataInicio, dataInicio, codigoTarefa, disciplina);
                    Alert alertCadastrado = new Alert(Alert.AlertType.INFORMATION);
                    alertCadastrado.setTitle("CONFIRMAÇÃO DE CADASTRO");
                    alertCadastrado.setContentText("Tarefa cadastrada com sucesso!");
                    alertCadastrado.show();
                }
            } catch(DisciplinaNaoExisteException e){
                Alert alertDisError = new Alert(Alert.AlertType.ERROR);
                alertDisError.setContentText("Disciplina não existe!");
                alertDisError.show();
            } catch(TarefaJaExisteException e1){
                Alert alertTarefaError = new Alert(Alert.AlertType.ERROR);
                alertTarefaError.setContentText("Tarefa já existe!");
                alertTarefaError.show();
            }
            txt_CodigoTarefa.setText("");
            txt_DescricaoTarefa.setText("");
            calendar_DataInicio.setValue(null);
            calendar_DataFim.setValue(null);
            TarefasController.listaTarefas();
        }
    } 

    @FXML
    private void add_Aluno(ActionEvent event) {
    }
     
} 