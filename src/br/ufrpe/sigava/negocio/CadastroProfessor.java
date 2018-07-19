package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioProfessor;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.dados.RepositorioProfessor;
import br.ufrpe.sigava.exceptions.ProfessorJaExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import java.time.LocalDate;
import java.util.ArrayList;


public class CadastroProfessor {
    private IRepositorioProfessor repositorioProfessor;

    public CadastroProfessor (){
        this.repositorioProfessor = RepositorioProfessor.getInstance();
    }

    public void cadastrar (Professor professor)throws ProfessorJaExisteException{
        if (professor != null && !repositorioProfessor.existe(professor)){
            this.repositorioProfessor.adicionar(professor);
         }else{
            ProfessorJaExisteException jaExiste = new ProfessorJaExisteException();
            throw jaExiste;
        }
    }
        

    public ArrayList<Professor> listarProfessores (){
        return repositorioProfessor.listarProfessores();
    }

    public void cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws ProfessorJaExisteException, IllegalArgumentException {
        Professor professor = null;
        if (nome != null && email != null && senha != null && cpf != null){ //TODO
            if(sexo == 'm' || sexo == 'f'){ //TODO
                professor = repositorioProfessor.buscarCpf(cpf);
            }else{}
            if (professor == null){ //TODO
                 this.repositorioProfessor.adicionar(nome,email,sexo,dataNascimento,senha,cpf);
            }else{
                throw new ProfessorJaExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)!");}
    }

    public void descadastrar (Professor professor) throws ProfessorNaoExisteException{
        if (professor != null && repositorioProfessor.existe(professor)){ //TODO
            this.repositorioProfessor.remover(professor);
        }else{
            ProfessorNaoExisteException naoExiste = new ProfessorNaoExisteException();
            throw naoExiste;
        }
    }

    public Professor procurar (String cpf) throws ProfessorNaoExisteException,IllegalArgumentException{
        Professor professor = null;
        if (cpf != null){
            if(repositorioProfessor.buscar(cpf)!= null){
            professor = this.repositorioProfessor.buscarCpf(cpf);
        }else{
            throw new ProfessorNaoExisteException();
    }
    }
        else{
            throw new IllegalArgumentException("Argumento inválido!");
        }
        return professor;
    }

    public boolean existe(Professor professor) throws IllegalArgumentException {
        boolean retorno = false;
        if(professor != null){ //TODO
            retorno = this.repositorioProfessor.existe(professor);
        }else{
            throw new IllegalArgumentException("Argumento Inválido!");
        }
        return retorno;
    }
}