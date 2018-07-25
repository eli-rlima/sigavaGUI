package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioTarefa;
import br.ufrpe.sigava.dados.RepositorioDisciplina;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.dados.RepositorioTarefa;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaJaExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;

import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroTarefas {
    private IRepositorioTarefa repositorioTarefa;

    public CadastroTarefas(){
        this.repositorioTarefa = RepositorioTarefa.getInstance();
    }

    public void cadastrar(Tarefa tarefa)throws TarefaJaExisteException{
       if (!this.repositorioTarefa.existe(tarefa)) {
           this.repositorioTarefa.adicionar(tarefa);
       } else throw new TarefaJaExisteException();
    }

    public ArrayList<Tarefa> listarTarefas (){
        return repositorioTarefa.listarTarefas();
    }

    public void cadastrar(String descricao, LocalDate dataInicio, LocalDate dataTermino, int codigoTarefa, Disciplina disciplina) 
            throws TarefaJaExisteException, IllegalArgumentException, DisciplinaNaoExisteException{
        if (descricao != null && dataInicio != null && dataTermino != null && codigoTarefa >= 0) { //TODO
            if(disciplina != null){
                if (this.repositorioTarefa.buscar(codigoTarefa) != null){ //TODO
                    this.repositorioTarefa.adicionar(descricao, dataInicio, dataTermino, codigoTarefa, disciplina);
                    this.repositorioTarefa.salvarArquivo();
                }else{
                    throw new TarefaJaExisteException();
                }
            }else throw new DisciplinaNaoExisteException();
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)!");
        }
   }

    public void descadastrar(Tarefa tarefa) throws TarefaNaoExisteException{
        if(tarefa != null){
            tarefa.getDisciplina().removerTarefa(tarefa);
            this.repositorioTarefa.remover(tarefa);
            this.repositorioTarefa.salvarArquivo();
            RepositorioDisciplina.getInstance().salvarArquivo();
        } else throw new TarefaNaoExisteException();
    }

    public Tarefa procurar(int codigo) throws TarefaNaoExisteException, IllegalArgumentException{
        Tarefa tarefa = null;
        if(codigo >= 0){
            if(repositorioTarefa.buscar(codigo) != null){
                tarefa = this.repositorioTarefa.buscar(codigo);
            }else{
            throw new TarefaNaoExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento inválido!");
        }
        return tarefa;
    }

    public boolean existe(Tarefa tarefa) throws TarefaNaoExisteException{
        boolean retorno = false;
        if (tarefa != null){ 
            retorno = this.repositorioTarefa.existe(tarefa);
        }else{
            throw new TarefaNaoExisteException();
        }
        return retorno;
    }

}
