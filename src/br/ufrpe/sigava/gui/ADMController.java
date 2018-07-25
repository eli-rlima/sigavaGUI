/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.gui.AddAluno;
import br.ufrpe.sigava.gui.AddProfessor;
import br.ufrpe.sigava.gui.SigavaGUI;
import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class ADMController implements Initializable {

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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
               try {
                   attAluno.start(new Stage());
               } catch (Exception ex) {
                   Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    
    @FXML
    public void handleClicks(ActionEvent event){ 
        if(event.getSource() == btn_Aluno){ 
           
            pane_Aluno.toFront(); 
            vbox_Aluno.toFront(); 
        } 
        if(event.getSource() == btn_Professor){ 
            
            pane_Professor.toFront(); 
            vbox_Professor.toFront(); 
        } 
        if(event.getSource() == btn_Disciplina){ 
            
            pane_Disciplina.toFront(); 
            vbox_Disciplina.toFront(); 
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
