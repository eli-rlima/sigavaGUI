package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.beans.Cronograma;
import br.ufrpe.sigava.negocio.beans.Tarefa;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
import br.ufrpe.sigava.negocio.beans.Disciplina;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Sistema {

    private static void MenuAcesso(){
        System.out.println("BEM-VINDO AO SIGAVA\n");
        System.out.println("ESCOLHA UMA OPÇÃO: ");
        System.out.println("1\t - Cadastrar Aluno");
        System.out.println("2\t - Cadastrar Professor");
        System.out.println("3\t - Cadastrar Disciplina");
        System.out.println("4\t - Cadastrar Tarefa");
        System.out.println("5\t - Descadastrar Aluno");
        System.out.println("6\t - Descadastrar Professor");
        System.out.println("7\t - Descadastrar Disciplina");
        System.out.println("8\t - Descadastrar Tarefa");
        System.out.println("9\t - Procurar Aluno");
        System.out.println("10\t - Procurar Professor");
        System.out.println("11\t - Procurar Disciplina");
        System.out.println("12\t - Procurar Tarefa");
        System.out.println("13\t - Listar Alunos");
        System.out.println("14\t - Listar Professores");
        System.out.println("15\t - Listar Disciplinas");
        System.out.println("16\t - Listar Tarefas");
        System.out.println("17\t - Cronograma");
        System.out.println("18\t - Associar Aluno - Disciplina");
        System.out.println("19\t - Associar Professor - Disciplina");
        System.out.println("20\t - Atualizar Alunos");
        System.out.println("21\t - Atualizar Professores");
        System.out.println("22\t - Atualizar Disciplinas");
        System.out.println("23\t - Atualizar Tarefas");
        System.out.println("24\t - SAIR");
    }

    private static void associarAlunoDisc(IServidorSigava servidorSigava){
        Aluno aluno;
        Disciplina disciplina;

        listarDisciplinas(servidorSigava);
        disciplina = selecionarDisciplina(servidorSigava);

        listarAlunos(servidorSigava);
        aluno = selecionarAluno(servidorSigava);

        servidorSigava.cadastrarAlunoDisciplina(disciplina.getNome(), aluno);
    }

    private static Disciplina selecionarDisciplina(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        String nome;
        Disciplina disciplina = null;

        System.out.println("\nDigite o nome da disciplina selecionada: ");
        nome = in.nextLine();

        if (servidorSigava.buscarDisciplina(nome) == null){
            System.out.println("Disciplina não encontrada!");
        }
        else {
            disciplina = servidorSigava.buscarDisciplina(nome);
        }
        return disciplina;
    }

    private static Professor selecionarProfessor(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        String cpf;
        Professor professor = null;

        System.out.println("\nDigite o CPF do professor selecionado: ");
        cpf = in.nextLine();

        if (servidorSigava.buscarProfessor(cpf) == null){
            System.out.println("Professor não encontrado!");
        }
        else {
            professor = servidorSigava.buscarProfessor(cpf);
        }
        return professor;
    }

    private static void associarDiscProf(IServidorSigava servidorSigava){
        Professor professor;
        Disciplina disciplina;
        listarDisciplinas(servidorSigava);
        disciplina = selecionarDisciplina(servidorSigava);
        listarProfessores(servidorSigava);
        professor = selecionarProfessor(servidorSigava);
        servidorSigava.cadastrarProfessorDisciplina(disciplina.getNome(),professor);
    }

    private static Aluno selecionarAluno(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        String cpf;
        Aluno aluno = null;

        System.out.println("\nDigite o CPF do aluno selecionado: ");
        cpf = in.nextLine();

        if (servidorSigava.buscarAluno(cpf) == null){
            System.out.println("Aluno não encontrado!");
        }
        else {
            aluno = servidorSigava.buscarAluno(cpf);
        }
        return aluno;
    }

    private static void listarCronograma (Aluno aluno, IServidorSigava servidorSigava){
        if (aluno == null){
            System.out.println("O aluno não existe!");
        }
        else {
            for (int i = 0; i < aluno.getCronogramas().size(); i++) {
                System.out.println(aluno.getCronogramas().get(i).toString());
            }
        }
    }

    private static void criarCronograma(Aluno aluno, IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        String nomeCrono, nomeDisc, data;
        String dataR[];
        int numDis, codigoTarefa;
        Cronograma cronograma;
        LocalDate dataTermino;
        boolean ver;

        if (aluno == null){
            System.out.println("O aluno não existe!");
        }
        else if(aluno.getDisciplinas().size() < 1){
            System.out.println("O aluno não tem disciplinas cadastradas");
        }
        else{
            for (int i = 0; i < aluno.getDisciplinas().size(); i++){
                System.out.println((i+1)+ "\t - " + aluno.getDisciplinas().get(i).getNome());
            }

            System.out.println("Digite o número da disciplina: ");
            numDis = in.nextInt();

            Disciplina disciplina = aluno.getDisciplinas().get(numDis-1);
            nomeDisc = disciplina.getNome();

            System.out.println(disciplina.ListarTarefas());

            System.out.println("Digite o código da tarefa: ");
            codigoTarefa = in.nextInt();

            in.nextLine();
            System.out.println("Data de Término (dd/MM/aaaa): ");
            data = in.nextLine();
            dataR = data.split("/");
            dataTermino = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);

            System.out.println("Digite o nome do cronograma: ");
            nomeCrono = in.nextLine();
            cronograma = new Cronograma(nomeCrono);
            aluno.adicionarCronograma(cronograma);
            ver = servidorSigava.adicionarMarcacao(nomeDisc, nomeCrono, aluno, codigoTarefa, dataTermino);

            if (ver){
                System.out.println("Tarefa adicionada ao cronograma " + nomeCrono);
                listarCronograma(aluno, servidorSigava);
            }
            else{
                System.out.println("Data de término selecionada maior que o limite!");
            }
        }
    }

    private static void cadastroProfessor(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Professor professor;
        String nome, email, senha, cpf, data;
        String dataR[];
        char sexo;
        LocalDate dataNascimento;

        System.out.println("Nome: ");
        nome = in.nextLine();

        System.out.println("Sexo (m ou f): ");
        sexo = in.next().charAt(0);
        in.nextLine();

        do {
            System.out.println("Data de Nascimento (dd/MM/aaaa): ");
            data = in.nextLine();
            dataR = data.split("/");
            dataNascimento = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);
            if (dataNascimento.isAfter(LocalDate.now())){
                System.out.println("Data de nascimento inválida!\nDigite uma data anterior a hoje!");
            }
        }while (dataNascimento.isAfter(LocalDate.now()));

        do {
            System.out.println("CPF: ");
            cpf = in.nextLine();
            if (servidorSigava.existeProfessor(servidorSigava.buscarProfessor(cpf))
                    || servidorSigava.existeAluno(servidorSigava.buscarAluno(cpf))){
                System.out.println("Pessoa já cadastrado!\n");
            }
        }while (servidorSigava.existeProfessor(servidorSigava.buscarProfessor(cpf))
                || servidorSigava.existeAluno(servidorSigava.buscarAluno(cpf)));


        System.out.println("E-mail: ");
        email = in.nextLine();

        System.out.println("Senha: ");
        senha = in.nextLine();

        professor = new Professor(nome, email, sexo, dataNascimento, senha, cpf);
        servidorSigava.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado");
    }

    private static void descadastrarProfessor(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Professor professor;
        String cpf;
        boolean ver;
        System.out.println("CPF do professor: ");
        cpf = in.nextLine();
        professor = servidorSigava.buscarProfessor(cpf);

        if (professor == null){
            System.out.println("Professor não cadastrado!");
        }
        else {
            ver = servidorSigava.descadastrarProfessor(professor);
            if (ver){
                System.out.println("Descadastrado com sucesso!");
            }else System.out.println("Não foi possível descadastrar!");
        }
    }

    private static void cadastrarAluno(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Aluno aluno;
        String nome, email, senha, cpf, data;
        char sexo;
        LocalDate dataNascimento;
        String dataA[];

        System.out.println("Nome: ");
        nome = in.nextLine();


        System.out.println("Sexo (m ou f): ");
        sexo = in.next().charAt(0);
        in.nextLine();

        do {
            System.out.println("Data de Nascimento (dd/MM/aaaa): ");
            data = in.nextLine();
            dataA = data.split("/");
            dataNascimento = LocalDate.parse(dataA[2] + "-" + dataA[1] + "-" + dataA[0]);
            if (dataNascimento.isAfter(LocalDate.now())){
                System.out.println("Data de nascimento inválida!!\nDigite uma data anterior a hoje!");
            }
        }while (dataNascimento.isAfter(LocalDate.now()));

        do {
            System.out.println("CPF: ");
            cpf = in.nextLine();
            if (servidorSigava.existeAluno(servidorSigava.buscarAluno(cpf))
                    || servidorSigava.existeProfessor(servidorSigava.buscarProfessor(cpf))){
                System.out.println("Pessoa já cadastrado!\n");
            }
        }while (servidorSigava.existeAluno(servidorSigava.buscarAluno(cpf))
                || servidorSigava.existeProfessor(servidorSigava.buscarProfessor(cpf)));

        System.out.println("E-mail: ");
        email = in.nextLine();

        System.out.println("Senha: ");
        senha = in.nextLine();


        aluno = new Aluno(nome, email, sexo, dataNascimento, senha, cpf);
        servidorSigava.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado");
    }

    private static void descadastrarAluno(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Aluno aluno;
        String cpf;
        boolean analisar;

        System.out.println("CPF do aluno: ");
        cpf = in.nextLine();
        aluno = servidorSigava.buscarAluno(cpf);
        if(aluno == null){
            System.out.println("Aluno não cadastrado");
        }
        else{
            analisar = servidorSigava.descadastrarAluno(aluno);
            if(analisar){
                System.out.println("Descadastrado com sucesso!");
            }else System.out.println("Não foi possível descadastrar!");
        }
    }

    private static void cadastrarTarefa(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Tarefa tarefa;
        Disciplina disciplina;
        String descricao, dataI[], data, data1, dataT[];
        LocalDate dataInicio, dataTermino;
        int codigoTarefa;

        System.out.println("Descricao da tarefa: ");
        descricao = in.nextLine();

        System.out.println("Codigo da tarefa: ");
        codigoTarefa = in.nextInt();


        listarDisciplinas(servidorSigava);
        do {
            disciplina = selecionarDisciplina(servidorSigava);

            if (disciplina == null){
                System.out.println("Disciplina não encontrada");
            }
        }while (disciplina == null);

        do {
            in.nextLine();
            System.out.println("Data de inicio: ");
            data = in.nextLine();
            dataI = data.split("/");
            dataInicio = LocalDate.parse(dataI[2] + "-" + dataI[1] + "-" + dataI[0]);
            if (dataInicio.isBefore(LocalDate.now())){
                System.out.println("Data de início anterior a hoje!!");
            }
        }while (dataInicio.isBefore(LocalDate.now()) && dataInicio.isBefore(disciplina.gerarDataFim()) &&
                dataInicio.isBefore(disciplina.getDataInicio()));

        do {
            System.out.println("Data de termino: ");
            data1 = in.nextLine();
            dataT = data1.split("/");
            dataTermino = LocalDate.parse(dataT[2] + "-" + dataT[1] + "-" + dataT[0]);
            if (dataTermino.isAfter(disciplina.gerarDataFim())){
                System.out.println("A data fornecida é após o término da disciplina!");
            }
            if (dataTermino.isBefore(dataInicio)){
                System.out.println("A data fornecida é anterior a data início da tarefa!");
            }
            if (dataTermino.isBefore(disciplina.getDataInicio())){
                System.out.println("A data fornecida é antes do início da disciplina!");
            }
        }while (dataTermino.isAfter(disciplina.gerarDataFim()) && dataTermino.isBefore(dataInicio) &&
                dataTermino.isBefore(disciplina.getDataInicio()));

        tarefa =  new Tarefa(descricao,dataInicio,dataTermino,codigoTarefa, disciplina);
        servidorSigava.cadastrarTarefa(tarefa);
        servidorSigava.cadastrarTarefaDisciplina(disciplina.getNome(), tarefa);
        System.out.println("Tarefa cadastrada");
    }

    private static void descadastrarTarefa(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Tarefa tarefa;
        int codigoTarefa;
        boolean analisar;

        System.out.println("Codigo da tarefa: ");
        codigoTarefa = in.nextInt();
        tarefa = servidorSigava.buscarTarefa(codigoTarefa);
        if (tarefa != null){
            analisar = servidorSigava.descadastrarTarefa(tarefa);
            System.out.println("Tarefa descadastrada!");
        }
        else {
            System.out.println("Tarefa nao localizada, favor inserir um codigo valido!");
        }
    }

    private static void cadastrarDisciplina(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Disciplina disciplina = null;
        Professor professor;
        String nome, data;
        String dataR[];
        LocalDate dataInicio;
        DayOfWeek diaAula;
        int duracaoAula, cargaHoraria;

        System.out.println("Nome: ");
        nome = in.nextLine();

       do {
           System.out.println("Data de Início (dd/MM/aaaa): ");
           data = in.nextLine();
           dataR = data.split("/");
           dataInicio = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);
           if (dataInicio.isBefore(LocalDate.now())){
               System.out.println("Data de início anterior a hoje!!");
           }
       }while (dataInicio.isBefore(LocalDate.now()));

        diaAula = dataInicio.getDayOfWeek();

        System.out.println("Duração da aula: ");
        duracaoAula = in.nextInt();

        System.out.println("Carga Horária da disciplina: ");
        cargaHoraria = in.nextInt();

        disciplina = new Disciplina(nome, dataInicio, diaAula, duracaoAula, cargaHoraria);
        servidorSigava.cadastrarDisciplina(disciplina);

        System.out.println("Acossie a disciplina a um professor!");
        listarProfessores(servidorSigava);
        professor = selecionarProfessor(servidorSigava);
        servidorSigava.cadastrarProfessorDisciplina(disciplina.getNome(), professor);
        System.out.println("Disciplina cadastrada");
    }

    private static void descadastrarDisciplina(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        Disciplina disciplina;
        String nome;
        boolean ver;

        System.out.println("Nome da disciplina: ");
        nome = in.nextLine();
        disciplina = servidorSigava.buscarDisciplina(nome);

        if (disciplina == null){
            System.out.println("Disciplina não cadastrada!");
        }
        else {
            ver = servidorSigava.descadastrarDisciplina(disciplina);
            if (ver){
                System.out.println("Descadastrada com sucesso!");
            }else System.out.println("Não foi possível descadastrar!");
        }
    }

    private static void procurarAluno (IServidorSigava servidor){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o cpf do Aluno: ");
        String cpf = scan.next();
        if (servidor.buscarAluno(cpf) != null);
        System.out.println(servidor.buscarAluno(cpf).toString());
    }

    private static void procurarProfessor (IServidorSigava servidor){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o cpf do Professor: ");
        String cpf = scan.next();
        if (servidor.buscarProfessor(cpf) != null);
        System.out.println(servidor.buscarProfessor(cpf).toString());
    }

    private static void procurarDisciplina (IServidorSigava servidor){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome da disciplina: ");
        String nome = scan.next();
        if (servidor.buscarDisciplina(nome) != null);
        System.out.println(servidor.buscarDisciplina(nome).toString());
    }

    private static void procurarTarefa (IServidorSigava servidor){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o código da tarefa: ");
        int codigo = scan.nextInt();
        if (servidor.buscarTarefa(codigo)!= null);
        System.out.println(servidor.buscarTarefa(codigo).toString());
    }

    private static void listarAlunos (IServidorSigava servidor){
        if (servidor.listarAlunos() == null || servidor.listarAlunos().size()<1){
            System.out.println("Não há alunos no repositório.\n");
        }
        else {
            for (int i = 0; i < servidor.listarAlunos().size() ; i++) {
                System.out.println(servidor.listarAlunos().get(i).toString());
            }
        }
    }

    private static void listarDisciplinas (IServidorSigava servidor){
        if (servidor.listarDisciplinas() == null || servidor.listarDisciplinas().size()<1){
            System.out.println("Não há disciplinas no repositório.\n");
        }
        else {
            for (int i = 0; i < servidor.listarDisciplinas().size(); i++) {
                System.out.println(servidor.listarDisciplinas().get(i).toString());
            }
        }
    }

    private static void listarTarefas (IServidorSigava servidor){
        if (servidor.listarTarefas() == null || servidor.listarTarefas().size()<1){
            System.out.println("Não há tarefas no repositório.\n");
        }
        else {
            for (int i = 0; i < servidor.listarTarefas().size(); i++) {
                System.out.println(servidor.listarTarefas().get(i).toString());
            }
        }
    }

    private static void listarProfessores (IServidorSigava servidor){
        if (servidor.listarProfessores() == null || servidor.listarProfessores().size()<1){
            System.out.println("Não há professores no repositório.\n");
        }
        else {
            for (int i = 0; i < servidor.listarProfessores().size() ; i++) {
                System.out.println(servidor.listarProfessores().get(i).toString());
            }
        }
    }

    private static void atualizarAluno (IServidorSigava servidorSigava) {
        listarAlunos(servidorSigava);
        Aluno aluno = selecionarAluno(servidorSigava);
        if (aluno != null) {
            Scanner in = new Scanner(System.in);
            int verificador;

            do {
                System.out.println("ESCOLHA UMA OPÇÃO: ");
                System.out.println("1\t - Atualizar Nome.");
                System.out.println("2\t - Atualizar Sexo.");
                System.out.println("3\t - Atualizar Data de Nascimento.");
                System.out.println("4\t - Atualizar Email.");
                System.out.println("5\t - SAIR.");
                verificador = in.nextInt();
                in.nextLine();
                switch (verificador) {
                    case 1:
                        System.out.println("Digite o novo nome: ");
                        String nome = in.nextLine();
                        aluno.setNome(nome);
                        break;
                    case 2:
                        char sexo;
                        System.out.println("Atualizar sexo (m ou f): ");
                        sexo = in.next().charAt(0);
                        in.nextLine();
                        aluno.setSexo(sexo);
                        break;
                    case 3:
                        LocalDate dataNascimento;
                        do {
                            String data;
                            System.out.println("Data de Nascimento (dd/MM/aaaa): ");
                            data = in.nextLine();
                            String dataR[] = data.split("/");
                            dataNascimento = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);
                            if (dataNascimento.isAfter(LocalDate.now())) {
                                System.out.println("Data de nascimento inválida!\nDigite uma data anterior a hoje!");
                            }
                        } while (dataNascimento.isAfter(LocalDate.now()));
                        aluno.setDataNascimento(dataNascimento);
                        break;
                    case 4:
                        String email;
                        System.out.println("Atualizar email: ");
                        email = in.nextLine();
                        aluno.setEmail(email);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Favor digitar um valor dentro do Menu.");
                        break;
                }
            } while (verificador != 5);
        }
    }



    private static Tarefa selecionarTarefa(IServidorSigava servidorSigava){
        Scanner in = new Scanner(System.in);
        int codigo;
        Tarefa tarefa = null;

        System.out.println("\nDigite o ID da tarefa: ");
        codigo = in.nextInt();
        in.nextLine();

        if (servidorSigava.buscarTarefa(codigo) == null){
            System.out.println("Tarefa não encontrada!");
        }
        else {
            tarefa = servidorSigava.buscarTarefa(codigo);
        }
        return tarefa;
    }


    private static void atualizarTarefa (IServidorSigava servidorSigava){
        listarDisciplinas(servidorSigava);
        Disciplina disciplina = selecionarDisciplina(servidorSigava);
        disciplina.ListarTarefas();
        Tarefa tarefa = selecionarTarefa(servidorSigava);
        if (tarefa != null){
            Scanner in = new Scanner (System.in);
            int verificador;
            do {
                System.out.println("ESCOLHA UMA OPÇÃO: ");
                System.out.println("1\t - Atualizar Descrição.");
                System.out.println("2\t - Atualizar dataInicio.");
                System.out.println("3\t - Atualizar DataTermino.");
                System.out.println("4\t - Atualizar Codigo da Tarefa.");
                System.out.println("5\t - SAIR.");
                verificador = in.nextInt();
                in.nextLine();
                switch (verificador){
                    case 1:
                        System.out.println("Digite a nova descrição: ");
                        String nome = in.nextLine();
                        tarefa.setDescricao(nome);
                        break;
                    case 2:
                        String dataI[], data;
                        LocalDate dataInicio;
                        do{
                            System.out.println("Nova data Inicio (dd/MM/aaaa): ");
                            data = in.nextLine();
                            dataI = data.split("/");
                            dataInicio = LocalDate.parse(dataI[2] + "-" + dataI[1] + "-" + dataI[0]);
                            if (dataInicio.isBefore(LocalDate.now())){
                                System.out.println("Data de início anterior a hoje!!");
                            }
                        }while (dataInicio.isBefore(LocalDate.now()) && dataInicio.isBefore(disciplina.gerarDataFim()) &&
                                dataInicio.isBefore(disciplina.getDataInicio()));
                        tarefa.setDataTermino(dataInicio);
                        break;
                    case 3:
                        String dataT[], data1;
                        LocalDate dataTermino;
                        do {
                            System.out.println("Nova Data termino: ");
                            data1 = in.nextLine();
                            dataT = data1.split("/");
                            dataTermino = LocalDate.parse(dataT[2] + "-" + dataT[1] + "-" + dataT[0]);
                            if (dataTermino.isAfter(disciplina.gerarDataFim())){
                                System.out.println("A data fornecida é após o término da disciplina!");
                            }
                            if (dataTermino.isBefore(tarefa.getDataInicio())){
                                System.out.println("A data fornecida é anterior a data início da tarefa!");
                            }
                            if (dataTermino.isBefore(disciplina.getDataInicio())){
                                System.out.println("A data fornecida é antes do início da disciplina!");
                            }
                        }while (dataTermino.isAfter(disciplina.gerarDataFim()) && dataTermino.isBefore(tarefa.getDataInicio()) &&
                                dataTermino.isBefore(disciplina.getDataInicio()));
                        tarefa.setDataTermino(dataTermino);
                        break;
                    case 4:
                        int codigo;
                        do{
                            System.out.println("Digite o novo código da Tarefa: ");
                            codigo = in.nextInt();
                            in.nextLine();
                            if (codigo <= 0){
                                System.out.printf("Digite um valor positivo.");
                            }
                        }while (codigo <=0);
                        tarefa.setCodigoTarefa(codigo);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Favor digitar um valor dentro do Menu.");
                        break;
                }
            }while(verificador != 5);
        }
    }


    private static void atualizarProfessor (IServidorSigava servidorSigava){
        listarProfessores(servidorSigava);
        Professor professor = selecionarProfessor(servidorSigava);
        if(professor != null){
            Scanner in = new Scanner (System.in);
            int verificador;

            do {
                System.out.println("ESCOLHA UMA OPÇÃO: ");
                System.out.println("1\t - Atualizar Nome.");
                System.out.println("2\t - Atualizar Sexo.");
                System.out.println("3\t - Atualizar Data de Nascimento.");
                System.out.println("4\t - Atualizar Email.");
                System.out.println("5\t - SAIR.");
                verificador = in.nextInt();
                in.nextLine();
                switch (verificador){
                    case 1:
                        System.out.println("Digite o novo nome: ");
                        String nome = in.nextLine();
                        professor.setNome(nome);
                        break;
                    case 2:
                        char sexo;
                        System.out.println("Atualizar sexo (m ou f): ");
                        sexo = in.next().charAt(0);
                        in.nextLine();
                        professor.setSexo(sexo);
                        break;
                    case 3:
                        LocalDate dataNascimento;
                        do {
                            String data;
                            System.out.println("Data de Nascimento (dd/MM/aaaa): ");
                            data = in.nextLine();
                            String dataR[] = data.split("/");
                            dataNascimento = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);
                            if (dataNascimento.isAfter(LocalDate.now())){
                                System.out.println("Data de nascimento inválida!!\nDigite uma data anterior a hoje!");
                            }
                        }while (dataNascimento.isAfter(LocalDate.now()));
                        professor.setDataNascimento(dataNascimento);
                        break;
                    case 4:
                        String email;
                        System.out.println("Atualizar email: ");
                        email = in.nextLine();
                        professor.setEmail(email);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Favor digitar um valor dentro do Menu.");
                        break;
                }
            }while(verificador != 5);
        }
    }

    public static void atualizarDisciplina (IServidorSigava servidorSigava){
        listarDisciplinas(servidorSigava);
        Disciplina disciplina = selecionarDisciplina(servidorSigava);
        if(disciplina != null) {
            Scanner in = new Scanner(System.in);
            int verificador;

            do {
                System.out.println("ESCOLHA UMA OPÇÃO: ");
                System.out.println("1\t - Atualizar Nome.");
                System.out.println("2\t - Atualizar Professor.");
                System.out.println("3\t - Atualizar Data de inicio.");
                System.out.println("4\t - Atualizar Duracao.");
                System.out.println("5\t - SAIR.");
                verificador = in.nextInt();
                in.nextLine();
                switch (verificador){
                    case 1:
                        System.out.println("Digite o novo nome: ");
                        String nome = in.nextLine();
                        disciplina.setNome(nome);
                        break;
                    case 2:
                        listarProfessores(servidorSigava);
                        Professor professor = selecionarProfessor(servidorSigava);
                        if(professor != null){
                            disciplina.setProfessor(professor);
                        }
                        else{
                            System.out.println("Professor nao atualizado!");
                        }
                        break;
                    case 3:
                        LocalDate dataInicio;
                        String data, dataR [];
                        do {
                            System.out.println("Data de Início (dd/MM/aaaa): ");
                            data = in.nextLine();
                            dataR = data.split("/");
                            dataInicio = LocalDate.parse(dataR[2] + "-" + dataR[1] + "-" + dataR[0]);
                            if (dataInicio.isBefore(LocalDate.now())){
                                System.out.println("Data de início anterior à hoje!!");
                            }
                        }while (dataInicio.isBefore(LocalDate.now()));
                        disciplina.setDataInicio(dataInicio);
                        break;
                    case 4:
                        int duracao;
                        do{
                            System.out.println("Digite a duração: ");
                            duracao = in.nextInt();
                            in.nextLine();
                            if (duracao <= 0){
                                System.out.printf("Digite um valor positivo!!");
                            }
                        }while (duracao <=0);
                        disciplina.setDuracaoAula(duracao);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Favor digitar um valor dentro do Menu.");
                        break;
                }

            }while(verificador != 5);
        }

    }

    public static void Sistema(IServidorSigava servidorSigava){
        boolean loop = true;
        do {
            MenuAcesso();
            Scanner in = new Scanner(System.in);
            int controleMenu = in.nextInt();
            switch (controleMenu){
                case 1:
                    cadastrarAluno(servidorSigava);
                    break;
                case 2:
                    cadastroProfessor(servidorSigava);
                    break;
                case 3:
                    cadastrarDisciplina(servidorSigava);
                    break;
                case 4:
                    cadastrarTarefa(servidorSigava);
                    break;
                case 5:
                    descadastrarAluno(servidorSigava);
                    break;
                case 6:
                    descadastrarProfessor(servidorSigava);
                    break;
                case 7:
                    descadastrarDisciplina(servidorSigava);
                    break;
                case 8:
                    descadastrarTarefa(servidorSigava);
                    break;
                case 9:
                    procurarAluno(servidorSigava);
                    break;
                case 10:
                    procurarProfessor(servidorSigava);
                    break;
                case 11:
                    procurarDisciplina(servidorSigava);
                    break;
                case 12:
                    procurarTarefa(servidorSigava);
                    break;
                case 13:
                    listarAlunos(servidorSigava);
                    break;
                case 14:
                    listarProfessores(servidorSigava);
                    break;
                case 15:
                    listarDisciplinas(servidorSigava);
                    break;
                case 16:
                    listarTarefas(servidorSigava);
                    break;
                case 17:
                    listarAlunos(servidorSigava);
                    criarCronograma(selecionarAluno(servidorSigava), servidorSigava);
                    break;
                case 18:
                    associarAlunoDisc(servidorSigava);
                    break;
                case 19:
                    associarDiscProf(servidorSigava);
                    break;
                case 20:
                    atualizarAluno(servidorSigava);
                    break;
                case 21:
                    atualizarProfessor(servidorSigava);
                    break;
                case 22:
                    atualizarDisciplina(servidorSigava);
                    break;
                case 23:
                    atualizarTarefa(servidorSigava);
                    break;
                case 24:
                    loop = false;
                    break;
                default:
                    System.out.printf("Fora do range do menu.\n");
                    break;

            }


        }while (loop);
    }
}
