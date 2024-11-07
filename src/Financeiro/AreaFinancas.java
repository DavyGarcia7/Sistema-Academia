package Financeiro;

import Pagamento.ProcessadorPagamento;
import java.util.List;
import java.util.Scanner;

public class AreaFinancas {
    private GerenciadorMensalidades gerenciadorMensalidades;
    private GerenciadorPagamentosFuncionarios gerenciadorPagamentosFuncionarios;
    private Scanner scanner;

    public AreaFinancas(ProcessadorPagamento processadorPagamento) {
        this.gerenciadorMensalidades = new GerenciadorMensalidades(processadorPagamento);
        this.gerenciadorPagamentosFuncionarios = new GerenciadorPagamentosFuncionarios();
        this.scanner = new Scanner(System.in);
    }

    // Método para iniciar o sistema financeiro
    public void iniciar() {
        boolean executando = true;

        while (executando) {
            System.out.println("=== Sistema de Gerenciamento Financeiro ===");
            System.out.println("1. Adicionar Mensalidade de Aluno");
            System.out.println("2. Consultar Mensalidades de Aluno");
            System.out.println("3. Relatório Financeiro dos Alunos");
            System.out.println("4. Adicionar Pagamento de Funcionário");
            System.out.println("5. Consultar Pagamentos de Funcionário");
            System.out.println("6. Relatório Financeiro dos Funcionários");
            System.out.println("7. Exibir Balanço da Academia");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> adicionarMensalidadeAluno();
                case 2 -> consultarMensalidadesAluno();
                case 3 -> exibirRelatorioFinanceiroAlunos();
                case 4 -> adicionarPagamentoFuncionario();
                case 5 -> consultarPagamentosFuncionario();
                case 6 -> exibirRelatorioFinanceiroFuncionarios();
                case 7 -> exibirBalancoAcademia();
                case 8 -> {
                    System.out.println("Retornando ao menu anterior...");
                    return; // Sai do método iniciar() e volta ao menu anterior
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // Método para adicionar mensalidade de um aluno
    private void adicionarMensalidadeAluno() {
        System.out.print("Digite o ID do aluno: ");
        int alunoId = scanner.nextInt();
        if (gerenciadorMensalidades.verificarAlunoRegistrado(alunoId)) {
            System.out.print("Digite o valor da mensalidade: ");
            double valor = scanner.nextDouble();
            gerenciadorMensalidades.adicionarMensalidade(alunoId, valor);
            System.out.println("Mensalidade adicionada com sucesso.");
        } else {
            System.out.println("Aluno com ID " + alunoId + " não encontrado em alunos.json.");
        }
    }

    // Método para consultar mensalidades de um aluno específico
    private void consultarMensalidadesAluno() {
        System.out.print("Digite o ID do aluno: ");
        int alunoId = scanner.nextInt();
        if (gerenciadorMensalidades.verificarAlunoRegistrado(alunoId)) {
            List<MensalidadeAluno> mensalidades = gerenciadorMensalidades.listarMensalidadesPorAluno(alunoId);
            if (mensalidades.isEmpty()) {
                System.out.println("Nenhuma mensalidade encontrada para o aluno com ID: " + alunoId);
            } else {
                System.out.println("Mensalidades do aluno com ID " + alunoId + ":");
                for (MensalidadeAluno mensalidade : mensalidades) {
                    System.out.println("Data: " + mensalidade.getDataPagamento() + " | Valor: R$" + mensalidade.getValorPago());
                }
            }
        } else {
            System.out.println("Aluno com ID " + alunoId + " não encontrado em alunos.json.");
        }
    }

    // Método para exibir o relatório financeiro dos alunos
    private void exibirRelatorioFinanceiroAlunos() {
        gerenciadorMensalidades.relatorioFinanceiroAlunos();
    }

    // Método para adicionar pagamento de um funcionário
    private void adicionarPagamentoFuncionario() {
        System.out.print("Digite o ID do funcionário: ");
        int funcionarioId = scanner.nextInt();
        if (gerenciadorPagamentosFuncionarios.verificarFuncionarioRegistrado(funcionarioId)) {
            System.out.print("Digite o valor do pagamento: ");
            double valor = scanner.nextDouble();
            gerenciadorPagamentosFuncionarios.adicionarPagamento(funcionarioId, valor);
            System.out.println("Pagamento registrado com sucesso.");
        } else {
            System.out.println("Funcionário com ID " + funcionarioId + " não encontrado em funcionarios.json.");
        }
    }

    // Método para consultar os pagamentos de um funcionário específico
    private void consultarPagamentosFuncionario() {
        System.out.print("Digite o ID do funcionário: ");
        int funcionarioId = scanner.nextInt();
        if (gerenciadorPagamentosFuncionarios.verificarFuncionarioRegistrado(funcionarioId)) {
            List<PagamentoFuncionario> pagamentos = gerenciadorPagamentosFuncionarios.listarPagamentosPorFuncionario(funcionarioId);
            if (pagamentos.isEmpty()) {
                System.out.println("Nenhum pagamento encontrado para o funcionário com ID: " + funcionarioId);
            } else {
                System.out.println("Pagamentos do funcionário com ID " + funcionarioId + ":");
                for (PagamentoFuncionario pagamento : pagamentos) {
                    System.out.println("Data: " + pagamento.getDataPagamento() + " | Valor: R$" + pagamento.getValorPago());
                }
            }
        } else {
            System.out.println("Funcionário com ID " + funcionarioId + " não encontrado em funcionarios.json.");
        }
    }

    // Método para exibir o relatório financeiro dos funcionários
    private void exibirRelatorioFinanceiroFuncionarios() {
        gerenciadorPagamentosFuncionarios.relatorioFinanceiroFuncionarios();
    }

    // Método para exibir o balanço financeiro da academia
    private void exibirBalancoAcademia() {
        double totalRecebido = gerenciadorMensalidades.calcularTotalMensalidades();
        double totalPago = gerenciadorPagamentosFuncionarios.calcularTotalPagamentos();
        double balanco = totalRecebido - totalPago;

        System.out.println("=== Balanço Financeiro da Academia ===");
        System.out.println("Total Recebido em Mensalidades: R$" + totalRecebido);
        System.out.println("Total Pago aos Funcionários: R$" + totalPago);
        System.out.println("Balanço da Academia: R$" + balanco);
    }
}
