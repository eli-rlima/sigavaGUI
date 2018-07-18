package br.ufrpe.sigava.negocio.beans;

import br.ufrpe.sigava.negocio.CadastroTarefas;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Marcacao {
    private int codigoTarefa;
    private LocalDate dataTermino;

    public Marcacao(int codigoTarefa, LocalDate dataTermino) {
        this.codigoTarefa = codigoTarefa;
        this.dataTermino = dataTermino;
    }

    public int getCodigoTarefa() {
        return codigoTarefa;
    }

    public void setCodigoTarefa(int codigoTarefa) {
        this.codigoTarefa = codigoTarefa;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString(){
        CadastroTarefas tarefa = new CadastroTarefas();
        StringBuilder retorno = new StringBuilder();
        retorno.append(tarefa);
        retorno.append("\nData termino do aluno: "+dataTermino.format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("dd/MM/yyyy")));
        return retorno.toString();
    }
    public boolean equals(Marcacao otherMarcacao){
        boolean feito = false;
        if(otherMarcacao != null){
            if(otherMarcacao.getCodigoTarefa() == this.getCodigoTarefa()){
                if(otherMarcacao.getDataTermino().equals(this.getDataTermino())){
                    feito = true;
                }
            }
        }
        return feito;
    }
}
