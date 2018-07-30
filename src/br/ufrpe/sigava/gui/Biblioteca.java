/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author elive
 */
public class Biblioteca {
    
    public static void AlteracaoCorMouse(JFXButton b){
        b.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setStyle("-fx-background-color: #808080;");
            }
        });
        
        b.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setStyle("-fx-background-color: #000000;");
            }
        });
    }
    public static void MarcaraCPF(JFXTextField txt){
        txt.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
            String mascara = "###.###.###-##";
            String alphaAndDigits = txt.getText().replaceAll("[\\-\\.]","");
            StringBuilder resultado = new StringBuilder();
            int i = 0;
            int quant = 0;

            if (number2.intValue() > number.intValue()) {
                if (txt.getText().length() <= mascara.length()) {
                    while (i<mascara.length()) {
                        if (quant < alphaAndDigits.length()) {
                            if ("#".equals(mascara.substring(i,i+1))) {
                                resultado.append(alphaAndDigits.substring(quant,quant+1));
                                quant++;
                            } else {
                               resultado.append(mascara.substring(i,i+1));
                            }
                        }
                    i++;    
                    }
                    txt.setText(resultado.toString());
                }
                if (txt.getText().length() > mascara.length()) {
                    txt.setText(txt.getText(0,mascara.length()));
                }    
            } 
        });
    }

    public static void MascaraInteiro(JFXTextField txt){
        txt.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
            String mascara = "###";
            String alphaAndDigits = txt.getText().replaceAll("[^\\d]", "");
            StringBuilder resultado = new StringBuilder();
            int i = 0;
            int quant = 0;

            if (number2.intValue() > number.intValue()) {
                if (txt.getText().length() <= mascara.length()) {
                    while (i<mascara.length()) {
                        if (quant < alphaAndDigits.length()) {
                            if ("#".equals(mascara.substring(i,i+1))) {
                                resultado.append(alphaAndDigits.substring(quant,quant+1));
                                quant++;
                            } else {
                               resultado.append(mascara.substring(i,i+1));
                            }
                        }
                    i++;    
                    }
                    txt.setText(resultado.toString());
                }
                if (txt.getText().length() > mascara.length()) {
                    txt.setText(txt.getText(0,mascara.length()));
                }    
            } 
        });

    }
    
}
