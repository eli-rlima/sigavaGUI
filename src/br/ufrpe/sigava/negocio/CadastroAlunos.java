package br.ufrpe.sigava.negocio;

import br.ufrpe.sigava.dados.IRepositorioAluno;
import br.ufrpe.sigava.negocio.beans.Marcacao;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.dados.RepositorioAluno;
import br.ufrpe.sigava.exceptions.AlunoJaExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.AlunoNaoExisteNaDisciplinaException;
import br.ufrpe.sigava.exceptions.CronogramaNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.MarcacaoNaoExisteException;
import br.ufrpe.sigava.exceptions.TarefaNaoExisteException;
import br.ufrpe.sigava.gui.SigavaGUI;
import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroAlunos {
    private IRepositorioAluno repositorioAluno;

    public CadastroAlunos(){
        this.repositorioAluno = RepositorioAluno.getInstance();
    }

    public void cadastrar(Aluno aluno) throws AlunoJaExisteException {
       if(!repositorioAluno.existe(aluno)){
           this.repositorioAluno.adicionar(aluno);
           this.repositorioAluno.salvarArquivo();
        } else{ 
           throw new AlunoJaExisteException();
       }
    }

    public void cadastrar (String nome, String email, char sexo, LocalDate dataNascimento, String senha, String cpf)
            throws AlunoJaExisteException, IllegalArgumentException{
        if (nome != null && email != null && sexo == 'm' || sexo == 'f' && dataNascimento != null && senha != null &&
                cpf != null) { //TODO
            Aluno aluno = repositorioAluno.buscar(cpf);
            if (aluno == null) { //TODO
                 repositorioAluno.adicionar(nome, email, sexo, dataNascimento, senha, cpf);
                 this.repositorioAluno.salvarArquivo();
            }else{
                throw new AlunoJaExisteException();
            }
        }else{
           throw new IllegalArgumentException("Argumento(s) inválido(s)!");
        }
    }
    
    public void descadastrar(Aluno aluno) throws AlunoNaoExisteException {
         if(aluno != null && repositorioAluno.existe(aluno)){
             ArrayList<Disciplina> disciplinas = aluno.getDisciplinas();
             for (int i = 0; i < disciplinas.size(); i++) {
                 try{
                    ServidorSigava.getIstance().RemoverAluno(disciplinas.get(i), aluno);
                 }catch(AlunoNaoExisteException | AlunoNaoExisteNaDisciplinaException | DisciplinaNaoExisteException e){
                  //silent
                 }                
             }
            this.repositorioAluno.remover(aluno);
            this.repositorioAluno.salvarArquivo();
        }else{
             throw new AlunoNaoExisteException();
         }
    }

    public ArrayList<Aluno> listarAlunos (){
        return repositorioAluno.listarAlunos();
    }

    public Aluno procurar (String cpf) throws AlunoNaoExisteException, IllegalArgumentException{
        Aluno aluno = null;
        if(cpf != null){ 
            if(repositorioAluno.buscar(cpf) != null){
                aluno = repositorioAluno.buscar(cpf);
            }
            else{
                throw new AlunoNaoExisteException();
            }
        }
        else{
            throw new IllegalArgumentException("Argumento inválido!");
        }
        return aluno;
    }
    
    public void atualizar (Aluno antigoAluno, String nome, String email, String cpf, String senha, char sexo, LocalDate dataNascimento) throws AlunoNaoExisteException{
        if(repositorioAluno.buscar(antigoAluno.getCpf()) != null){
            if(antigoAluno != null && nome != null && email != null && cpf != null && senha!= null && dataNascimento != null){
              repositorioAluno.atualizar(antigoAluno, nome, email, cpf, senha, sexo, dataNascimento);
              repositorioAluno.salvarArquivo();  
            }else{
                throw new IllegalArgumentException("Argumento(s) inválido(s)!"); 
            }
        }else{
                throw new AlunoNaoExisteException();
            }
    }

    public boolean existe(Aluno aluno) throws IllegalArgumentException{
        boolean retorno = false;
        if(aluno != null){ 
            retorno = repositorioAluno.existe(aluno);
        }
        else{
            throw new IllegalArgumentException("Argumento Inválido!");
        }
        return retorno;
    }
    
    public boolean adicionarMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino)
                    throws TarefaNaoExisteException, AlunoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException, 
                    DisciplinaNaoExisteException{
        boolean retorno = false;
        Marcacao marcacao = null;
        Tarefa tarefa = null;
        if(nomeCronograma != null){
            if(repositorioAluno.existe(aluno)){
                if(repositorioAluno.existeCronograma(aluno,nomeCronograma)){
                    if (codigoTarefa >= 0){
                        if(aluno.buscarDisciplina(nomeDisciplina) != null){
                            if(aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa) != null){
                                tarefa = aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa);
                                if (tarefa.getDataTermino().isEqual(dataTermino) || tarefa.getDataTermino().isAfter(dataTermino)){ //TODO
                                    marcacao = new Marcacao(codigoTarefa,dataTermino);
                                    retorno = repositorioAluno.adicionarMarcacao(nomeCronograma, aluno, codigoTarefa, dataTermino);
                                    this.repositorioAluno.salvarArquivo();
                                }
                            }else{
                                throw new TarefaNaoExisteException();
                            }
                        }else{
                            throw new DisciplinaNaoExisteException();
                        }
                    }else{
                        throw new IllegalArgumentException("Argumento inválido");
                    }
                } else{
                    throw new CronogramaNaoExisteException();
                }
            }else{
                throw new AlunoNaoExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento inválido!");
        }
        return retorno;
    }
    
    public void removerMarcacao(String nomeDisciplina, String nomeCronograma, Aluno aluno, int codigoTarefa, LocalDate dataTermino)
                throws TarefaNaoExisteException, DisciplinaNaoExisteException, IllegalArgumentException{
        Tarefa tarefa;
        Marcacao marcacao;
        if(nomeCronograma != null){ 
            if (codigoTarefa >= 0){
                if(aluno.buscarDisciplina(nomeDisciplina) != null){
                    if(aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa) != null){
                        tarefa = aluno.buscarDisciplina(nomeDisciplina).procurarTarefa(codigoTarefa);
                        if (tarefa.getDataTermino().isEqual(dataTermino) || tarefa.getDataTermino().isBefore(dataTermino)){ //TODO
                            marcacao = new Marcacao(codigoTarefa,dataTermino);
                            repositorioAluno.removerMarcacao(nomeCronograma, aluno, marcacao);
                            this.repositorioAluno.salvarArquivo();
                        }
                    }else{
                        throw new TarefaNaoExisteException();
                    }
                }else{
                    throw new DisciplinaNaoExisteException();
                }
            }else{
                throw new IllegalArgumentException("Argumento inválido");
            }        
        }else{
            throw new IllegalArgumentException("Argumento inválido");
        }
    }
    
    public void removerMarcacao(Marcacao marcacao, String nomeCronograma, Aluno aluno) 
                throws AlunoNaoExisteException, MarcacaoNaoExisteException, IllegalArgumentException{
        if(marcacao != null){
            if(nomeCronograma != null){
                if(aluno != null){
                    repositorioAluno.removerMarcacao(nomeCronograma, aluno, marcacao);
                    this.repositorioAluno.salvarArquivo();
                }else{
                    throw new AlunoNaoExisteException();
                }
            }else{
                throw new IllegalArgumentException("Argumento inválido");
            }
        }else{
            throw new MarcacaoNaoExisteException();
        }
    }
    
    public Marcacao buscarMarcacao(String nome, Aluno aluno, int codigoTarefa)
            throws MarcacaoNaoExisteException, CronogramaNaoExisteException, IllegalArgumentException{
        Marcacao marcacao = null;
        Cronograma cronograma = null;
        if(aluno != null && nome != null && codigoTarefa >= 0){ //TODO
            if(aluno.buscarCronograma(nome) != null){ //TODO
                cronograma = aluno.buscarCronograma(nome);
                if(cronograma.buscarMarcacao(codigoTarefa)!= null){ //TODO
                    marcacao = cronograma.buscarMarcacao(codigoTarefa);
                }else{
                    throw new MarcacaoNaoExisteException();
                }
            }else{
                throw new CronogramaNaoExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)");
        }
        return marcacao;
    }
    
    public void atualizarMarcacoes(String nomeCronograma, Aluno aluno) 
                throws TarefaNaoExisteException, DisciplinaNaoExisteException, MarcacaoNaoExisteException, 
                CronogramaNaoExisteException, IllegalArgumentException{
        if(nomeCronograma != null && aluno != null){
            if(aluno.buscarCronograma(nomeCronograma)!= null){
                for(int i = 0; i < aluno.buscarCronograma(nomeCronograma).marcacoes().size(); i++){
                    for(int j = 0; j < aluno.getDisciplinas().size(); j++){
                        if(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i) != null){
                            if(aluno.getDisciplinas().get(j) != null){
                                if(aluno.getDisciplinas().get(j).procurarTarefa(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getCodigoTarefa())!= null){
                                    if(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getDataTermino().isAfter(aluno.getDisciplinas().get(j).procurarTarefa(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i).getCodigoTarefa()).getDataTermino())){
                                        aluno.buscarCronograma(nomeCronograma).remover(aluno.buscarCronograma(nomeCronograma).marcacoes().get(i));
                                        this.repositorioAluno.salvarArquivo();
                                    }
                                }else{
                                    throw new TarefaNaoExisteException();
                                }
                            }else{
                                throw new DisciplinaNaoExisteException();
                            }
                        }else{
                            throw new MarcacaoNaoExisteException();
                        }
                    }
                }
            }else{
                throw new CronogramaNaoExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)");
        }
    }
    
    public boolean existeMarcacao(Aluno aluno, Marcacao marcacao, String nome) throws CronogramaNaoExisteException, 
            MarcacaoNaoExisteException, IllegalArgumentException{
        boolean existe = false;
        if(aluno != null && marcacao != null && nome != null){ //TODO
            if(aluno.buscarCronograma(nome)!= null){
                Cronograma cronograma = aluno.buscarCronograma(nome);
                if(cronograma.buscarMarcacao(marcacao.getCodigoTarefa())!= null){
                    existe = true;
                }else{
                    throw new MarcacaoNaoExisteException();
                }
            }else{
                throw new CronogramaNaoExisteException();
            }
        }else{
            throw new IllegalArgumentException("Argumento(s) inválido(s)");
        }
        return existe;
    }
    
    public void adicionarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        Cronograma cronograma;
        if(aluno != null){
            if(nomeCronograma != null){
                if(aluno.buscarCronograma(nomeCronograma) == null){
                    cronograma = new Cronograma(nomeCronograma);
                    aluno.adicionarCronograma(cronograma);
                    this.repositorioAluno.salvarArquivo();
                }else{
                    throw new CronogramaNaoExisteException();
                }
            }else{
                throw new IllegalArgumentException("Argumento inválido");
            }
        }else{
            throw new AlunoNaoExisteException();
        }
    }
    
    public void removerCronograma(Aluno aluno, String nomeCronograma)throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        Cronograma cronograma;
        if(aluno != null){
            if(nomeCronograma != null){
                if(aluno.buscarCronograma(nomeCronograma) != null){
                    cronograma = aluno.buscarCronograma(nomeCronograma);
                    aluno.removerCronograma(cronograma);
                    this.repositorioAluno.salvarArquivo();
                }else{
                    throw new CronogramaNaoExisteException();
                }
            }else{
                throw new IllegalArgumentException("Argumento inválido");
            }
        }else{
            throw new AlunoNaoExisteException();
        }
    }
    
    public Cronograma buscarCronograma(Aluno aluno, String nomeCronograma) throws AlunoNaoExisteException, 
            CronogramaNaoExisteException, IllegalArgumentException{
        Cronograma cronograma = null;
        if(aluno != null){
            if(nomeCronograma != null){
                if(aluno.buscarCronograma(nomeCronograma)!= null){
                    cronograma = aluno.buscarCronograma(nomeCronograma);
                }else{
                    throw new CronogramaNaoExisteException();
                }
            }else{
                throw new IllegalArgumentException("Argumento inválido");
            }
        }else{
            throw new AlunoNaoExisteException();
        }
        return cronograma;
    }
    
    public boolean removerDisciplina (Disciplina disciplina, Aluno aluno) throws DisciplinaNaoExisteException, AlunoNaoExisteException, AlunoNaoExisteNaDisciplinaException{
        boolean retorno = false;
        if(disciplina != null){
            if (aluno != null){
                try{
                    ServidorSigava.getIstance().RemoverAluno(disciplina, aluno);
                    retorno = repositorioAluno.removerDisciplina(disciplina, aluno);
                    repositorioAluno.salvarArquivo();
                    repositorioAluno.salvarArquivo();
                }catch(AlunoNaoExisteNaDisciplinaException e){
                    //silent
                }                
            }else throw new AlunoNaoExisteException();
        }else throw new DisciplinaNaoExisteException();
        return retorno;
    }
}
