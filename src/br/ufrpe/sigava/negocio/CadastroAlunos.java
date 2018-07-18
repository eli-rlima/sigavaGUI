package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioAluno;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.dados.RepositorioAluno;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroAlunos {
    private IRepositorioAluno repositorioAluno;

    public CadastroAlunos(){
        this.repositorioAluno = RepositorioAluno.getInstance();
    }

    public boolean cadastrar(Aluno aluno){
        boolean retorno = false;
        if(aluno == null){ //TODO
            retorno = false;
        }
        else if(!repositorioAluno.existe(aluno)){
            retorno = this.repositorioAluno.adicionar(aluno);
        }
        return retorno;
    }

    public boolean cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) {
        boolean retorno = false;
        if (nome != null && email != null && sexo == 'm' || sexo == 'f' && dataNascimento != null && senha != null &&
                cpf != null) { //TODO
            Aluno aluno = repositorioAluno.buscar(cpf);
            if (aluno == null) { //TODO
                retorno = repositorioAluno.adicionar(nome, email, sexo, dataNascimento, senha, cpf);
            }
        }
        return retorno;
    }
    public boolean descadastrar(Aluno aluno){
        boolean retorno = false;
         if(aluno != null && repositorioAluno.existe(aluno)){ //TODO
            retorno = repositorioAluno.remover(aluno);
        }
        return retorno;
    }

    public ArrayList<Aluno> listarAlunos (){
        return repositorioAluno.listarAlunos();
    }

    public Aluno procurar (String cpf){
        Aluno aluno = null;
        if(cpf != null){ //TODO
            aluno = repositorioAluno.buscar(cpf);
        }
        return aluno;
    }

    public boolean existe(Aluno aluno){
        boolean retorno = false;
        if(aluno != null){ //TODO
            retorno = repositorioAluno.existe(aluno);
        }
        return retorno;
    }

    public boolean adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino){
        boolean retorno = false;
        Marcacao marcacao = null;
        Tarefa tarefa = null;

        if(nomeCronograma != null){ //TODO
            if(repositorioAluno.existe(aluno) &&  repositorioAluno.existeCronograma(aluno,nomeCronograma)){
                if (codigoTarefa >= 0 && aluno.buscarDisciplina(nomeDisciplina) != null &&
                        aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa) != null) { //TODO
                    tarefa = aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa);
                    if (tarefa.getDataTermino().isEqual(dataTermino) || tarefa.getDataTermino().isAfter(dataTermino)){ //TODO
                        marcacao = new Marcacao(codigoTarefa,dataTermino);
                        retorno = repositorioAluno.adicionarMarcacao(nomeCronograma, aluno, codigoTarefa, dataTermino);
                    }
                }
            }
        }
        return retorno;
    }
    
    public void removerMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino){
        Tarefa tarefa;
        Marcacao marcacao;
        if(nomeCronograma != null){ 
            if (codigoTarefa >= 0 && aluno.buscarDisciplina(nomeDisciplina) != null &&
                aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa) != null) { //TODO
                tarefa = aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa);
                if (tarefa.getDataTermino().isEqual(dataTermino) || tarefa.getDataTermino().isBefore(dataTermino)){ //TODO
                    marcacao = new Marcacao(codigoTarefa,dataTermino);
                    repositorioAluno.removerMarcacao(nomeCronograma, aluno, marcacao);
                }
            }
        }
    }
    
    public void removerMarcacao(Marcacao marcacao, String nomeCronograma, Aluno aluno){
        if(marcacao != null){ //TODO
            repositorioAluno.removerMarcacao(nomeCronograma, aluno, marcacao);
        }
    }
}
