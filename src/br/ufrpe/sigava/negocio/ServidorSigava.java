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

public class ServidorSigava implements IServidorSigava{
    private CadastroProfessor professores;
    private CadastroAlunos alunos;
    private CadastroTarefas tarefas;
    private CadastroDisciplinas disciplinas;

    private static ServidorSigava instance;

    private ServidorSigava(){
        this.professores = new CadastroProfessor();
        this.alunos = new CadastroAlunos();
        this.tarefas = new CadastroTarefas();
        this.disciplinas = new CadastroDisciplinas();
    }

    public static ServidorSigava getIstance(){
        if (instance == null){
            instance = new ServidorSigava();
        }
        return instance;
    }
    @Override
    public ArrayList <Aluno> listarAlunos (){
        return alunos.listarAlunos();
    }
    
    @Override
    public ArrayList <Professor> listarProfessores (){
        return professores.listarProfessores();
    }
    
    @Override
    public ArrayList <Tarefa> listarTarefas (){
        return tarefas.listarTarefas();
    }
    
    @Override
    public ArrayList <Disciplina> listarDisciplinas (){
        return disciplinas.listarDisciplinas();
    }
    
    @Override
    public void cadastrarAluno(Aluno aluno) throws AlunoJaExisteException{
        this.alunos.cadastrar(aluno);
    }
    
    @Override
    public Aluno buscarAluno(String cpf) throws AlunoNaoExisteException, IllegalArgumentException{
        return this.alunos.procurar(cpf);
    }
    
    @Override
    public void cadastrarAluno(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws AlunoJaExisteException, IllegalArgumentException{
        this.alunos.cadastrar(nome, email, sexo, dataNascimento, senha, cpf);
    }
    
    @Override
    public void descadastrarAluno(Aluno aluno) throws AlunoNaoExisteException{
        this.alunos.descadastrar(aluno);
    }
    
    @Override
    public void atualizarAluno(Aluno antigoAluno, String nome, String email, String cpf, String senha, char sexo, LocalDate dataNascimento) throws AlunoNaoExisteException{
        this.alunos.atualizar(antigoAluno, nome, email, cpf, senha, sexo, dataNascimento);
    }
    
    @Override
    public boolean existeAluno(Aluno aluno) throws IllegalArgumentException{
        return this.alunos.existe(aluno);
    }
    
    @Override
    public void adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino) throws TarefaNaoExisteException, AlunoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException, DisciplinaNaoExisteException{
        this.alunos.adicionarMarcacao(nomeDisciplina, nomeCronograma, aluno, codigoTarefa, dataTermino);
    }
    
