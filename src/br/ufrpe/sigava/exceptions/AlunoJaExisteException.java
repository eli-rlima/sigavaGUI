package br.ufrpe.sigava.exceptions;

public class AlunoJaExisteException extends Exception {

    public AlunoJaExisteException (){
        super("Aluno ja cadastrado");
    }
}
