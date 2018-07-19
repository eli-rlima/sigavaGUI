package br.ufrpe.sigava.exceptions;


public class MarcacaoJaExisteException extends Exception{
    
    public MarcacaoJaExisteException(){
        super("Marcacao ja existe");
    }
    
}
