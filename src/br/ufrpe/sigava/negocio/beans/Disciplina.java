package br.ufrpe.sigava.negocio.beans;

import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import java.io.Serializable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




public class Disciplina implements Serializable, Comparable{

    private String nome;
    private Professor professor;
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private DayOfWeek diaAula;
    private int duracaoAula;
    private int cargaHoraria;
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();


    public Disciplina(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula,int cargaHoraria) {
        this.nome= nome;
        this.dataInicio = dataInicio;
        this.diaAula = diaAula;
        this.duracaoAula = duracaoAula;
        this.cargaHoraria = cargaHoraria;
        this.setDataFim(this.gerarDataFim());
    }

    public String ListarAlunos (){
        StringBuilder retorno = new StringBuilder();
        for (int i = 0; i < alunos.size(); i++){
            retorno.append("\n" + alunos.get(i).toString());
        }
        return retorno.toString();
    }

    public String ListarTarefas (){
        StringBuilder retorno = new StringBuilder();
        for (int i = 0; i < tarefas.size(); i++){
            retorno.append("\n" + tarefas.get(i).toString());
        }
        return retorno.toString();
    }

    public int getDuracaoAula() {
        return this.duracaoAula;
    }

    public void setDuracaoAula(int duracaoAula) {
        this.duracaoAula = duracaoAula;
        this.setDataFim(this.gerarDataFim());
    }

    public String getNome() {
    return this.nome;
    }

    public Professor getProfessor() {
    return professor;
    }

    public ArrayList getAlunos() {
    return alunos;
    }
    
    public ArrayList getTarefas(){
        return tarefas;
    }

    public LocalDate getDataInicio() {
    return dataInicio;
    }

    public DayOfWeek getDiaAula() {
    return diaAula;
    }

    public int getCargaHoraria() {
    return this.cargaHoraria;
    }

    public void setNome(String nome) {
    this.nome = nome;
    }

    public void setProfessor(Professor professor) {
    this.professor = professor;
    }

    public void setAlunos(ArrayList alunos) {
    this.alunos = alunos;
    }

