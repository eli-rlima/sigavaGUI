
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class DesktopController implements Initializable {

    @FXML
    private Button btn_Aluno;
    @FXML
    private Button btn_Professor;
    @FXML
    private Button btn_Disciplina;
    @FXML
    private AnchorPane AnchorPane_Main;
    @FXML
    private VBox VBox_Main;
    @FXML
    private Pane Pane_Sigava;
    @FXML
    private Label Label_Sigava;
    @FXML
    private Label Label_Disciplina;
    @FXML
    private Label Professor;
    private Pane Pane_Aluno;
    @FXML
    private Label Label_Aluno;
    @FXML
    private VBox VBox_Aluno;
    @FXML
    private Button btn_Aluno_Cadastrar;
    @FXML
    private Button btn_Aluno_Remover;
    @FXML
    private Button btn_Aluno_Atualizar;
    @FXML
    private Button btn_Aluno_Procurar;
    @FXML
    private GridPane grid_Disciplina;
    @FXML
    private TableView<?> table_Aluno2;
    @FXML
    private TableColumn<?, ?> table_Aluno_Nome2;
    @FXML
    private TableColumn<?, ?> table_Aluno_CPF2;
    @FXML
    private TableColumn<?, ?> table_Aluno_Nascimento2;
    @FXML
    private TableColumn<?, ?> table_Aluno_Email2;
    @FXML
    private TableColumn<?, ?> table_Aluno_CPF22;
    @FXML
    private TableColumn<?, ?> table_Aluno_Senha2;
    @FXML
    private GridPane grid_Professor;
    @FXML
    private TableView<?> table_Professor;
    @FXML
    private TableColumn<?, ?> table_Professor_Nome;
    @FXML
    private TableColumn<?, ?> table_Professor_CPF;
    @FXML
    private TableColumn<?, ?> table_Professor_Nascimento;
    @FXML
    private TableColumn<?, ?> table_Professor_Email;
    @FXML
    private TableColumn<?, ?> table_Professor_Senha;
    @FXML
    private GridPane grid_Aluno;
    @FXML
    private TableView<?> table_Aluno;
    @FXML
    private TableColumn<?, ?> table_Aluno_Nome;
    @FXML
    private TableColumn<?, ?> table_Aluno_CPF;
    @FXML
    private TableColumn<?, ?> table_Aluno_Nascimento;
    @FXML
    private TableColumn<?, ?> table_Aluno_Email;
    @FXML
    private TableColumn<?, ?> table_Aluno_Senha;
    @FXML
    private Pane pane_Disciplina;
    @FXML
    private Pane pane_Professor;
    @FXML
    private Pane pane_Aluno;
    @FXML
    private VBox VBox_Professor;
    @FXML
    private Button btn_Professor_Cadastrar;
    @FXML
    private Button btn_Professor_Remover;
    @FXML
    private Button btn_Professor_Atualizar;
    @FXML
    private Button btn_Professor_Procurar;
    @FXML
    private VBox VBox_Disciplina;
    @FXML
    private Button btn_Disciplina_Cadastrar;
    @FXML
    private Button btn_Disciplina_Remover;
    @FXML
    private Button btn_Disciplina_Atualizar;
    @FXML
    private Button btn_Disciplina_Procurar;
    @FXML
    private Button btn_Disciplina_AssociarP;
    @FXML
    private Button btn_Disciplina_AssociarA;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    @FXML
    public void handleClicks(ActionEvent event){
        if(event.getSource() == btn_Aluno){
            grid_Aluno.toFront();
            pane_Aluno.toFront();
            VBox_Aluno.toFront();
        }
        if(event.getSource() == btn_Professor){
            grid_Professor.toFront();
            pane_Professor.toFront();
            VBox_Professor.toFront();
        }
        if(event.getSource() == btn_Disciplina){
            grid_Disciplina.toFront();
            pane_Disciplina.toFront();
            VBox_Disciplina.toFront();
        }
    }

    @FXML
    private void ClickProfessor(ActionEvent event) {
        if (event.getSource() == btn_Professor_Atualizar){
            System.out.println("Professor_Att");
        }
        if (event.getSource() == btn_Professor_Cadastrar){
            System.out.println("Professor_C");
        }
        if (event.getSource() == btn_Professor_Remover){
            System.out.println("P_R");
        }
        if (event.getSource() == btn_Professor_Procurar){
            System.out.println("P_P");
            
        }
    }
    
    @FXML
    private void ClickDisciplina(ActionEvent event) {
        if (event.getSource() == btn_Disciplina_AssociarA){
            System.out.println("D_AA");
        }
        if (event.getSource() == btn_Disciplina_AssociarP){
            System.out.println("D_AP");
        }
        if (event.getSource() == btn_Disciplina_Atualizar){
            System.out.println("D_A");
        }
        if (event.getSource() == btn_Disciplina_Remover){
            System.out.println("D_R");
        }
        if (event.getSource() == btn_Disciplina_Cadastrar){
            System.out.println("D_C");
        }
        if (event.getSource() == btn_Disciplina_Procurar){
             System.out.println("D_P");
        }
        
    
    }

    @FXML
    private void ClickAluno(ActionEvent event) {
        if (event.getSource() == btn_Aluno_Atualizar){
            System.out.println("A_A");
            
        }
        if (event.getSource() == btn_Aluno_Remover){
            System.out.println("A_R");
            
        }
        if (event.getSource() == btn_Aluno_Procurar){
            System.out.println("A_P");
            
        }
        if (event.getSource() == btn_Aluno_Cadastrar){
            System.out.println("A_C");
            
        }
    
    }
    
}