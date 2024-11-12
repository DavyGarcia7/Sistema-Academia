package Financeiro;

import Agendamento.GerenciadorDiaria;
import Agendamento.GerenciadorMensalidades;
import Pagamento.ProcessadorPagamento;
import java.util.List;

public class FinanceiroFacade {
    private GerenciadorMensalidades gerenciadorMensalidades;
    private GerenciadorDiaria gerenciadorDiaria;
    private GerenciadorPagamentosFuncionarios gerenciadorPagamentosFuncionarios;
    private AutenticacaoAdministrador autenticacaoAdministrador;
    

    public FinanceiroFacade(ProcessadorPagamento processadorPagamento) {
        this.gerenciadorMensalidades = new GerenciadorMensalidades(processadorPagamento);
        this.gerenciadorPagamentosFuncionarios = new GerenciadorPagamentosFuncionarios();
        this.autenticacaoAdministrador = new AutenticacaoAdministrador();
        this.gerenciadorDiaria = new GerenciadorDiaria(processadorPagamento);
    }

    // Autenticação de administrador
    public boolean autenticarAdministrador(int id, String senha) {
        return autenticacaoAdministrador.autenticar(id, senha);
    }

    // Registro de novo administrador
    public void registrarAdministrador(int id, String nome, String senha) {
        autenticacaoAdministrador.registrarAdministrador(id, nome, senha);
    }

    // Listagem de administradores
    public void listarAdministradores() {
        autenticacaoAdministrador.listarAdministradores();
    }

    // Relatório financeiro dos alunos
    public void exibirRelatorioFinanceiroAlunos() {
        gerenciadorMensalidades.relatorioFinanceiroAlunos();
        gerenciadorDiaria.relatorioFinanceiroAlunos();
    }

    // Adiciona um pagamento para um funcionário
    public boolean adicionarPagamentoFuncionario(int funcionarioId, double valor) {
        if (gerenciadorPagamentosFuncionarios.verificarFuncionarioRegistrado(funcionarioId)) {
            gerenciadorPagamentosFuncionarios.adicionarPagamento(funcionarioId, valor);
            return true;
        } else {
            return false;
        }
    }

    // Consultar pagamentos de um funcionário
    public List<PagamentoFuncionario> consultarPagamentosFuncionario(int funcionarioId) {
        return gerenciadorPagamentosFuncionarios.listarPagamentosPorFuncionario(funcionarioId);
    }

    // Relatório financeiro dos funcionários
    public void exibirRelatorioFinanceiroFuncionarios() {
        gerenciadorPagamentosFuncionarios.relatorioFinanceiroFuncionarios();
    }

    // Exibir balanço financeiro da academia
    public void exibirBalancoAcademia() {
        double totalRecebido = gerenciadorMensalidades.calcularTotalMensalidades();
        double totalRecebidoDiaria = gerenciadorDiaria.calcularTotalDiarias();
        double totalPago = gerenciadorPagamentosFuncionarios.calcularTotalPagamentos();
        double balanco = totalRecebido + totalRecebidoDiaria - totalPago;

        System.out.println("=== Balanço Financeiro da Academia ===");
        System.out.println("Total Recebido em Diria: R$" + totalRecebidoDiaria);
        System.out.println("Total Recebido em Mensalidades: R$" + totalRecebido);
        System.out.println("Total Pago aos Funcionários: R$" + totalPago);
        System.out.println("Balanço da Academia: R$" + balanco);
    }
}
