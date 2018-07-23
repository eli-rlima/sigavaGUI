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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elive
 */
public class Controller implements Initializable {
    
    public static boolean IS_ALUNO;
    public static boolean IS_PROFESSOR;
    private static final String USER_ADM = "admin";
    private static final String LOCK_ADM = "admin";

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
        //Dimensionamento
        
       /* Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        btn_login.setLayoutX(screenDimension.getWidth()/2);
        btn_login.setLayoutY(screenDimension.getHeight()/1.85);
        btn_CancelLogin.setLayoutX(screenDimension.getWidth()/1.8);
        btn_CancelLogin.setLayoutY(screenDimension.getHeight()/1.85);
        txt_CPF.setLayoutX(screenDimension.getWidth()/2);
        txt_CPF.setLayoutY(screenDimension.getHeight()/2.5);
        txt_PASS.setLayoutX(screenDimension.getWidth()/2);
        txt_PASS.setLayoutY(screenDimension.getHeight()/2.2);
        icon_User.setLayoutX(screenDimension.getWidth()/2.15);
        icon_User.setLayoutY(screenDimension.getHeight()/2.3);
        icon_UserLock.setLayoutX(screenDimension.getWidth()/2.15);
        icon_UserLock.setLayoutY(screenDimension.getHeight()/2.05);
        */
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
            professor = servidor.buscarProfessor(usuario);
            if(aluno.getLogin().equals(login)){
                this.isAluno(aluno);
            }else{
                if(professor.getLogin().equals(login)){
                    this.isProfessor(professor);
                }
            }  
        }catch(AlunoNaoExisteException e){
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
        }catch(ProfessorNaoExisteException e1){
            alertProf.setHeaderText(null);
            alertProf.setContentText(e1.getMessage());
        }
        
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usuario, senha;
                ADM adm = new ADM();
                usuario = txt_CPF.getText();
                senha = txt_PASS.getText();
                
                if(usuario.equalsIgnoreCase(USER_ADM) && senha.equalsIgnoreCase(LOCK_ADM)){
                    SigavaGUI.fechar();
                    try {
                        adm.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(!IS_ALUNO){
                        alert.show();
                    }else if(!IS_PROFESSOR){
                        alertProf.show();
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
        
        
    }    


    
}
