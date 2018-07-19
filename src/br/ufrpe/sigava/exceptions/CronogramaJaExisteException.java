package br.ufrpe.sigava.exceptions;

public class CronogramaJaExisteException extends Exception {

    public CronogramaJaExisteException (){
        super("Cronograma ja cadastrado.");
    }
}
