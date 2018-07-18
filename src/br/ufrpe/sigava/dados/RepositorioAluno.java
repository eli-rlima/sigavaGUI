package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioAluno implements IRepositorioAluno{
    private ArrayList<Aluno> repositorioAluno;
    private static RepositorioAluno instance;

    private RepositorioAluno(){
        this.repositorioAluno = new ArrayList<Aluno>();
    }

    public static RepositorioAluno getInstance(){
        if (instance == null){
            instance = new RepositorioAluno();
        }
        return instance;
    }
    
    @Override
    public ArrayList<Aluno> listarAlunos(){
        return this.repositorioAluno;
    }

    @Override
    public boolean adicionar (Aluno aluno){
        return this.repositorioAluno.add(aluno);
    }

    @Override
    public boolean adicionar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf){
        Aluno aluno = new Aluno(nome,email,sexo,dataNascimento,senha,cpf);
        return adicionar(aluno);
    }

    @Override
    public boolean remover (Aluno aluno){
        return this.repositorioAluno.remove(aluno);
    }

    @Override
    public Aluno buscar (String cpf){
        Aluno aluno = null;
        for (int i = 0; i < this.repositorioAluno.size(); i++) {
            if (this.repositorioAluno.get(i).getCpf().equalsIgnoreCase(cpf)) {
                aluno = this.repositorioAluno.get(i);
            }
        }
        return aluno;
    }

    @Override
    public boolean existe (Aluno aluno) {
        return this.repositorioAluno.contains(aluno);
    }

    @Override
    public boolean adicionarMarcacao(String nome, Aluno aluno, int codigoTarefa, LocalDate dataTermino){
        Cronograma cronograma = aluno.buscarCronograma(nome);
        return cronograma.adicionar(codigoTarefa, dataTermino);
    }
    
    @Override
    public boolean removerMarcacao(String nome, Aluno aluno, Marcacao marcacao){
        Cronograma cronograma = aluno.buscarCronograma(nome);
        return cronograma.remover(marcacao);
    }
    
    public Marcacao buscarMarcacao(String nome, Aluno aluno, int codigoTarefa){
        Marcacao marcacao =  null;
        Cronograma cronograma = null;
        if(nome != null && aluno != null && codigoTarefa >= 0){
            for(int i = 0; i < repositorioAluno.size(); i++){
                if(repositorioAluno.get(i).equals(aluno)){
                    if(repositorioAluno.get(i).buscarCronograma(nome) != null){
                        cronograma = repositorioAluno.get(i).buscarCronograma(nome);
                        if(cronograma.buscarMarcacao(codigoTarefa) != null){
                            marcacao = cronograma.buscarMarcacao(codigoTarefa);
                        }
                    }
                }
            }
        }
        return marcacao;
    }
    
    public boolean existeMarcacao(Aluno aluno, Marcacao marcacao, String nome){
        boolean existe = false;
        Cronograma cronograma = null;
        if(marcacao != null){
            for(int i = 0; i < repositorioAluno.size(); i++){
                if(repositorioAluno.get(i).equals(aluno)){
                    if(repositorioAluno.get(i).buscarCronograma(nome) != null){
                        cronograma = repositorioAluno.get(i).buscarCronograma(nome);
                        if(cronograma.existeMarcacao(marcacao)){
                            existe = true;
                        }
                    }
                }
            }
        }
        return existe;
    }
    
    public void adicionarCronograma(Aluno aluno, String nomeCronograma){
        if(aluno != null && nomeCronograma != null){
            for(int i = 0; i < repositorioAluno.size(); i++){
                if(repositorioAluno.get(i).equals(aluno)){
                    Cronograma cronograma = new Cronograma(nomeCronograma);
                    repositorioAluno.get(i).adicionarCronograma(cronograma);
                }
            }
        }
    }
    
    public void removerCronograma(Aluno aluno, String nomeCronograma){
        if(aluno != null && nomeCronograma != null){
            for(int i = 0; i < repositorioAluno.size(); i++){
                if(repositorioAluno.get(i).equals(aluno)){
                    Cronograma cronograma = new Cronograma(nomeCronograma);
                    repositorioAluno.get(i).removerCronograma(cronograma);
                }
            }
        }
    }
    
    public Cronograma buscarCronograma(Aluno aluno, String nomeCronograma){
        Cronograma cronograma = null;
        if(aluno != null && nomeCronograma != null){
            for(int i = 0; i < repositorioAluno.size(); i++){
                if(repositorioAluno.get(i).equals(aluno)){
                    cronograma = repositorioAluno.get(i).buscarCronograma(nomeCronograma);
                }
            }
        }
        return cronograma;
    }
    
    @Override
    public boolean existeCronograma (Aluno aluno, String nomeCronograma) {
        boolean retorno = false;
        if (aluno != null && nomeCronograma != null) {
            for (int i = 0; i < aluno.getCronogramas().size(); i++) {
                if (aluno.getCronogramas().get(i).getNome().equalsIgnoreCase(nomeCronograma)) {
                    retorno = true;
                }
            }
        }
        return retorno;
    }
    
    @Override
    public ArrayList<Disciplina> listarDisciplinas (Aluno aluno){
        return aluno.getDisciplinas();
    }

}
