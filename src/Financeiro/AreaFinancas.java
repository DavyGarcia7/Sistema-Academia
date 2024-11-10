    package Financeiro;

    import Pagamento.ProcessadorPagamento;
    import java.util.List;
    import java.util.Scanner;
    import Financeiro.FinanceiroFacade;

    public class AreaFinancas {
        private FinanceiroFacade financeiroFacade;
        private Scanner scanner;

        public AreaFinancas(ProcessadorPagamento processadorPagamento) {
            this.financeiroFacade = new FinanceiroFacade(processadorPagamento);
            this.scanner = new Scanner(System.in);
        }

        // Método para iniciar o sistema financeiro
        public void iniciar() {
            System.out.println("=== Login do Administrador ===");
            if (!realizarLogin()) {
                System.out.println("Acesso negado. Credenciais incorretas.");
                return;
            }

            boolean executando = true;
            while (executando) {
                System.out.println("=== Sistema de Gerenciamento Financeiro ===");
                System.out.println("1. Relatório Financeiro dos Alunos");
                System.out.println("2. Adicionar Pagamento de Funcionário");
                System.out.println("3. Consultar Pagamentos de Funcionário");
                System.out.println("4. Relatório Financeiro dos Funcionários");
                System.out.println("5. Exibir Balanço da Academia");
                System.out.println("6. Listar Administradores");
                System.out.println("7. Registrar Novo Administrador");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1 -> financeiroFacade.exibirRelatorioFinanceiroAlunos();
                    case 2 -> adicionarPagamentoFuncionario();
                    case 3 -> consultarPagamentosFuncionario();
                    case 4 -> financeiroFacade.exibirRelatorioFinanceiroFuncionarios();
                    case 5 -> financeiroFacade.exibirBalancoAcademia();
                    case 6 -> financeiroFacade.listarAdministradores();
                    case 7 -> registrarNovoAdministrador();
                    case 8 -> {
                        System.out.println("Retornando ao menu principal...");
                        executando = false;
                    }
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            }
        }

        // Método para realizar login do administrador
        private boolean realizarLogin() {
            System.out.print("Digite o ID do administrador: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            return financeiroFacade.autenticarAdministrador(id, senha);
        }

        // Método para registrar um novo administrador
        private void registrarNovoAdministrador() {
            System.out.print("Digite o ID para o novo administrador: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Digite o nome do novo administrador: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a senha para o novo administrador: ");
            String senha = scanner.nextLine();

            financeiroFacade.registrarAdministrador(id, nome, senha);
        }

        // Método para adicionar pagamento de um funcionário
        private void adicionarPagamentoFuncionario() {
            System.out.print("Digite o ID do funcionário: ");
            int funcionarioId = scanner.nextInt();
            System.out.print("Digite o valor do pagamento: ");
            double valor = scanner.nextDouble();

            if (financeiroFacade.adicionarPagamentoFuncionario(funcionarioId, valor)) {
                System.out.println("Pagamento registrado com sucesso.");
            } else {
                System.out.println("Funcionário com ID " + funcionarioId + " não encontrado.");
            }
        }

        // Método para consultar os pagamentos de um funcionário específico
        private void consultarPagamentosFuncionario() {
            System.out.print("Digite o ID do funcionário: ");
            int funcionarioId = scanner.nextInt();
            List<PagamentoFuncionario> pagamentos = financeiroFacade.consultarPagamentosFuncionario(funcionarioId);

            if (pagamentos.isEmpty()) {
                System.out.println("Nenhum pagamento encontrado para o funcionário com ID: " + funcionarioId);
            } else {
                System.out.println("Pagamentos do funcionário com ID " + funcionarioId + ":");
                for (PagamentoFuncionario pagamento : pagamentos) {
                    System.out.println("Data: " + pagamento.getDataPagamento() + " | Valor: R$" + pagamento.getValorPago());
                }
            }
        }
    }
