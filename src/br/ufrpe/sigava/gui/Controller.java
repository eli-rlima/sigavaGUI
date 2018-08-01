/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Pessoa;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.negocio.beans.Login;
import br.ufrpe.sigava.gui.ADM;
import br.ufrpe.sigava.gui.ADM;
import br.ufrpe.sigava.gui.ADM;
import br.ufrpe.sigava.gui.SigavaGUI;
import br.ufrpe.sigava.gui.SigavaGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class Controller implements Initializable {
    private static Aluno alunoC;
    private static Professor professorC;
    public static boolean IS_ALUNO;
    public static boolean IS_PROFESSOR;
    private static final String USER_ADM = "admin";
    private static final String LOCK_ADM = "admin";
    private static final String USER_PROF = "prof";
    private static final String LOCK_PROF = "prof";

    @FXML
    private AnchorPane pane_Login;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXButton btn_CancelLogin;
    @FXML
    private JFXTextField txt_CPF;
    @FXML
    private JFXPasswordField txt_PASS;
    @FXML
    private Label txt_SigavaLogin;
    @FXML
    private FontAwesomeIconView icon_User;
    @FXML
    private FontAwesomeIconView icon_UserLock;

    /**
     * Initializes the controller class.
     */
    
    public static Aluno getAluno(){
        return alunoC;
    }
    
    public static void setAluno(Aluno aluno){
        alunoC = aluno;
    }
    
    public static Professor getProfessor(){
        return professorC;
    }
    
    public static void setProfessor(Professor professor){
        professorC = professor;
    }
    
    public boolean isProfessor(Object o){
        if(o.getClass().equals(Professor.class)){
            IS_PROFESSOR = true;
        }
        return IS_PROFESSOR;
    }
    public boolean isAluno(Object o){
        if(o.getClass().equals(Aluno.class)){
            IS_ALUNO = true;
        }
        return IS_ALUNO;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        String usuario, senha;
        //Funcionalidades
        
        usuario = txt_CPF.getText();
        senha = txt_PASS.getText();
        Login login = new Login(usuario, senha);
        Aluno aluno;
        Professor professor;
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login ERROR");
        Alert alertProf = new Alert(Alert.AlertType.INFORMATION);
        alertProf.setTitle("Login ERROR PROF");
        
        try{
            aluno = servidor.buscarAluno(usuario);
            if(aluno.getLogin().equals(login)){
                this.isAluno(aluno);
                setAluno(aluno);
            }
        }catch(AlunoNaoExisteException e){
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
        }
        
        
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Professor professor = null;
                String usuario, senha;
                ADM adm = new ADM();
                ProfessorTela prof = new ProfessorTela();
                usuario = txt_CPF.getText();
                System.out.println(usuario);
                senha = txt_PASS.getText();
                
                if(usuario.equals(USER_ADM) && senha.equals(LOCK_ADM)){
                    SigavaGUI.fechar();
                    try {
                        adm.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(usuario.equals(USER_PROF) && senha.equals(LOCK_PROF)){
                    SigavaGUI.fechar();
                    try {
                        prof.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try{
                    professor = servidor.buscarProfessor(usuario);
                    setProfessor(professor);
                }catch(ProfessorNaoExisteException e1){
                    alertProf.setHeaderText(null);
                    alertProf.setContentText(e1.getMessage());
                }
                if(professor != null){
                    SigavaGUI.fechar();
                    try {
                        prof.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
                
        
        btn_CancelLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SigavaGUI.fechar();
            }
        });
        
        Biblioteca.AlteracaoCorMouse(btn_login);
        Biblioteca.AlteracaoCorMouse(btn_CancelLogin);
    }
}
