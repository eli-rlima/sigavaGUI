package br.ufrpe.sigava.exceptions;

public class MarcacaoNaoExisteException extends Exception{
    
    public MarcacaoNaoExisteException(){
        super("Marcacao nao existe");
    }
}
