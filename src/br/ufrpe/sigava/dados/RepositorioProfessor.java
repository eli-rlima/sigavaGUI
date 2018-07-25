package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;


public class RepositorioProfessor implements IRepositorioProfessor, Serializable {
    private ArrayList<Professor> repositorioProfessor;
    private static RepositorioProfessor instance;

    private RepositorioProfessor(){
        repositorioProfessor = new ArrayList<Professor>();
    }

    public static RepositorioProfessor getInstance(){
        if (instance == null){
            instance = lerDoArquivo();
        }
        return instance;
    }
    
    public ArrayList <Professor> listarProfessores(){
        return this.repositorioProfessor;
    }

    @Override
    public boolean adicionar (Professor professor){
        return this.repositorioProfessor.add(professor);
    }

    @Override
    public boolean adicionar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf){
        Professor professor =  new Professor(nome, email, sexo,dataNascimento,senha,cpf);
        return adicionar(professor);
    }
    @Override
    public boolean remover (Professor professor){
        return this.repositorioProfessor.remove(professor);
    }
    @Override
    public Professor buscar (String nome){
        Professor professor = null;

        for(int i = 0; i<this.repositorioProfessor.size(); i++){
            if(this.repositorioProfessor.get(i).getNome().equalsIgnoreCase(nome)){
                professor = this.repositorioProfessor.get(i);
            }
        }
        return professor;
    }

    public Professor buscarCpf (String cpf){
        Professor professor = null;
        for (int i = 0; i < this.repositorioProfessor.size(); i++) {
            if (this.repositorioProfessor.get(i).getCpf().equalsIgnoreCase(cpf)){
                professor = this.repositorioProfessor.get(i);
            }
        }
        return professor;
    }


    @Override
    public boolean existe (Professor professor){
        return this.repositorioProfessor.contains(professor);
    }
    
    private static RepositorioProfessor lerDoArquivo() {
    RepositorioProfessor instanciaLocal = null;
    File in = new File("Professor.dat");
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(in);
      ois = new ObjectInputStream(fis);
      Object o = ois.readObject();
      instanciaLocal = (RepositorioProfessor) o;
    } catch (Exception e) {
      instanciaLocal =  new RepositorioProfessor();
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
    File out = new File("Professor.dat");
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
