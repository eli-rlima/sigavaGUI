package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioAluno implements IRepositorioAluno, Serializable{
    private ArrayList<Aluno> repositorioAluno;
    private static RepositorioAluno instance;

    private RepositorioAluno(){
        this.repositorioAluno = new ArrayList<Aluno>();
    }

    public static RepositorioAluno getInstance(){
        if (instance == null){
            instance = lerDoArquivo();
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
    @Override
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
    
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    
    private static RepositorioAluno lerDoArquivo() {
    RepositorioAluno instanciaLocal = null;
    File in = new File("Aluno.dat");
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(in);
      ois = new ObjectInputStream(fis);
      Object o = ois.readObject();
      instanciaLocal = (RepositorioAluno) o;
    } catch (Exception e) {
      instanciaLocal =  new RepositorioAluno();
    } finally {
      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {/* Silent exception */
        }
      }
    }

    return instanciaLocal;
  }
      
    @Override
    public void salvarArquivo() {
    if (instance == null) {
      return;
    }
    File out = new File("Aluno.dat");
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    try {
      fos = new FileOutputStream(out);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(instance);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
          /* Silent */}
      }
    }
  }

}
