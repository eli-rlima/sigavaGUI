package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioTarefa;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.dados.RepositorioTarefa;

import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroTarefas {
    private IRepositorioTarefa repositorioTarefa;

    public CadastroTarefas(){
        this.repositorioTarefa = RepositorioTarefa.getInstance();
    }

    public boolean cadastrar(Tarefa tarefa){
        boolean retorno = false;
        if (tarefa == null){ //TODO
            retorno = false;
        }
        else if (!this.repositorioTarefa.existe(tarefa)) {
            this.repositorioTarefa.adicionar(tarefa);
            retorno = true;
        }
        return retorno;
    }

    public ArrayList<Tarefa> listarTarefas (){
        return repositorioTarefa.listarTarefas();
    }

    public boolean cadastrar(String descricao, LocalDate dataInicio,
                             LocalDate dataTermino, int codigoTarefa, Disciplina disciplina){
        boolean retorno = false;
        if (descricao != null && dataInicio != null && dataTermino != null
                && disciplina != null && codigoTarefa >= 0) { //TODO
            if (this.repositorioTarefa.buscar(codigoTarefa) != null){ //TODO
                retorno = this.repositorioTarefa.adicionar(descricao, dataInicio,
                        dataTermino, codigoTarefa, disciplina);
            }
        }
        return retorno;
    }

    public boolean descadastrar(Tarefa tarefa){
        boolean retorno = false;
        if(tarefa != null){ //TODO
            this.repositorioTarefa.remover(tarefa);
            retorno = true;
        }
        return retorno;
    }

    public Tarefa procurar(int codigo){
        Tarefa tarefa = null;
        if(codigo >= 0){ //TODO
            tarefa = this.repositorioTarefa.buscar(codigo);
        }
        return tarefa;
    }

    public boolean existe(Tarefa tarefa){
        boolean retorno = false;
        if (tarefa != null){ //TODO
            retorno = this.repositorioTarefa.existe(tarefa);
        }
        return retorno;
    }

}
