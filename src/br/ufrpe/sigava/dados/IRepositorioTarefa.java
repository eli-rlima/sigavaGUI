package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositorioTarefa {
    /**
     * Adiciona uma tarefa no repositório tarefa
     *
     * @param tarefa
     *           tarefa a ser adionada
     *
     * @return true ou false, caso seja adicionado true, caso não false.
     */
    boolean adicionar(Tarefa tarefa);

    /**
     * Adiciona uma nova tarefa no repositório tarefa
     *
     * @param descricao
     *            descrição da tarefa
     * @param dataInicio
     *            data de início da tarefa
     * @param dataTermino
     *            data de término
     * @param codigoTarefa
     *            código da tarefa
     *
     * @return true ou false, caso seja adicionado true, caso não false.
     */
     boolean adicionar (String descricao, LocalDate dataInicio, LocalDate dataTermino,
                        int codigoTarefa, Disciplina disciplina);

    /**
     * Remove uma tareda do repositório tarefa
     *
     * @param tarefa
     *          tarefa a ser removida
     *
     * @return true ou false, caso seja removida true, caso não false.
     */
     boolean remover(Tarefa tarefa);

    /**
     * Procura uma tarefa no repositório tarefa
     *
     * @param codigo
     *          código da tarefa ser buscada
     *
     * @return a tarefa procurada
     */
     Tarefa buscar(int codigo);

    /**
     * Verifica se a tarefa existe
     *
     * @param tarefa
     *           tarefa a ser procurada
     *
     * @return true ou false, caso seja encontrada true, caso não false.
     */
     boolean existe(Tarefa tarefa);

    ArrayList <Tarefa> listarTarefas();


}
