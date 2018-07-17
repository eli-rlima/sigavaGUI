package br.ufrpe.sigava.exceptions;

public class TarefaNaoExisteException extends Exception {

    public TarefaNaoExisteException (){
        super("Tarefa nao existe.");
    }
}
