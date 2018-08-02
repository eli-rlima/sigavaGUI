package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioDisciplina implements IRepositorioDisciplina, Serializable {
    private ArrayList<Disciplina> repositorioDisciplina;
    private static RepositorioDisciplina instance;

    private RepositorioDisciplina(){
        this.repositorioDisciplina = new ArrayList<Disciplina>();
    }

    public static IRepositorioDisciplina getInstance(){
        if (instance == null){
            instance = lerDoArquivo();
        }
        return instance;
    }

    @Override
    public ArrayList<Disciplina> listarDisciplinas(){
        return this.repositorioDisciplina;
    }

    @Override
    public boolean adicionar(Disciplina disciplina){
        return this.repositorioDisciplina.add(disciplina);
    }

    @Override
    public boolean adicionar(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria){
        Disciplina disciplina = new Disciplina(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);
        return adicionar(disciplina);
    }
    @Override
    public boolean remover(Disciplina disciplina){
        return this.repositorioDisciplina.remove(disciplina);
    }

    @Override
    public Disciplina buscar(String nome){
        Disciplina disciplina = null;
        for (int i = 0; i < this.repositorioDisciplina.size(); i++){
            if (this.repositorioDisciplina.get(i).getNome().equalsIgnoreCase(nome)){
                disciplina = this.repositorioDisciplina.get(i);
            }
        }
        return disciplina;
    }
    
    @Override
    public void atualizar(Disciplina antigaDisciplina, String nome, int duracaoAula, DayOfWeek diaAula, LocalDate dataInicio, int cargaHoraria){
        antigaDisciplina.setNome(nome);
        antigaDisciplina.setDuracaoAula(duracaoAula);
        antigaDisciplina.setDiaAula(diaAula);
        antigaDisciplina.setDataInicio(dataInicio);
        antigaDisciplina.setCargaHoraria(cargaHoraria);
    }
    
    @Override
    public boolean existe(Disciplina disciplina){
        return this.repositorioDisciplina.contains(disciplina);
    }
    
    private static RepositorioDisciplina lerDoArquivo() {
    RepositorioDisciplina instanciaLocal = null;
    File in = new File("Disciplina.dat");
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(in);
      ois = new ObjectInputStream(fis);
      Object o = ois.readObject();
      instanciaLocal = (RepositorioDisciplina) o;
    } catch (Exception e) {
      instanciaLocal =  new RepositorioDisciplina();
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
    File out = new File("Disciplina.dat");
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
    
     @Override 
     public boolean existeAluno(Disciplina disciplina, Aluno aluno){
         return disciplina.existeAluno(aluno);
    }
     
     @Override
     public boolean existeProfessor(Disciplina disciplina, Professor professor){
         return disciplina.existeProfessor(professor);
     }
     
     @Override
     public boolean removerAlunoDisciplina (Disciplina disciplina, Aluno aluno){
         return disciplina.removerAluno(aluno);
     }
     
     @Override
     public boolean removerProfessorDisciplina (Disciplina disciplina){
         return disciplina.removerProfessor();
     }
 
      
      
}
