/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.negocio.beans;

import java.util.Comparator;

/**
 *
 * @author elive
 */
public class ComparadorDisciplina implements Comparator <Disciplina> {

    @Override
    public int compare(Disciplina e1, Disciplina e2) {
        return e1.getNome().compareTo(e2.getNome());
    }
    
}
