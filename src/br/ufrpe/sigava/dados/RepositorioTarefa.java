package br.ufrpe.sigava.dados;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;


public class RepositorioTarefa implements IRepositorioTarefa {

    private ArrayList <Tarefa> repositorioTarefas;
    private static RepositorioTarefa instance;

    private RepositorioTarefa(){
        this.repositorioTarefas = new ArrayList<Tarefa>();
    }


    public static RepositorioTarefa getInstance (){
        if (instance == null){
            instance = new RepositorioTarefa();
        }
        return instance;
    }

    public ArrayList <Tarefa> listarTarefas(){
       return this.repositorioTarefas;
    }



    @Override
    public boolean adicionar(Tarefa tarefa){
        return this.repositorioTarefas.add(tarefa);
    }

    @Override
    public boolean adicionar (String descricao, LocalDate dataInicio,
                              LocalDate dataTermino, int codigoTarefa, Disciplina disciplina){
        Tarefa tarefa = new Tarefa(descricao,dataInicio,dataTermino,codigoTarefa,disciplina);
        return adicionar(tarefa);
    }

    @Override
    public boolean remover(Tarefa tarefa){
        return this.repositorioTarefas.remove(tarefa);
    }
    @Override
    public Tarefa buscar(int codigo){
        Tarefa tarefa = null;
        for (int i = 0; i < this.repositorioTarefas.size(); i++){
            if (this.repositorioTarefas.get(i).getCodigoTarefa() == codigo){
                tarefa = this.repositorioTarefas.get(i);
            }
        }
        return tarefa;
    }
    @Override
    public boolean existe(Tarefa tarefa){
        return this.repositorioTarefas.contains(tarefa);
    }

}

