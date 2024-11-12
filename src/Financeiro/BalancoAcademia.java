package Financeiro;

public class BalancoAcademia {
    private double totalRecebido;
    private double totalPago;
    private double totalRecebidoDiaria;

    public BalancoAcademia() {
        this.totalRecebido = 0.0;
        this.totalPago = 0.0;
        this.totalRecebidoDiaria = 0.0;
    }

    // Método para registrar recebimentos
    public void registrarRecebimento(double valor) {
        totalRecebido += valor;
    }

    // Método para registrar pagamentos
    public void registrarPagamento(double valor) {
        totalPago += valor;
    }

    // Método para calcular o balanço final
    public double calcularBalanco() {
        return totalRecebido + totalRecebidoDiaria - totalPago;
    }

    // Exibir balanço
    public void exibirBalanco() {
        System.out.println("=== Balanço Financeiro da Academia ===");
        System.out.println("Total Recebido em Mensalidades: R$" + totalRecebido);
        System.out.println("Total Recebido em Diaria: R$" + totalRecebidoDiaria);
        System.out.println("Total Pago aos Funcionários: R$" + totalPago);
        System.out.println("Balanço da Academia: R$" + calcularBalanco());
    }
}
