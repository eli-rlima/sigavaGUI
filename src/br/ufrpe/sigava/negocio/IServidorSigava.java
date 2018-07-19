package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.CronogramaNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaJaExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.MarcacaoNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorJaExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaJaExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;
import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IServidorSigava {

    void cadastrarAluno(Aluno aluno) throws AlunoJaExisteException;

    Aluno buscarAluno(String cpf) throws AlunoNaoExisteException, IllegalArgumentException;

    void cadastrarAluno(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws AlunoJaExisteException, IllegalArgumentException;

    void descadastrarAluno(Aluno aluno) throws AlunoNaoExisteException;

    boolean existeAluno(Aluno aluno) throws IllegalArgumentException;
    
    void adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino)throws TarefaNaoExisteException, AlunoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException, DisciplinaNaoExisteException;
    
    void removerMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino)
                throws TarefaNaoExisteException, DisciplinaNaoExisteException, IllegalArgumentException;
    
    void removerMarcacao(Marcacao marcacao, String nomeCronograma, Aluno aluno) throws AlunoNaoExisteException, MarcacaoNaoExisteException, IllegalArgumentException;
    
    void atualizarMarcacoes(String nomeCronograma, Aluno aluno) throws TarefaNaoExisteException, DisciplinaNaoExisteException, MarcacaoNaoExisteException, 
                CronogramaNaoExisteException, IllegalArgumentException;
    
    Marcacao buscarMarcacao(String nome, Aluno aluno, int codigoTarefa) throws MarcacaoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException;
    
    boolean existeMarcacao(Aluno aluno, Marcacao marcacao, String nome) throws CronogramaNaoExisteException, 
            MarcacaoNaoExisteException, IllegalArgumentException;
    
    void adicionarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException;
    
    void removerCronograma(Aluno aluno, String nomeCronograma)throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException;
    
    Cronograma buscarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException;

    void cadastrarDisciplina(Disciplina disciplina) throws DisciplinaJaExisteException;

    void cadastrarDisciplina(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria) throws
            DisciplinaJaExisteException, IllegalArgumentException;

    void descadastrarDisciplina(Disciplina disciplina) throws DisciplinaNaoExisteException;

    Disciplina buscarDisciplina(String nome) throws DisciplinaNaoExisteException, IllegalArgumentException;

    boolean existeDisciplina(Disciplina disciplina) throws IllegalArgumentException;

    void cadastrarProfessorDisciplina(String nomeDisciplina, Professor professor)throws DisciplinaNaoExisteException,
            ProfessorNaoExisteException, IllegalArgumentException;

    void cadastrarAlunoDisciplina(String nomeDisciplina, Aluno aluno)throws DisciplinaNaoExisteException, AlunoNaoExisteException,
            IllegalArgumentException;

    void cadastrarTarefaDisciplina(String nomeDisciplina, Tarefa tarefa)throws DisciplinaNaoExisteException, TarefaNaoExisteException, 
            IllegalArgumentException;

    void cadastrarProfessor(Professor professor) throws ProfessorJaExisteException;

    void cadastrarProfessor(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws ProfessorJaExisteException, IllegalArgumentException;

    void descadastrarProfessor(Professor professor) throws ProfessorNaoExisteException;

    Professor buscarProfessor(String cpf) throws ProfessorNaoExisteException, IllegalArgumentException;

    boolean existeProfessor(Professor professor);

    void cadastrarTarefa(Tarefa tarefa) throws TarefaJaExisteException;

    void cadastrarTarefa(String descricao, LocalDate dataInicio,
                            LocalDate dataTermino, int codigoTarefa, Disciplina disciplina) throws TarefaJaExisteException, IllegalArgumentException;

    void descadastrarTarefa(Tarefa tarefa) throws TarefaNaoExisteException;

    Tarefa buscarTarefa(int codigo) throws TarefaNaoExisteException, IllegalArgumentException;

    boolean existeTarefa(Tarefa tarefa);

    ArrayList<Aluno> listarAlunos ();

    ArrayList <Professor> listarProfessores ();

    ArrayList <Tarefa> listarTarefas ();

    ArrayList <Disciplina> listarDisciplinas ();


}
