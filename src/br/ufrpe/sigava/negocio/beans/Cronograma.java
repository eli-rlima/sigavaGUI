package br.ufrpe.sigava.negocio.beans;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cronograma {
    private ArrayList<Marcacao> marcacoes = new ArrayList<Marcacao>();
    private String nome;

    public Cronograma(String nome){ this.setNome(nome);}

    public boolean adicionar(Marcacao marcacao){
        return marcacoes.add(marcacao);
    }

    public boolean adicionar(int codigoTarefa, LocalDate dataTermino){
        Marcacao marcacao = new Marcacao(codigoTarefa, dataTermino);
        return this.adicionar(marcacao);
    }

    public boolean remover(Marcacao marcacao){
        return marcacoes.remove(marcacao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marcacao criarMarcacao(Tarefa tarefa, LocalDate dataTerminoTarefa){
        Marcacao marcacao = null;
        if (tarefa.getDataTermino().isEqual(dataTerminoTarefa) || tarefa.getDataTermino().isBefore(dataTerminoTarefa)){
            marcacao.setCodigoTarefa(tarefa.getCodigoTarefa());
            marcacao.setDataTermino(dataTerminoTarefa);
        }
        return marcacao;
    }
    public Marcacao buscarMarcacao(int codigoTarefa){
        Marcacao marcacao = null; 
        for(int i = 0; i < marcacoes.size(); i++){
            if(marcacoes.get(i).getCodigoTarefa() == codigoTarefa){
                marcacao = marcacoes.get(i);
            }
        }
        return marcacao;
    }

    @Override
    public String toString (){
        StringBuilder retorno = new StringBuilder();
        retorno.append("Cronograma: "+nome);
        for (int i = 0; i < marcacoes.size(); i++) {
            retorno.append("\n"+marcacoes.get(i).toString());
        }
        return retorno.toString();
    }
}
