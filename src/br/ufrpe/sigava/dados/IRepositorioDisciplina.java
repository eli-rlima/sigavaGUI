package br.ufrpe.sigava.dados;

import br.ufrpe.sigava.negocio.beans.Disciplina;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositorioDisciplina {

    /**
     * Adiciona uma disciplina no repositório
     *
     * @param disciplina
     *             disciplina a ser adicionada
     *
     * @return true ou false, caso tenha sido adicionada ou não, respectivamente.
     */
    boolean adicionar(Disciplina disciplina);

    /**
     * Adiciona uma nova disciplina no repositório
     *
     * @param nome
     *           nome da disciplina
     * @param dataInicio
     *           data de início das aulas
     * @param diaAula
     *           dia que ocorrem as aulas
     * @param duracaoAula
     *           duração em horas da aula
     * @param cargaHoraria
     *           carga horária completa da disciplina
     *
     * @return true ou false, caso tenha sido adicionada ou não, respectivamente.
     */
    boolean adicionar(String nome, LocalDate dataInicio, DayOfWeek diaAula, int duracaoAula, int cargaHoraria);

    /**
     * Remove a disciplina fornecida como parâmetro do repositório
     *
     * @param disciplina
     *                 disciplina a ser removida
     *
     * @return true ou false, caso tenhas sido removida ou não, respectivamente.
     */
    boolean remover(Disciplina disciplina);

    /**
     * Procura uma disciplina baseado no nome dela
     *
     * @param nome
     *           nome da disciplina a ser procurada
     *
     * @return a disciplina encontrada
     */
    Disciplina buscar(String nome);

    /**
     * Verifica se existe a disciplina no repositório
     *
     * @param disciplina
     *            disciplina a ser procurada
     *
     * @return true ou false, caso tenha sido encontrada ou não, respectivamente.
     */
    boolean existe(Disciplina disciplina);

    ArrayList<Disciplina> listarDisciplinas();

}
