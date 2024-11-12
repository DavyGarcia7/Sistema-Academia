package Registrar_nova_Pessoa;

import Agendamento.GerenciadorMensalidades;
import Pagamento.ProcessadorPagamento;
import java.util.List;
import java.util.Scanner;

public class AreaLogin {

    private final GestaoCadastroFacade gestaoCadastroFacade;
    private final Catraca catraca;

    public AreaLogin(ProcessadorPagamento processadorPagamento, List<SCadastroAluno> alunos, List<SCadastrofuncionario> funcionarios, GerenciadorMensalidades gerenciadorMensalidades) {
        this.gestaoCadastroFacade = new GestaoCadastroFacade(processadorPagamento);
        this.catraca = new Catraca(alunos, funcionarios, gerenciadorMensalidades); // Inicializa a catraca com os alunos e funcionários registrados
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Sistema de Cadastro e Controle de Acesso ===");
            System.out.println("1. Registrar novo Aluno");
            System.out.println("2. Registrar novo Funcionario");
            System.out.println("3. Deletar aluno ou Funcionario");
            System.out.println("4. Editar Aluno");
            System.out.println("5. Editar Funcionario");
            System.out.println("6. Registrar Entrada na Academia");
            System.out.println("7. Registrar Saída da Academia");
            System.out.println("8. Verificar Ocupação Atual da Academia");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> gestaoCadastroFacade.cadastrarAluno();
                case 2 -> gestaoCadastroFacade.cadastrarFuncionario();
                case 3 -> deletarPessoa(scanner);
                case 4 -> editarAluno(scanner);
                case 5 -> editarFuncionario(scanner);
                case 6 -> registrarEntrada(scanner); // Função de entrada na academia
                case 7 -> registrarSaida(scanner);    // Função de saída da academia
                case 8 -> catraca.verificarOcupacaoAtual(); // Exibir a ocupação atual
                case 9 -> {
                    System.out.println("Encerrando sistema.");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void deletarPessoa(Scanner scanner) {
        System.out.println("=== Deletar aluno ou Funcionario ===");
        System.out.println("1. Remover Aluno");
        System.out.println("2. Remover Funcionario");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Digite o ID do aluno a ser deletado: ");
                int idA = scanner.nextInt();
                gestaoCadastroFacade.deletarAluno(idA);
            }
            case 2 -> {
                System.out.print("Digite o ID do funcionário a ser deletado: ");
                int idF = scanner.nextInt();
                gestaoCadastroFacade.deletarFuncionario(idF);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void editarAluno(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser editado: ");
        int idA = scanner.nextInt();
        scanner.nextLine();
        gestaoCadastroFacade.editarAluno(idA);
    }

    private void editarFuncionario(Scanner scanner) {
        System.out.print("Digite o ID do funcionário a ser editado: ");
        int idF = scanner.nextInt();
        scanner.nextLine();
        gestaoCadastroFacade.editarFuncionario(idF);
    }

    // Método para registrar a entrada de uma pessoa na catraca
    private void registrarEntrada(Scanner scanner) {
        System.out.print("Digite o ID da pessoa para registrar entrada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o tipo de pessoa (aluno/funcionario): ");
        String tipoPessoa = scanner.nextLine();
        catraca.registrarEntrada(id, tipoPessoa);
    }

    // Método para registrar a saída de uma pessoa na catraca
    private void registrarSaida(Scanner scanner) {
        System.out.print("Digite o ID da pessoa para registrar saída: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        catraca.registrarSaida(id);
    }

    @Override
    public String toString() {
        return "AreaLogin{" +
                "gestaoCadastroFacade=" + gestaoCadastroFacade +
                ", catraca=" + catraca +
                '}';
    }
}