    public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
    this.setDataFim(this.gerarDataFim());
    }

    private void setDataFim(LocalDate dataFim) {
    this.dataFim = dataFim;
    }

    public LocalDate getDataFim() {
      return dataFim;
    }

    public void setDiaAula(DayOfWeek diaAula) {
    this.diaAula = diaAula;
    this.setDataFim(this.gerarDataFim());
    }

    public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
    this.setDataFim(this.gerarDataFim());
    }

    public boolean adicionarAluno(Aluno aluno) {
        boolean retorno = false;
        if (aluno != null) {
              this.alunos.add(aluno);
              retorno = true;
        }
        return retorno;
    }
    
    public boolean removerProfessor(Professor professor){
        boolean retorno = false;
        if (this.professor != null){
            professor = null;
            retorno = true;
        }
        return retorno;
    }

    public boolean removerAluno(Aluno aluno) {
        boolean retorno = false;
        if (aluno != null) { //TODO
            this.alunos.remove(aluno);
            retorno = true;
        }
        return retorno;
    }

    public Aluno procurarAluno(String cpf) {
        boolean retorno = false;
        Aluno aluno = null;
        if (cpf != null) { //TODO
            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).getCpf().equals(cpf)){ //TODO
                    aluno = alunos.get(i);
                }
            }
        }
        return aluno;
    }

    public boolean existeAluno(Aluno aluno){
        boolean retorno = false;
        if(aluno != null){ //TODO
            retorno = this.alunos.contains(aluno);
        }
        return retorno;
    }

    public boolean adicionarProfessor(Professor professor) {
    boolean retorno = false;
    if (professor != null) {
      this.professor = professor;
      retorno = true;
    }
    return retorno;
    }

    public boolean adicionarTarefa(Tarefa tarefa) {
    boolean retorno = false;
    if (tarefa != null) {
        this.tarefas.add(tarefa);
        retorno = true;
    }
    return retorno;
    }

    public boolean removerTarefa(Tarefa tarefa){
        boolean retorno = false;
        if(tarefa != null){ //TODO
            retorno = this.tarefas.remove(tarefa);
        }
        return retorno;
    }

    public Tarefa procurarTarefa(int codigoTarefa){
        Tarefa tarefa = null;
        if(codigoTarefa >= 0){ //TODO
            for(int i = 0; i < tarefas.size(); i++){
                if(tarefas.get(i).getCodigoTarefa() == codigoTarefa){ //TODO
                    tarefa = tarefas.get(i);
                }
            }
        }
        return tarefa;
    }

    public boolean existeTarefa(Tarefa tarefa){
        boolean retorno = false;
        if(tarefa != null){ //TODO
            retorno = this.tarefas.contains(tarefa);
        }
        return retorno;
    }

    public LocalDate gerarDataFim(){
      LocalDate dataFim = null;
      LocalDate dataInicioP = this.getDataInicio();
      DayOfWeek diaAula = this.getDiaAula();
      int cargaHoraria = this.getCargaHoraria();
      for (int i = cargaHoraria; i > 0;){
          if (dataInicioP.getDayOfWeek().equals(diaAula)){
              i -= this.getDuracaoAula();
              dataInicioP = dataInicioP.plusDays(1);
            }
          else {
              dataInicioP = dataInicioP.plusDays(1);
          }
        }
        dataInicioP = dataInicioP.plusDays(-1);
        dataFim = dataInicioP;
      return dataFim;
    }

    public int gerarCargaHorariaRestante(){
      int cargaHorariaRestante = 0;
      LocalDate dataInicioP = this.getDataInicio();
      LocalDate hoje = LocalDate.now();
      DayOfWeek diaAula = this.getDiaAula();
      int cargaHoraria = this.getCargaHoraria();

      if (dataInicioP.isBefore(hoje)){
          for (int i = cargaHoraria; dataInicioP.isBefore(hoje);){
               if (dataInicioP.getDayOfWeek().equals(diaAula)) {
                   i -= this.getDuracaoAula();
                   dataInicioP = dataInicioP.plusDays(1);
                   cargaHorariaRestante = i;
               }
               else{
                   dataInicioP = dataInicioP.plusDays(1);
               }
          }

          if (hoje.getDayOfWeek().equals(diaAula)){
              cargaHorariaRestante -= this.getDuracaoAula();
          }
        }
      else cargaHorariaRestante = cargaHoraria;

      return cargaHorariaRestante;
    }

    public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       return "Nome da Disciplina: " + this.getNome() +  "\n"
          + "Inicio da disciplina: " + this.getDataInicio().format(formatter) + "\nData termino da disciplina: "
           + this.getDataFim().format(formatter) + "\nDia que ocorre a aula:" + this.getDiaAula().toString()
           + "\nCarga Horaria: " + this.getCargaHoraria();
 
    }

    public boolean equals(Disciplina obj) {
        boolean retorno = false;
        if (obj.getAlunos() != null && obj.getDataFim() != null && obj.getDataInicio() != null
            && obj.getDiaAula() != null && obj.getNome() != null && obj.getProfessor() != null) {
          if (this.getAlunos() != null && this.getDataFim() != null && this.getDataInicio() != null
              && this.getDiaAula() != null && this.getNome() != null && this.getProfessor() != null) {

            if (this.getNome().equals(obj.getNome()) && this.getAlunos().equals(obj.getAlunos())
                && this.getCargaHoraria() == obj.getCargaHoraria() && this.getDataFim().equals(obj.getDataFim())
                && this.getDataInicio().equals(obj.getDataInicio())
                && this.getProfessor().equals(obj.getProfessor())
                && this.getDiaAula().equals(obj.getDiaAula())) {
              retorno = true;

            }

          }

        }

        return retorno;
      }
    
    @Override
    public int compareTo (Object o){
        Disciplina d = (Disciplina) o;
        return this.getNome().compareTo(d.getNome());
    }

}