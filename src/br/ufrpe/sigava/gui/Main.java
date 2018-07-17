package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;


public class Main {
    public static void main(String[] args) {
        IServidorSigava servidorSigava = ServidorSigava.getIstance();
        Sistema.Sistema(servidorSigava);
    }
}
