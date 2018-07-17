package br.ufrpe.sigava.exceptions;

public class DisciplinaNaoExisteException extends Exception {

    public DisciplinaNaoExisteException (){
        super("Disciplina nao existe.");
    }
}
