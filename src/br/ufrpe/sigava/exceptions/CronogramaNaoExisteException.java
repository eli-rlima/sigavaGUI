package br.ufrpe.sigava.exceptions;

public class CronogramaNaoExisteException extends Exception {

    public CronogramaNaoExisteException (){
        super("Cronograma nao encontrado.");
    }
}
