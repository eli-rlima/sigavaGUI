package br.ufrpe.sigava.exceptions;

public class TarefaJaExisteException extends Exception {

    public TarefaJaExisteException (){
        super("Tarefa ja cadastrada.");
    }
}
