package br.ufrpe.sigava.dados;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;


public class RepositorioTarefa implements IRepositorioTarefa, Serializable {

    private ArrayList <Tarefa> repositorioTarefas;
    private static RepositorioTarefa instance;

    private RepositorioTarefa(){
        this.repositorioTarefas = new ArrayList<Tarefa>();
    }


    public static RepositorioTarefa getInstance (){
        if (instance == null){
            instance = lerDoArquivo();
        }
        return instance;
    }

    public ArrayList <Tarefa> listarTarefas(){
       return this.repositorioTarefas;
    }



    @Override
    public boolean adicionar(Tarefa tarefa){
        return this.repositorioTarefas.add(tarefa);
    }

    @Override
    public boolean adicionar (String descricao, LocalDate dataInicio,
                              LocalDate dataTermino, int codigoTarefa, Disciplina disciplina){
        Tarefa tarefa = new Tarefa(descricao,dataInicio,dataTermino,codigoTarefa,disciplina);
        return adicionar(tarefa);
    }

    @Override
    public boolean remover(Tarefa tarefa){
        return this.repositorioTarefas.remove(tarefa);
    }
    @Override
    public Tarefa buscar(int codigo){
        Tarefa tarefa = null;
        for (int i = 0; i < this.repositorioTarefas.size(); i++){
            if (this.repositorioTarefas.get(i).getCodigoTarefa() == codigo){
                tarefa = this.repositorioTarefas.get(i);
            }
        }
        return tarefa;
    }
    @Override
    public boolean existe(Tarefa tarefa){
        return this.repositorioTarefas.contains(tarefa);
    }

    private static RepositorioTarefa lerDoArquivo() {
    RepositorioTarefa instanciaLocal = null;
    File in = new File("Professor.dat");
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(in);
      ois = new ObjectInputStream(fis);
      Object o = ois.readObject();
      instanciaLocal = (RepositorioTarefa) o;
    } catch (Exception e) {
      instanciaLocal =  new RepositorioTarefa();
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
    File out = new File("Tarefa.dat");
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

