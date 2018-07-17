package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioProfessor;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.dados.RepositorioProfessor;
import java.time.LocalDate;
import java.util.ArrayList;


public class CadastroProfessor {
    private IRepositorioProfessor repositorioProfessor;

    public CadastroProfessor (){
        this.repositorioProfessor = RepositorioProfessor.getInstance();
    }

    public boolean cadastrar (Professor professor){
        boolean retorno = false;
        if (professor != null && !repositorioProfessor.existe(professor)){ //TODO
            if (repositorioProfessor.adicionar(professor)){//TODO
                retorno = true;
            }
        }
        return retorno;
    }

    public ArrayList<Professor> listarProfessores (){
        return repositorioProfessor.listarProfessores();
    }

    public boolean cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf) {
        Professor professor = null;
        boolean retorno = false;
        if (nome != null && email != null && senha != null && cpf != null){ //TODO
            if(sexo == 'm' || sexo == 'f'){ //TODO
                professor = repositorioProfessor.buscarCpf(cpf);
            }
            if (professor == null){ //TODO
                 retorno = this.repositorioProfessor.adicionar(nome,email,sexo,dataNascimento,senha,cpf);
            }
        }
        return retorno;
    }

    public boolean descadastrar (Professor professor){
        boolean retorno = false;
        if (professor != null && repositorioProfessor.existe(professor)){ //TODO
            retorno = repositorioProfessor.remover(professor);
        }
        return retorno;
    }

    public Professor procurar (String cpf){
        Professor professor = null;
        if (cpf != null){ //TODO
            professor = this.repositorioProfessor.buscarCpf(cpf);
        }
        return professor;
    }

    public boolean existe(Professor professor){
        boolean retorno = false;
        if(professor != null){ //TODO
            retorno = this.repositorioProfessor.existe(professor);
        }
        return retorno;
    }


}
