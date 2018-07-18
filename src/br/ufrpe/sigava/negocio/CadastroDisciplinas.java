package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioDisciplina;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.dados.RepositorioDisciplina;
import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroDisciplinas {
    private IRepositorioDisciplina repositorioDisciplina;

    public CadastroDisciplinas(){
        this.repositorioDisciplina = RepositorioDisciplina.getInstance();
    }

    public void cadastrar(Disciplina disciplina) throws DisciplinaJaExisteException{
       if (this.repositorioDisciplina.existe(disciplina)){
           this.repositorioDisciplina.adicionar(disciplina);
        }else{
           DisciplinaJaExisteException jaExiste = new DisciplinaJaExisteException();
           throw jaExiste;
       }
    }

    public ArrayList<Disciplina> listarDisciplinas(){
        return repositorioDisciplina.listarDisciplinas();
    }

    public boolean cadastrar(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria){
        boolean retorno = false;
        if (nome != null && dataInicio != null && diaAula != null && duracaoAula > 0 && cargaHoraria > 0){ //TODO
            retorno = this.repositorioDisciplina.adicionar(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);

        }
        return retorno;
    }

    public void descadastrar(Disciplina disciplina) throws DisciplinaNaoExisteException{
        if(disciplina != null){
            this.repositorioDisciplina.remover(disciplina);
        } else{
            DisciplinaNaoExisteException naoExiste = new DisciplinaNaoExisteException();
            throw naoExiste;
        }
    }

    public Disciplina procurar(String nome){
        Disciplina disciplina = null;
        if(nome != null){ //TODO
            disciplina = this.repositorioDisciplina.buscar(nome);
        }
        return disciplina;
    }

    public boolean existe(Disciplina disciplina){
        boolean retorno = false;
        if (disciplina != null){ //TODO
            retorno = this.repositorioDisciplina.existe(disciplina);
        }
        return retorno;
    }

    public boolean cadastrarProfessor(String nome, Professor professor){
        boolean retorno = false;
        Disciplina disciplina = null;
        if (nome != null){ //TODO
            disciplina = this.repositorioDisciplina.buscar(nome);
        }
        if (disciplina != null && professor != null){ //TODO
            disciplina.adicionarProfessor(professor);
            professor.adicionar(disciplina);  // Garantir que professor receba a disciplina,
                                                        // quando o mesmo esteja sendo adicionado.
            retorno = true;
        }
        return retorno;
    }

    public boolean cadastrarAluno(String nome, Aluno aluno){
        boolean retorno = false;
        Disciplina disciplina = null;
        if (nome != null){ //TODO
            disciplina = this.repositorioDisciplina.buscar(nome);
        }
        if (disciplina != null && aluno != null){ //TODO
            disciplina.adicionarAluno(aluno);
            aluno.adicionarDisciplina(disciplina);  // certificar de quando um aluno entrar na disciplina,
                                                    // a disciplina esteja nos seus atributos.
            retorno = true;
        }
        return retorno;
    }

    public boolean cadastrarTarefa(String nome, Tarefa tarefa){
        boolean retorno = false;
        Disciplina disciplina = null;
        if (nome != null){ //TODO
            disciplina = this.repositorioDisciplina.buscar(nome);
        }
        if (disciplina != null && tarefa != null){ //TODO
            disciplina.adicionarTarefa(tarefa);
            retorno = true;
        }
        return retorno;
    }
}
