package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioProfessor;
import br.ufrpe.sigava.dados.RepositorioDisciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.dados.RepositorioProfessor;
import br.ufrpe.sigava.exceptions.ProfessorJaExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.beans.Disciplina;
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
            this.repositorioProfessor.salvarArquivo();
         }else throw new ProfessorJaExisteException();
    }
        

    public ArrayList<Professor> listarProfessores (){
        return repositorioProfessor.listarProfessores();
    }

    public void cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) throws ProfessorJaExisteException, IllegalArgumentException {
        Professor professor = null;
        if (nome != null && email != null && senha != null && cpf != null){ //TODO
            if(sexo == 'm' || sexo == 'f'){
                professor = repositorioProfessor.buscarCpf(cpf);
            }else throw new IllegalArgumentException("Argumento inválido");
            if (professor == null){ //TODO
                 this.repositorioProfessor.adicionar(nome,email,sexo,dataNascimento,senha,cpf);
                 this.repositorioProfessor.salvarArquivo();
            }else{
                throw new ProfessorJaExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)!");}
    }

    public void descadastrar (Professor professor) throws ProfessorNaoExisteException, IllegalArgumentException{
        if (professor != null){
            
            if(repositorioProfessor.existe(professor)){
                
                ArrayList <Disciplina> disciplinas = professor.getDisciplinas();
                for (int i = 0; i < disciplinas.size() ; i++) {
                    professor.getDisciplinas().get(i).removerProfessor(professor);
                }
                
                RepositorioDisciplina.getInstance().salvarArquivo();
                this.repositorioProfessor.remover(professor);
                this.repositorioProfessor.salvarArquivo();
                
            }else throw new ProfessorNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido");
    }

    public Professor procurar (String cpf) throws ProfessorNaoExisteException,IllegalArgumentException{
        Professor professor = null;
        if (cpf != null){
            if(repositorioProfessor.buscar(cpf)!= null){
                professor = this.repositorioProfessor.buscarCpf(cpf);
            }else throw new ProfessorNaoExisteException();
        }else throw new IllegalArgumentException("Argumento inválido!");        
        return professor;
    }
    
    public void atualizar(Professor antigoProfessor, String cpf, LocalDate dataNascimento, String email, String nome, String senha, char sexo) throws ProfessorNaoExisteException{
        if(repositorioProfessor.buscar(antigoProfessor.getNome())!= null){
            if(antigoProfessor != null && cpf != null && dataNascimento != null && email != null && nome != null && senha != null){
                repositorioProfessor.atualizar(antigoProfessor, cpf, dataNascimento, email, nome, senha, sexo);
                repositorioProfessor.salvarArquivo();
            }else{
                throw new IllegalArgumentException("Argumento(s) inválido(s)!");
            }
    }else{
            throw new ProfessorNaoExisteException();
        }
    }

    public boolean existe(Professor professor) throws IllegalArgumentException {
        boolean retorno = false;
        if(professor != null){
            retorno = this.repositorioProfessor.existe(professor);
        }else{
            throw new IllegalArgumentException("Argumento Inválido!");
        }
        return retorno;
    }
}