package br.ufrpe.sigava.negocio.beans.pessoa;

import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;

import java.time.LocalDate;
import java.util.ArrayList;

public  class Aluno extends Pessoa{
    private ArrayList <Cronograma> cronogramas = new ArrayList<Cronograma>();
    private ArrayList <Disciplina> disciplinas = new ArrayList<Disciplina>();

    public Aluno(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf){
        super(nome, email, sexo, dataNascimento, senha, cpf);
    }

    public ArrayList <Cronograma> getCronogramas() {
        return cronogramas;
    }

    public ArrayList<Disciplina> getDisciplinas(){
        return disciplinas;
    }

    public boolean adicionarDisciplina(Disciplina disciplina){
        return disciplinas.add(disciplina);
    }//

    public boolean removerDisciplina(Disciplina disciplina){
        return disciplinas.remove(disciplina);
    }//

    public Disciplina buscarDisciplina(String nomeDisciplina){
        Disciplina disciplina = null;
        for (int i = 0; i < this.disciplinas.size(); i++){
            if (this.disciplinas.get(i).getNome().equalsIgnoreCase(nomeDisciplina)){
                disciplina = this.disciplinas.get(i);
            }
        }
        return disciplina;
    }

    public boolean existeDisciplina(Disciplina disciplina){
        return this.disciplinas.contains(disciplina);
    }

    public boolean adicionarCronograma(Cronograma cronograma){
        return cronogramas.add(cronograma);
    }

    public boolean removerCronograma(Cronograma cronograma){
        return cronogramas.remove(cronograma);
    }

    public Cronograma buscarCronograma(String semestre){
        Cronograma cronograma = null;
        for (int i = 0; i < cronogramas.size(); i++){
            if (this.cronogramas.get(i).getNome().equals(semestre)){
                cronograma = this.cronogramas.get(i);
            }
        }
        return cronograma;
    }

    public boolean existeCronograma(Cronograma cronograma){
        return cronogramas.contains(cronograma);
    }
}