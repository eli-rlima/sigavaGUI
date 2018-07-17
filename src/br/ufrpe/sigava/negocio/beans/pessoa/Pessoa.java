package br.ufrpe.sigava.negocio.beans.pessoa;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Login;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Pessoa {
    private String nome;
    private String email;
    private char sexo;
    private LocalDate dataNascimento;
    private String cpf;
    private Login login;

    public Login getLogin() {
        return login;
    }

    public Pessoa(String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf){
        this.setNome(nome);
        this.setEmail(email);
        this.setSexo(sexo);
        this.setDataNascimento(dataNascimento);
        this.login = new Login(email,senha);
        this.cpf = cpf;
    }
    public Pessoa(){}

    public String getCpf() {
        return cpf;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nome = " + this.getNome() +
                "\nE-mail = " + this.getEmail() +
                "\nSexo = " + this.getSexo() +
                "\nData de nascimento = " + this.getDataNascimento().format(formatter) +
                "\nCPF = " + this.getCpf();

    }

    public boolean equals(Pessoa anotherPessoa){
        boolean equals = false;
        if (this.getCpf().equals(anotherPessoa.getCpf())){
            equals = true;
        }else equals = false;
        return equals;
    }

    public abstract ArrayList<Disciplina> getDisciplinas();

    public abstract Disciplina buscarDisciplina(String nomeDisciplina);
}