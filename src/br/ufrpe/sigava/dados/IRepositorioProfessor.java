package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.pessoa.Professor;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositorioProfessor {
    /**
     * Adiciona um professor no repositório professor
     *
     * @param professor
     *          professor a ser adicionado
     *
     * @return true ou false, caso seja adicionado true, caso não false.
     */
    public boolean adicionar (Professor professor);

    /**
     * Adiciona um novo professor no repositório professor
     *
     * @param nome
     *          nome do professor
     * @param email
     *          email do professor
     * @param sexo
     *          sexo do professor
     * @param dataNascimento
     *           data de nascimento do professor
     * @param senha
     *           senha de acesso ao sistema do professor
     * @param cpf
     *           CPF do professor
     *
     * @return true ou false, caso seja adicionado true, caso não false.
     */
    public boolean adicionar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf);

    /**
     * Remove um professor do repositório professor
     *
     * @param professor
     *             professor a ser removido
     *
     * @return true ou false, caso seja removido true, caso não false.
     */
    public boolean remover (Professor professor);

    /**
     * Procura um professor no repositório professor
     *
     * @param nome
     *          nome do professor a ser procurado
     *
     * @return o professor encontrado
     */
    public Professor buscar (String nome);

    public Professor buscarCpf (String cpf);

    /**
     * Verifica se o professor está no repositório
     *
     * @param professor
     *             professor a ser procurado
     *
     * @return true ou false, caso seja encontrado true, caso não false.
     */
    public boolean existe (Professor professor);

    ArrayList<Professor> listarProfessores();
    
   /**
   * Método responsável por Salvar todo o repositório em um arquivo específico,
   * apagando o conteúdo salvo arteriormente no arquivo ou criando um novo
   * arquivo se o mesmo não existir.
   * 
   */
  void salvarArquivo();




}
