package br.ufrpe.sigava.exceptions;

public class AlunoNaoExisteException extends Exception {

    public AlunoNaoExisteException(){
        super("Aluno nao existe.");
    }
}
