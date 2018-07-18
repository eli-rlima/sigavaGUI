package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioAluno;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.dados.RepositorioAluno;
import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.negocio.beans.Cronograma;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroAlunos {
    private IRepositorioAluno repositorioAluno;

    public CadastroAlunos(){
        this.repositorioAluno = RepositorioAluno.getInstance();
    }

    public void cadastrar(Aluno aluno) throws AlunoJaExisteException {
       if(repositorioAluno.existe(aluno)){
           this.repositorioAluno.adicionar(aluno);
        } else{ 
           AlunoJaExisteException jaExiste = new AlunoJaExisteException();
           throw jaExiste;
       }
    }

    public void cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) {
        if (nome != null && email != null && sexo == 'm' || sexo == 'f' && dataNascimento != null && senha != null &&
                cpf != null) { //TODO
            Aluno aluno = repositorioAluno.buscar(cpf);
            if (aluno == null) { //TODO
                 repositorioAluno.adicionar(nome, email, sexo, dataNascimento, senha, cpf);
            }else{}
        }else{}
    }
    
    public void descadastrar(Aluno aluno) throws AlunoNaoExisteException {
         if(aluno != null && repositorioAluno.existe(aluno)){
            this.repositorioAluno.remover(aluno);
        }else{
             AlunoNaoExisteException naoExiste = new AlunoNaoExisteException();
             throw naoExiste;
         }
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
                    } else {}
                } else{}
            }else{}
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
                }else{}
            }else{}
        }else{}
    }
    
    public void removerMarcacao(Marcacao marcacao, String nomeCronograma, Aluno aluno){
        if(marcacao != null){ //TODO
            repositorioAluno.removerMarcacao(nomeCronograma, aluno, marcacao);
        }else{}
    }
    
    public Marcacao buscarMarcacao(String nome, Aluno aluno, int codigoTarefa){
        Marcacao marcacao = null;
        Cronograma cronograma = null;
        if(aluno != null && nome != null && codigoTarefa >= 0){ //TODO
            if(aluno.buscarCronograma(nome) != null){ //TODO
                cronograma = aluno.buscarCronograma(nome);
                if(cronograma.buscarMarcacao(codigoTarefa)!= null){ //TODO
                    marcacao = cronograma.buscarMarcacao(codigoTarefa);
                }else{}
            }else{}
        }else{}
        return marcacao;
    }
    
    public void atualizarMarcacoes(String nomeCronograma, Aluno aluno){
        if(nomeCronograma != null && aluno != null){
            if(aluno.buscarCronograma(nomeCronograma)!= null){
                for(int i = 0; i < aluno.buscarCronograma(nomeCronograma).marcacoes().size(); i++){
                    for(int j = 0; j < aluno.getDisciplinas().size(); j++){
                        if(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i) != null){
                            if(aluno.getDisciplinas().get(j) != null){
                                if(aluno.getDisciplinas().get(j).procurarTarefa(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getCodigoTarefa())!= null){
                                    if(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getDataTermino().isAfter(aluno.getDisciplinas().get(j).procurarTarefa(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getCodigoTarefa()).getDataTermino())){
                                        aluno.buscarCronograma(nomeCronograma).remover(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i));
                                    }else{}
                                }else{}
                            }else{}
                        }else{}
                    }
                }
            }else{}
        }else{}
    }
    
    public boolean existeMarcacao(Aluno aluno, Marcacao marcacao, String nome){
        boolean existe = false;
        if(aluno != null && marcacao != null && nome != null){ //TODO
            if(aluno.buscarCronograma(nome)!= null){
                Cronograma cronograma = aluno.buscarCronograma(nome);
                if(cronograma.buscarMarcacao(marcacao.getCodigoTarefa())!= null){
                    existe = true;
                }else{}
            }else{}
        }else{}
        return existe;
    }
    
    public void adicionarCronograma(Aluno aluno, String nomeCronograma){
        Cronograma cronograma;
        if(aluno != null && nomeCronograma != null){
            if(aluno.buscarCronograma(nomeCronograma) == null){
                cronograma = new Cronograma(nomeCronograma);
                aluno.adicionarCronograma(cronograma);
            }else{}
        }else{}
    }
    
    public void removerCronograma(Aluno aluno, String nomeCronograma){
        Cronograma cronograma;
        if(aluno != null && nomeCronograma != null){
            if(aluno.buscarCronograma(nomeCronograma) != null){
                cronograma = aluno.buscarCronograma(nomeCronograma);
                aluno.removerCronograma(cronograma);
            }else{}
        }else{}
    }
    
    public Cronograma buscarCronograma(Aluno aluno, String nomeCronograma){
        Cronograma cronograma = null;
        if(aluno != null && nomeCronograma != null){
            if(aluno.buscarCronograma(nomeCronograma)!= null){
                cronograma = aluno.buscarCronograma(nomeCronograma);
            }else{}
        }else{}
        return cronograma;
    }
}
