/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.negocio.beans.pessoa;

import java.util.Comparator;

/**
 *
 * @author helto
 */
public class ComparadorPessoa implements Comparator <Pessoa>{
    
    @Override
    public int compare (Pessoa e1, Pessoa e2){
        return e1.getNome().compareTo(e2.getNome());
    }
}
