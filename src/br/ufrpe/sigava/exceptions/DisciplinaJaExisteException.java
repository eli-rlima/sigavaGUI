package br.ufrpe.sigava.exceptions;

public class DisciplinaJaExisteException extends Exception {

    public DisciplinaJaExisteException (){
        super("Disciplina ja cadastrada.");
    }
}
