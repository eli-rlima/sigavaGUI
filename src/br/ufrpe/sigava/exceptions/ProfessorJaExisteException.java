package br.ufrpe.sigava.exceptions;

public class ProfessorJaExisteException extends Exception{

    public ProfessorJaExisteException (){
        super("Professor ja cadastrado.");
    }
}
