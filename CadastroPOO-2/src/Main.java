import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        String opcao;
        do {
            exibirMenu();
            opcao = lerInput();
            tratarOpcaoMenu(opcao);
        } while (!"0".equals(opcao));
        System.out.println("Programa finalizado.");
    }

    private static void exibirMenu() {
        System.out.println("=============================================");
        System.out.println("1 - Incluir pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Exibir pelo ID");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Salvar Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("=============================================");
        System.out.print("Escolha uma opção: ");
    }

    private static String lerInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Erro ao ler a entrada: " + e.getMessage());
            return "";
        }
    }

    private static void tratarOpcaoMenu(String opcao) {
        try {
            switch (opcao) {
                case "1":
                    incluirPessoa();
                    break;
                case "2":
                    alterarPessoa();
                    break;
                case "3":
                    excluirPessoa();
                    break;
                case "4":
                    obterPessoa();
                    break;
                case "5":
                    obterTodos();
                    break;
                case "6":
                    salvarDados();
                    break;
                case "7":
                    recuperarDados();
                    break;
                case "0":
                    // Finalizar Programa
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void incluirPessoa() throws IOException {
        System.out.println("Escolha o tipo de pessoa para incluir:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Digite sua opção: ");
        String tipo = lerInput();

        if ("1".equals(tipo)) {
            PessoaFisica pessoaFisica = lerDadosPessoaFisica();
            repoFisica.inserir(pessoaFisica);
            System.out.println("Pessoa Física inserida com sucesso.");
        } else if ("2".equals(tipo)) {
            PessoaJuridica pessoaJuridica = lerDadosPessoaJuridica();
            repoJuridica.inserir(pessoaJuridica);
            System.out.println("Pessoa Jurídica inserida com sucesso.");
        } else {
            System.out.println("Tipo de pessoa inválido. Tente novamente.");
        }
    }

    private static PessoaFisica lerDadosPessoaFisica() throws IOException {
        System.out.print("Digite o ID da pessoa física: ");
        int id = Integer.parseInt(lerInput());
        System.out.print("Digite o nome da pessoa física: ");
        String nome = lerInput();
        System.out.print("Digite o CPF da pessoa física: ");
        String cpf = lerInput();
        System.out.print("Digite a idade da pessoa física: ");
        int idade = Integer.parseInt(lerInput());

        return new PessoaFisica(id, nome, cpf, idade);
    }

    private static PessoaJuridica lerDadosPessoaJuridica() throws IOException {
        System.out.print("Digite o ID da pessoa jurídica: ");
        int id = Integer.parseInt(lerInput());
        System.out.print("Digite o nome da pessoa jurídica: ");
        String nome = lerInput();
        System.out.print("Digite o CNPJ da pessoa jurídica: ");
        String cnpj = lerInput();

        return new PessoaJuridica(id, nome, cnpj);
    }
    private static void alterarPessoa() throws IOException {
        System.out.println("Escolha o tipo de pessoa para alterar:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Digite sua opção: ");
        String tipo = lerInput();

        System.out.print("Digite o ID da pessoa: ");
        int id = Integer.parseInt(lerInput());

        if ("1".equals(tipo)) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.println("Dados atuais: " + pf);
                PessoaFisica novaPf = lerDadosPessoaFisica();
                novaPf.setId(id); // Mantém o mesmo ID
                repoFisica.alterar(novaPf);
                System.out.println("Pessoa Física alterada com sucesso.");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if ("2".equals(tipo)) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.println("Dados atuais: " + pj);
                PessoaJuridica novaPj = lerDadosPessoaJuridica();
                novaPj.setId(id); // Mantém o mesmo ID
                repoJuridica.alterar(novaPj);
                System.out.println("Pessoa Jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }
    private static void excluirPessoa() throws IOException {
        System.out.println("Escolha o tipo de pessoa para excluir:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Digite sua opção: ");
        String tipo = lerInput();

        System.out.print("Digite o ID da pessoa: ");
        int id = Integer.parseInt(lerInput());

        if ("1".equals(tipo)) {
            boolean excluido = repoFisica.excluir(id);
            if (excluido) {
                System.out.println("Pessoa Física excluída com sucesso.");
            } else {
                System.out.println("Pessoa Física não encontrada ou erro na exclusão.");
            }
        } else if ("2".equals(tipo)) {
            boolean excluido = repoJuridica.excluir(id);
            if (excluido) {
                System.out.println("Pessoa Jurídica excluída com sucesso.");
            } else {
                System.out.println("Pessoa Jurídica não encontrada ou erro na exclusão.");
            }
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }
    private static void obterPessoa() throws IOException {
        System.out.println("Escolha o tipo de pessoa para obter os dados:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Digite sua opção: ");
        String tipo = lerInput();

        System.out.print("Digite o ID da pessoa: ");
        int id = Integer.parseInt(lerInput());

        if ("1".equals(tipo)) {
            PessoaFisica pessoaFisica = repoFisica.obterPorId(id);
            if (pessoaFisica != null) {
                System.out.println("Dados da Pessoa Física:");
                System.out.println(pessoaFisica); // Assume que o método toString() está implementado
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if ("2".equals(tipo)) {
            PessoaJuridica pessoaJuridica = repoJuridica.obterPorId(id);
            if (pessoaJuridica != null) {
                System.out.println("Dados da Pessoa Jurídica:");
                System.out.println(pessoaJuridica); // Assume que o método toString() está implementado
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }
    private static void obterTodos() throws IOException {
        System.out.println("Escolha o tipo de pessoa para listar todos os registros:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Digite sua opção: ");
        String tipo = lerInput();

        if ("1".equals(tipo)) {
            List<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();
            System.out.println("Lista de todas as Pessoas Físicas:");
            for (PessoaFisica pf : pessoasFisicas) {
                System.out.println(pf); // Assume que PessoaFisica tem um método toString() implementado
            }
        } else if ("2".equals(tipo)) {
            List<PessoaJuridica> pessoasJuridicas = repoJuridica.obterTodos();
            System.out.println("Lista de todas as Pessoas Jurídicas:");
            for (PessoaJuridica pj : pessoasJuridicas) {
                System.out.println(pj); // Assume que PessoaJuridica tem um método toString() implementado
            }
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }
    private static void salvarDados() {
        System.out.print("Digite o prefixo para os arquivos onde os dados serão salvos: ");
        String prefixo = lerInput();

        try {
            String arquivoPessoaFisica = prefixo + ".fisica.bin";
            String arquivoPessoaJuridica = prefixo + ".juridica.bin";

            repoFisica.persistir(arquivoPessoaFisica);
            repoJuridica.persistir(arquivoPessoaJuridica);

            System.out.println("Dados salvos com sucesso nos arquivos:");
            System.out.println(arquivoPessoaFisica);
            System.out.println(arquivoPessoaJuridica);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.print("Digite o prefixo para os arquivos de onde os dados serão recuperados: ");
        String prefixo = lerInput();

        try {
            String arquivoPessoaFisica = prefixo + ".fisica.bin";
            String arquivoPessoaJuridica = prefixo + ".juridica.bin";

            repoFisica.recuperar(arquivoPessoaFisica);
            repoJuridica.recuperar(arquivoPessoaJuridica);

            System.out.println("Dados recuperados com sucesso dos arquivos:");
            System.out.println(arquivoPessoaFisica);
            System.out.println(arquivoPessoaJuridica);
        } catch (IOException e) {
            System.out.println("Erro ao ler os dados do arquivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: uma das classes não foi encontrada.");
        }
    }

}
