/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class ADMController implements Initializable {
    private static Aluno tbAluno;
    private static Disciplina tbDisciplina;
    private static Professor tbProfessor;
    @FXML
    private Pane pane_Aluno;
    @FXML
    private Pane pane_Disciplina;
    @FXML
    private Pane pane_Professor;
    @FXML
    private JFXButton btn_Aluno;
    @FXML
    private JFXButton btn_Disciplina;
    @FXML
    private JFXButton btn_Professor;
    @FXML
    private VBox vbox_Aluno;
    @FXML
    private JFXButton btn_Atualizar_Aluno;
    @FXML
    private JFXButton btn_Buscar_Aluno;
    @FXML
    private JFXButton btn_Cadastrar_Aluno;
    @FXML
    private JFXButton btn_Remover_Aluno;
    @FXML
    private VBox vbox_Professor;
    @FXML
    private JFXButton btn_Atualizar_Disciplina;
    @FXML
    private JFXButton btn_Buscar_Disciplina;
    @FXML
    private JFXButton btn_Cadastrar_Disciplina;
    @FXML
    private JFXButton btn_Remover_Disciplina;
    @FXML
    private VBox vbox_Disciplina;
    @FXML
    private JFXButton btn_Ass_Aluno_Disc;
    @FXML
    private JFXButton btn_Ass_Prof_Disc;
    @FXML
    private Pane pane_Default;
    @FXML
    private JFXButton btn_Atualizar_Professor;
    @FXML
    private JFXButton btn_Buscar_Professor;
    @FXML
    private JFXButton btn_Cadastrar_Professor;
    @FXML
    private JFXButton btn_Remover_Professor;
    @FXML
    private JFXButton btn_Logout;
    @FXML
    private TableView<Aluno> table_AdmAluno;
    @FXML
    private TableColumn<Aluno, String> tb_CellNameA;
    @FXML
    private TableColumn<Aluno, String> tb_CellCPFA;
    @FXML
    private TableColumn<Aluno, LocalDate> tb_CellDataNascA;
    @FXML
    private TableView<Disciplina> table_AdmDisc;
    @FXML
    private TableColumn<Disciplina, String> tb_CellCH;
    @FXML
    private TableColumn<Disciplina, String> tb_CellNameD;
    @FXML
    private TableView<Professor> table_AdmProfessor;
    @FXML
    private TableColumn<Professor, String> tb_CellNameP;
    @FXML
    private TableColumn<Professor, String> tb_CellCPFP;
    @FXML
    private TableColumn<Professor, String> tb_CellDataNascP;
    @FXML
    private JFXButton btn_AttLista;
    @FXML
    private JFXButton btn_AttListaD;
    @FXML
    private JFXButton btn_AttListaP;
    
    public static void setAluno(Aluno aluno){
        tbAluno = aluno;
    }
    public static Aluno getAluno(){
        return tbAluno;
    }
    public static void setPofessor(Professor professor){
        tbProfessor = professor;
    }
    public static Professor getProfessor(){
        return tbProfessor;
    }
    public static void setDisciplina(Disciplina disciplina){
        tbDisciplina = disciplina;
    }
    public static Disciplina getDisciplina(){
        return tbDisciplina;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tb_CellCPFA.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tb_CellNameA.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellDataNascA.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tb_CellCH.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tb_CellNameD.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellNameP.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellDataNascP.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tb_CellCPFP.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        btn_AttLista.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listaAlunos();
            }
        });
        
        btn_AttListaD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listaDisciplinas();
            }
        });
        
        btn_AttListaP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listaProfessores();
            }
        });
        
        btn_Cadastrar_Aluno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddAluno addAluno = new AddAluno();
                try {
                    addAluno.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_Cadastrar_Professor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddProfessor addProfessor = new AddProfessor();
                try {
                    addProfessor.start(new Stage());

                } catch (Exception ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btn_Atualizar_Professor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AttProfessor attProfessor = new AttProfessor();
                setPofessor(table_AdmProfessor.getSelectionModel().getSelectedItem());
                try {
                    attProfessor.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_Atualizar_Aluno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AttAluno attAluno = new AttAluno();
                setAluno(table_AdmAluno.getSelectionModel().getSelectedItem());
                try {
                    attAluno.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn_Remover_Aluno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IServidorSigava servidor = ServidorSigava.getIstance();
                Aluno aluno = table_AdmAluno.getSelectionModel().getSelectedItem();
                try{
                    if(aluno != null){
                        Alert alertConfRemover = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfRemover.setTitle("REMOVER ALUNO");
                        alertConfRemover.setContentText("Deseja remover o aluno?");
                        Optional<ButtonType> result = alertConfRemover.showAndWait();
                        if(result.get() == ButtonType.OK){
                            servidor.descadastrarAluno(aluno);
                            Alert alertRemovido = new Alert(Alert.AlertType.INFORMATION);
                            alertRemovido.setContentText("Aluno removido com sucesso");
                            alertRemovido.show();
                        }
                    }
                }catch(AlunoNaoExisteException e){
                    Alert alertAlunoNaoEncontrado = new Alert(Alert.AlertType.INFORMATION);
                    alertAlunoNaoEncontrado.setContentText(e.getMessage());
                    alertAlunoNaoEncontrado.show();
                }
            }
        });
        
        btn_Remover_Professor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IServidorSigava servidor = ServidorSigava.getIstance();
                Professor professor = table_AdmProfessor.getSelectionModel().getSelectedItem();
                try{
                    if(professor != null){
                        Alert alertConfRemover = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfRemover.setTitle("REMOVER PROFESSOR");
                        alertConfRemover.setContentText("Deseja remover o professor?");
                        Optional<ButtonType> result = alertConfRemover.showAndWait();
                        if(result.get() == ButtonType.OK){
                            servidor.descadastrarProfessor(professor);
                            Alert alertRemovido = new Alert(Alert.AlertType.INFORMATION);
                            alertRemovido.setContentText("Professor removido com sucesso");
                            alertRemovido.show();
                        }
                    }
                }catch(ProfessorNaoExisteException e){
                    Alert alertAlunoNaoEncontrado = new Alert(Alert.AlertType.INFORMATION);
                    alertAlunoNaoEncontrado.setContentText(e.getMessage());
                    alertAlunoNaoEncontrado.show();
                }
            }
        });

        Biblioteca.AlteracaoCorMouse(btn_Aluno);
        Biblioteca.AlteracaoCorMouse(btn_Ass_Aluno_Disc);
        Biblioteca.AlteracaoCorMouse(btn_Ass_Prof_Disc);
        Biblioteca.AlteracaoCorMouse(btn_Atualizar_Aluno);
        Biblioteca.AlteracaoCorMouse(btn_Atualizar_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Atualizar_Professor);
        Biblioteca.AlteracaoCorMouse(btn_Buscar_Aluno);
        Biblioteca.AlteracaoCorMouse(btn_Buscar_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Buscar_Professor);
        Biblioteca.AlteracaoCorMouse(btn_Cadastrar_Aluno);
        Biblioteca.AlteracaoCorMouse(btn_Cadastrar_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Cadastrar_Professor);
        Biblioteca.AlteracaoCorMouse(btn_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_Logout);
        Biblioteca.AlteracaoCorMouse(btn_Remover_Aluno);
        Biblioteca.AlteracaoCorMouse(btn_Professor);
        Biblioteca.AlteracaoCorMouse(btn_Remover_Disciplina);
        Biblioteca.AlteracaoCorMouse(btn_AttLista);
        Biblioteca.AlteracaoCorMouse(btn_AttListaD);
        Biblioteca.AlteracaoCorMouse(btn_AttListaP);
    }
    
    public void listaAlunos(){
        table_AdmAluno.getItems().clear();
        IServidorSigava servidor = ServidorSigava.getIstance();
        ArrayList<Aluno> alunos = servidor.listarAlunos();
        table_AdmAluno.getItems().addAll(alunos);
    }
    
    public void listaProfessores(){
        table_AdmProfessor.getItems().clear();
        IServidorSigava servidor = ServidorSigava.getIstance();
        ArrayList<Professor> professores = servidor.listarProfessores();
        table_AdmProfessor.getItems().addAll(professores);
    }
    
    public void listaDisciplinas(){
        table_AdmDisc.getItems().clear();
        IServidorSigava servidor = ServidorSigava.getIstance();
        ArrayList<Disciplina> disciplinas = servidor.listarDisciplinas();
        table_AdmDisc.getItems().addAll(disciplinas);
    }
    
    @FXML
    public void handleClicks(ActionEvent event){ 
        if(event.getSource() == btn_Aluno){ 
            pane_Aluno.toFront(); 
            vbox_Aluno.toFront();
            table_AdmAluno.toFront();
            listaAlunos();
        } 
        if(event.getSource() == btn_Professor){ 
            pane_Professor.toFront(); 
            vbox_Professor.toFront(); 
            table_AdmProfessor.toFront();
            listaProfessores();
        } 
        if(event.getSource() == btn_Disciplina){ 
            pane_Disciplina.toFront(); 
            vbox_Disciplina.toFront(); 
            table_AdmDisc.toFront();
            listaDisciplinas();
        } 
    }

    @FXML
    private void handleClicks(MouseEvent event) {
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
