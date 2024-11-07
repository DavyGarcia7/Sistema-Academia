package Registrar_nova_Pessoa;

import Pagamento.ProcessadorPagamento;
import java.util.Scanner;

public class AreaLogin {

    private final GestaoCadastroFacade gestaoCadastroFacade;

    public AreaLogin(ProcessadorPagamento processadorPagamento) {
        this.gestaoCadastroFacade = new GestaoCadastroFacade(processadorPagamento);
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Sistema de Cadastro ===");
            System.out.println("1. Registrar novo Aluno");
            System.out.println("2. Registrar novo Funcionario");
            System.out.println("3. Deletar aluno ou Funcionario");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> gestaoCadastroFacade.cadastrarAluno(); // Cadastro de aluno
                case 2 -> gestaoCadastroFacade.cadastrarFuncionario(); // Cadastro de funcionário
                case 3 -> deletarPessoa(scanner); // Excluir aluno ou funcionário
                case 4 -> {
                    System.out.println("Saindo do sistema...");
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
}
