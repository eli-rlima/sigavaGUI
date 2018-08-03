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
    private static final String USER_ADM = "000.000.000-00";
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        String usuario, senha;
        Biblioteca.MarcaraCPF(txt_CPF);
        //Funcionalidades
        
        //usuario = txt_CPF.getText();
        //senha = txt_PASS.getText();
        //Login login = new Login(usuario, senha);
        //Aluno aluno;
        //Professor professor;
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login ERROR");
        Alert alertProf = new Alert(Alert.AlertType.INFORMATION);
        alertProf.setTitle("Login ERROR PROF");
        
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Professor professor = null;
                Aluno aluno = null;
                String usuario, senha;
                ADM adm = new ADM();
                ProfessorTela prof = new ProfessorTela();
                AlunoTela alun = new AlunoTela();
                usuario = txt_CPF.getText();
                System.out.println(usuario);
                senha = txt_PASS.getText();
                Login login = new Login(usuario,senha);
                
                if(usuario.equals(USER_ADM) && senha.equals(LOCK_ADM)){
                    SigavaGUI.fechar();
                    try {
                        adm.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else {
                    try{
                        if (servidor.buscarProfessor(usuario).getLogin().equals(login)){
                        professor = servidor.buscarProfessor(usuario);
                        setProfessor(professor);
                        System.out.println("entrou prof");
                    }

                    }catch(ProfessorNaoExisteException  e1 ){
                        //
                    }try{
                        if (servidor.buscarAluno(usuario).getLogin().equals(login)){
                        System.out.println("entrou aluno");
                        aluno = servidor.buscarAluno(usuario);
                        setAluno(aluno);
                        }
                    }catch (AlunoNaoExisteException e1){
                        //
                    }
                    if(professor != null){
                        SigavaGUI.fechar();
                        try {
                            prof.start(new Stage());
                        } catch (Exception ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(aluno != null){
                        SigavaGUI.fechar();
                        try {
                            alun.start(new Stage());
                        } catch (Exception ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setContentText("Login inválido!");
                        alerta.show();
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
