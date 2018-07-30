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
public class ComparadorDisciplina implements Comparator<Disciplina>{

    @Override
    public int compare(Disciplina d1, Disciplina d2) {
        return d1.getNome().compareTo(d2.getNome());
    }
    
}
