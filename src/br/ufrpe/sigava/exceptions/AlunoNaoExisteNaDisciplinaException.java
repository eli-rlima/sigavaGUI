/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.exceptions;

/**
 *
 * @author helto
 */
public class AlunoNaoExisteNaDisciplinaException extends Exception {

    public AlunoNaoExisteNaDisciplinaException(){
        super("Aluno não existe na disciplina");
    }
}

