package br.ufrpe.sigava.exceptions;

public class ProfessorNaoExisteException extends Exception{

    public ProfessorNaoExisteException (){
        super("Professor nao existe.");
    }
}
