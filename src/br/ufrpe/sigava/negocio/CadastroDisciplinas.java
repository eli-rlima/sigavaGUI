package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioDisciplina;
import br.ufrpe.sigava.dados.RepositorioAluno;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.dados.RepositorioDisciplina;
import br.ufrpe.sigava.dados.RepositorioProfessor;
import br.ufrpe.sigava.dados.RepositorioTarefa;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroDisciplinas {
    private IRepositorioDisciplina repositorioDisciplina;

    public CadastroDisciplinas(){
        this.repositorioDisciplina = RepositorioDisciplina.getInstance();
    }

    public void cadastrar(Disciplina disciplina) throws DisciplinaJaExisteException{
        if (!this.repositorioDisciplina.existe(disciplina)){
           this.repositorioDisciplina.adicionar(disciplina);
           this.repositorioDisciplina.salvarArquivo();
        }else{
            throw new DisciplinaJaExisteException();
        }
    }

    public ArrayList<Disciplina> listarDisciplinas(){
        return repositorioDisciplina.listarDisciplinas();
    }

    public void cadastrar(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria) throws
            DisciplinaJaExisteException, IllegalArgumentException{
        if (nome != null && dataInicio != null && diaAula != null && duracaoAula > 0 && cargaHoraria > 0){
            Disciplina disciplina = new Disciplina(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);
            if(!repositorioDisciplina.existe(disciplina)){
                this.repositorioDisciplina.adicionar(disciplina);
                this.repositorioDisciplina.salvarArquivo();
            }else{
                throw new DisciplinaJaExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)");
        }
    }

    public void descadastrar(Disciplina disciplina) throws DisciplinaNaoExisteException{
        if(disciplina != null){
            ArrayList<Aluno> alunos = disciplina.getAlunos();
            for (int i = 0; i < alunos.size(); i++) {
                alunos.get(i).removerDisciplina(disciplina);
            }
            RepositorioProfessor.getInstance().remover(disciplina.getProfessor());
            this.repositorioDisciplina.remover(disciplina);
            this.repositorioDisciplina.salvarArquivo();
            RepositorioAluno.getInstance().salvarArquivo();
            RepositorioProfessor.getInstance().salvarArquivo();
        } else{
            throw new DisciplinaNaoExisteException();
        }
    }

    public Disciplina procurar(String nome) throws DisciplinaNaoExisteException, IllegalArgumentException{
        Disciplina disciplina = null;
        if(nome != null){
            if(this.repositorioDisciplina.buscar(nome) != null){
                disciplina = this.repositorioDisciplina.buscar(nome);
            }else throw new DisciplinaNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido");
        return disciplina;
    }
    
    public void atualizar(Disciplina antigaDisciplina, String nome, int duracaoAula, DayOfWeek diaAula, LocalDate dataInicio, int cargaHoraria) throws DisciplinaNaoExisteException{
        if(this.repositorioDisciplina.buscar(antigaDisciplina.getNome()) != null){
            if(antigaDisciplina != null && nome != null && diaAula != null && dataInicio != null){
            this.repositorioDisciplina.atualizar(antigaDisciplina, nome, duracaoAula, diaAula, dataInicio, cargaHoraria);
            this.repositorioDisciplina.salvarArquivo();
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)");
        }
    }else{
            throw new DisciplinaNaoExisteException();
        }
    }

    public boolean existe(Disciplina disciplina) throws IllegalArgumentException{
        boolean retorno = false;
        if (disciplina != null){ //TODO
            retorno = this.repositorioDisciplina.existe(disciplina);
        }else throw new IllegalArgumentException("Argumento inválido");
        return retorno;
    }

    public void cadastrarProfessor(String nome, Professor professor) throws DisciplinaNaoExisteException,
            ProfessorNaoExisteException, IllegalArgumentException{
        Disciplina disciplina = null;
        if (nome != null){ //TODO
            if(this.repositorioDisciplina.buscar(nome) != null){
                disciplina = this.repositorioDisciplina.buscar(nome);
            }else throw new DisciplinaNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido");
        if (disciplina != null ){
            if(professor != null){
                disciplina.adicionarProfessor(professor);
                professor.adicionar(disciplina);
                this.repositorioDisciplina.salvarArquivo();
                RepositorioProfessor.getInstance().salvarArquivo();
            }else throw new ProfessorNaoExisteException();
        }else throw new DisciplinaNaoExisteException();
    }

    public void cadastrarAluno(String nome, Aluno aluno) throws DisciplinaNaoExisteException, AlunoNaoExisteException,
            IllegalArgumentException{
        Disciplina disciplina = null;
        if (nome != null){
            if(this.repositorioDisciplina.buscar(nome) != null){
               disciplina = this.repositorioDisciplina.buscar(nome);
            }else throw new DisciplinaNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido");
        if(aluno != null){
            disciplina.adicionarAluno(aluno);
            aluno.adicionarDisciplina(disciplina);
            RepositorioAluno.getInstance().salvarArquivo();
            this.repositorioDisciplina.salvarArquivo();
        }else throw new AlunoNaoExisteException();
    }

    public void cadastrarTarefa(String nome, Tarefa tarefa) throws DisciplinaNaoExisteException, TarefaNaoExisteException, 
            IllegalArgumentException{
        Disciplina disciplina = null;
        if (nome != null){
            if(this.repositorioDisciplina.buscar(nome) != null){
                disciplina = this.repositorioDisciplina.buscar(nome);
            }else throw new DisciplinaNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido");
        if(tarefa != null){
            disciplina.adicionarTarefa(tarefa);
            this.repositorioDisciplina.salvarArquivo();
            RepositorioTarefa.getInstance().salvarArquivo();
        }else throw new TarefaNaoExisteException();
           
    }
  
        public boolean existeAluno(Disciplina disciplina, Aluno aluno){
         boolean retorno = false;
         if (disciplina != null && aluno != null){
             retorno = repositorioDisciplina.existeAluno(disciplina, aluno);
         }else throw new IllegalArgumentException("Argumento(s) inválido(s).");
         return retorno;
     }
        
        public boolean existeProfessor(Disciplina disciplina, Professor professor){
        boolean retorno = false;
         if (disciplina != null && professor != null){
             retorno = repositorioDisciplina.existeProfessor(disciplina, professor);
         }else throw new IllegalArgumentException("Argumento(s) inválido(s).");
         return retorno;
     }
}