    @Override
    public void removerMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino)
                throws TarefaNaoExisteException, DisciplinaNaoExisteException, IllegalArgumentException{
        this.alunos.removerMarcacao(nomeDisciplina, nomeCronograma, aluno, codigoTarefa, dataTermino);
    }
    
    @Override
    public void removerMarcacao(Marcacao marcacao, String nomeCronograma, Aluno aluno) 
                throws AlunoNaoExisteException, MarcacaoNaoExisteException, IllegalArgumentException{
         this.alunos.removerMarcacao(marcacao, nomeCronograma, aluno);
    }
    
    @Override
    public Marcacao buscarMarcacao(String nome, Aluno aluno, int codigoTarefa)
            throws MarcacaoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException{
        return alunos.buscarMarcacao(nome, aluno, codigoTarefa);
    }
    
    @Override
    public void atualizarMarcacoes(String nomeCronograma, Aluno aluno) 
           throws TarefaNaoExisteException, DisciplinaNaoExisteException, MarcacaoNaoExisteException, 
                CronogramaNaoExisteException, IllegalArgumentException{
        this.alunos.atualizarMarcacoes(nomeCronograma, aluno);
    }
    
    @Override
    public boolean existeMarcacao(Aluno aluno, Marcacao marcacao, String nome) throws CronogramaNaoExisteException, 
            MarcacaoNaoExisteException, IllegalArgumentException{
        return this.alunos.existeMarcacao(aluno, marcacao, nome);
    }
    
    @Override
    public void adicionarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        this.alunos.adicionarCronograma(aluno, nomeCronograma);
    }
    
    @Override
    public void removerCronograma(Aluno aluno, String nomeCronograma)throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        this.alunos.removerCronograma(aluno, nomeCronograma);
    }
    
    @Override
    public Cronograma buscarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        return this.alunos.buscarCronograma(aluno, nomeCronograma);
    }

    @Override
    public void cadastrarDisciplina(Disciplina disciplina) throws DisciplinaJaExisteException{
        this.disciplinas.cadastrar(disciplina);
    }

    @Override
    public void cadastrarDisciplina(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria) throws
            DisciplinaJaExisteException, IllegalArgumentException{
         this.disciplinas.cadastrar(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);
    }

    @Override
    public void descadastrarDisciplina(Disciplina disciplina) throws DisciplinaNaoExisteException {
        this.disciplinas.descadastrar(disciplina);
    }

    @Override
    public Disciplina buscarDisciplina(String nome)throws DisciplinaNaoExisteException, IllegalArgumentException{
        return this.disciplinas.procurar(nome);
    }
    
    @Override    
    public void atualizarDisciplina(Disciplina antigaDisciplina, String nome, int duracaoAula, DayOfWeek diaAula, LocalDate dataInicio, int cargaHoraria) throws DisciplinaNaoExisteException{
        this.disciplinas.atualizar(antigaDisciplina, nome, duracaoAula, diaAula, dataInicio, cargaHoraria);
    }

    @Override
    public boolean existeDisciplina(Disciplina disciplina) throws IllegalArgumentException{
        return this.disciplinas.existe(disciplina);
    }
    
    @Override
    public void cadastrarProfessorDisciplina(String nomeDisciplina, Professor professor)throws DisciplinaNaoExisteException,
            ProfessorNaoExisteException, IllegalArgumentException{
         this.disciplinas.cadastrarProfessor(nomeDisciplina, professor);
    }

    @Override
    public void cadastrarAlunoDisciplina(String nomeDisciplina, Aluno aluno)throws DisciplinaNaoExisteException, AlunoNaoExisteException,
            IllegalArgumentException{
         this.disciplinas.cadastrarAluno(nomeDisciplina, aluno);
    }
    
    @Override
    public void cadastrarTarefaDisciplina(String nomeDisciplina, Tarefa tarefa)throws DisciplinaNaoExisteException, TarefaNaoExisteException, 
            IllegalArgumentException{
         this.disciplinas.cadastrarTarefa(nomeDisciplina, tarefa);
    }

    @Override
    public void cadastrarProfessor(Professor professor) throws ProfessorJaExisteException{
        this.professores.cadastrar(professor);
    }

    @Override
    public void cadastrarProfessor(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws ProfessorJaExisteException, IllegalArgumentException{
        this.professores.cadastrar(nome, email, sexo, dataNascimento, senha, cpf);
    }

    @Override
    public void descadastrarProfessor(Professor professor) throws ProfessorNaoExisteException, IllegalArgumentException{
        this.professores.descadastrar(professor);
    }

    @Override
    public Professor buscarProfessor(String cpf) throws ProfessorNaoExisteException, IllegalArgumentException{
        return this.professores.procurar(cpf);
    }
    
    
    @Override
    public void atualizarProfessor(Professor antigoProfessor, String cpf, LocalDate dataNascimento, String email, String nome, String senha, char sexo) throws ProfessorNaoExisteException{
        this.professores.atualizar(antigoProfessor, cpf, dataNascimento, email, nome, senha, sexo);
    }

    @Override
    public boolean existeProfessor(Professor professor){
        return this.professores.existe(professor);
    }

    @Override
    public void cadastrarTarefa(Tarefa tarefa) throws TarefaJaExisteException{
        this.tarefas.cadastrar(tarefa);
    }

    @Override
    public void cadastrarTarefa(String descricao, LocalDate dataInicio,LocalDate dataTermino, int codigoTarefa, Disciplina disciplina)
            throws TarefaJaExisteException, IllegalArgumentException, DisciplinaNaoExisteException{
        this.tarefas.cadastrar(descricao, dataInicio, dataTermino, codigoTarefa, disciplina);
    }

    @Override
    public void descadastrarTarefa(Tarefa tarefa) throws TarefaNaoExisteException{
        this.tarefas.descadastrar(tarefa);
    }

    @Override
    public Tarefa buscarTarefa(int codigo) throws TarefaNaoExisteException, IllegalArgumentException{
        return this.tarefas.procurar(codigo);
    }
    
    @Override
    public void atualizarTarefa(Tarefa antigaTarefa, String descricao, LocalDate dataInicio, LocalDate dataTermino, int codigoTarefa) throws TarefaNaoExisteException{
        this.tarefas.atualizar(antigaTarefa, descricao, dataInicio, dataTermino, codigoTarefa);
    }

    @Override
    public boolean existeTarefa(Tarefa tarefa) throws TarefaNaoExisteException{
        return this.tarefas.existe(tarefa);
    }
    
    @Override
    public boolean existeAlunoDiscilina(Disciplina disciplina, Aluno aluno)throws IllegalArgumentException{
        return this.disciplinas.existeAluno(disciplina, aluno);
    }

}