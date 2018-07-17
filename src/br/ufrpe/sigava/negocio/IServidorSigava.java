package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IServidorSigava {

    boolean cadastrarAluno(Aluno aluno);

    Aluno buscarAluno(String cpf);

    boolean cadastrarAluno(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf);

    boolean adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino);

    boolean descadastrarAluno(Aluno aluno);

    boolean existeAluno(Aluno aluno);

    boolean cadastrarDisciplina(Disciplina disciplina);

    boolean cadastrarDisciplina(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria);

    boolean descadastrarDisciplina(Disciplina disciplina);

    Disciplina buscarDisciplina(String nome);

    boolean existeDisciplina(Disciplina disciplina);

    boolean cadastrarProfessorDisciplina(String nomeDisciplina, Professor professor);

    boolean cadastrarAlunoDisciplina(String nomeDisciplina, Aluno aluno);

    boolean cadastrarTarefaDisciplina(String nomeDisciplina, Tarefa tarefa);

    boolean cadastrarProfessor(Professor professor);

    boolean cadastrarProfessor(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf);

    boolean descadastrarProfessor(Professor professor);

    Professor buscarProfessor(String cpf);

    boolean existeProfessor(Professor professor);

    boolean cadastrarTarefa(Tarefa tarefa);

    boolean cadastrarTarefa(String descricao, LocalDate dataInicio,
                            LocalDate dataTermino, int codigoTarefa, Disciplina disciplina);

    boolean descadastrarTarefa(Tarefa tarefa);

    Tarefa buscarTarefa(int codigo);

    boolean existeTarefa(Tarefa tarefa);

    ArrayList<Aluno> listarAlunos ();

    ArrayList <Professor> listarProfessores ();

    ArrayList <Tarefa> listarTarefas ();

    ArrayList <Disciplina> listarDisciplinas ();


}
