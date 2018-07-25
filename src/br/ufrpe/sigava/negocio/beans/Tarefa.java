package br.ufrpe.sigava.negocio.beans;

import java.io.Serializable;
import java.time.LocalDate; 
import java.time.Period; 
import java.time.format.DateTimeFormatter; 
 
public class Tarefa implements Serializable{ 
    private String descricao; 
    private LocalDate dataInicio; 
    private LocalDate dataTermino; 
    private int codigoTarefa;


    private Disciplina disciplina;
 
    public Tarefa(String descricao, LocalDate dataInicio, LocalDate dataTermino,
                  int codigoTarefa, Disciplina disciplina) {
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataTermino(dataTermino);
        this.setCodigoTarefa(codigoTarefa);
        this.setDisciplina(disciplina);
    }
    
    public Tarefa(){
    	
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getDescricao() { 
        return descricao; 
    } 
 
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    } 
 
    public LocalDate getDataInicio() { 
        return dataInicio; 
    } 
 
    public void setDataInicio(LocalDate dataInicio) { 
        this.dataInicio = dataInicio; 
    } 
 
    public LocalDate getDataTermino() { 
        return dataTermino; 
    } 
 
    public void setDataTermino(LocalDate dataTermino) { 
        this.dataTermino = dataTermino; 
    } 
 
    public int getCodigoTarefa() { 
        return codigoTarefa; 
    } 
 
    public void setCodigoTarefa(int codigoTarefa) { 
        this.codigoTarefa = codigoTarefa; 
    } 
 
    public int getDuracao(){ 
        Period period = Period.between(this.getDataInicio(), this.getDataTermino()); 
        return period.getDays(); 
    } 
    
    public String toString() { 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        return "Codigo da tarefa: " + this.getCodigoTarefa() +
                "\nDescricao da tarefa: " + this.getDescricao() +
                "\nData Inicio = " + this.getDataInicio().format(formatter) +
                "\nData Termino = " + this.getDataTermino().format(formatter) + "\n" +
                this.getDuracao() + " dias para a data da entrega."; 
    } 
 
    public boolean equals(Tarefa anotherTarefa){ 
        boolean equals = false; 
        if(this.getCodigoTarefa() == anotherTarefa.getCodigoTarefa()){ 
            equals = true; 
        } 
        return equals; 
    } 
} 