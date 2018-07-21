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

/**
 * FXML Controller class
 *
 * @author elive
 */
public class Controller implements Initializable {

    @FXML
    private AnchorPane pane_Login;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXButton btn_CancelLogin;
    @FXML
    private JFXButton btn_Cadastrar;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        btn_login.setLayoutX(screenDimension.getWidth()/2);
        btn_login.setLayoutY(screenDimension.getHeight()/1.85);
        btn_CancelLogin.setLayoutX(screenDimension.getWidth()/1.8);
        btn_CancelLogin.setLayoutY(screenDimension.getHeight()/1.85);
        btn_Cadastrar.setLayoutX(screenDimension.getWidth()/1.1);
        btn_Cadastrar.setLayoutY(screenDimension.getHeight()/20);
        txt_CPF.setLayoutX(screenDimension.getWidth()/2);
        txt_CPF.setLayoutY(screenDimension.getHeight()/2.5);
        txt_PASS.setLayoutX(screenDimension.getWidth()/2);
        txt_PASS.setLayoutY(screenDimension.getHeight()/2.2);
        icon_User.setLayoutX(screenDimension.getWidth()/2.15);
        icon_User.setLayoutY(screenDimension.getHeight()/2.3);
        icon_UserLock.setLayoutX(screenDimension.getWidth()/2.15);
        icon_UserLock.setLayoutY(screenDimension.getHeight()/2.05);
    }    
    
}
