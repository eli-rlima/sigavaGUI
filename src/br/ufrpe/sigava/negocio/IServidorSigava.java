package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorJaExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaJaExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IServidorSigava {

    void cadastrarAluno(Aluno aluno) throws AlunoJaExisteException;

    Aluno buscarAluno(String cpf);

    boolean cadastrarAluno(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf);

    boolean adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino);

    void descadastrarAluno(Aluno aluno) throws AlunoNaoExisteException;

    boolean existeAluno(Aluno aluno);

    void cadastrarDisciplina(Disciplina disciplina) throws DisciplinaJaExisteException;

    boolean cadastrarDisciplina(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria);

    void descadastrarDisciplina(Disciplina disciplina) throws DisciplinaNaoExisteException;

    Disciplina buscarDisciplina(String nome);

    boolean existeDisciplina(Disciplina disciplina);

    boolean cadastrarProfessorDisciplina(String nomeDisciplina, Professor professor);

    boolean cadastrarAlunoDisciplina(String nomeDisciplina, Aluno aluno);

    boolean cadastrarTarefaDisciplina(String nomeDisciplina, Tarefa tarefa);

    void cadastrarProfessor(Professor professor) throws ProfessorJaExisteException;

    boolean cadastrarProfessor(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf);

    void descadastrarProfessor(Professor professor) throws ProfessorNaoExisteException;

    Professor buscarProfessor(String cpf);

    boolean existeProfessor(Professor professor);

    void cadastrarTarefa(Tarefa tarefa) throws TarefaJaExisteException;

    boolean cadastrarTarefa(String descricao, LocalDate dataInicio,
                            LocalDate dataTermino, int codigoTarefa, Disciplina disciplina);

    void descadastrarTarefa(Tarefa tarefa) throws TarefaNaoExisteException;

    Tarefa buscarTarefa(int codigo);

    boolean existeTarefa(Tarefa tarefa);

    ArrayList<Aluno> listarAlunos ();

    ArrayList <Professor> listarProfessores ();

    ArrayList <Tarefa> listarTarefas ();

    ArrayList <Disciplina> listarDisciplinas ();


}
