package Financeiro;

public class BalancoAcademia {
    private double totalRecebido;
    private double totalPago;

    public BalancoAcademia(double totalRecebido, double totalPago) {
        this.totalRecebido = totalRecebido;
        this.totalPago = totalPago;
    }

    // Método para calcular o balanço final
    public double calcularBalanco() {
        return totalRecebido - totalPago;
    }

    // Getters e Setters
    public double getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    // Método para exibir o balanço financeiro
    public void exibirBalanco() {
        System.out.println("=== Balanço Financeiro da Academia ===");
        System.out.println("Total Recebido em Mensalidades: R$" + totalRecebido);
        System.out.println("Total Pago aos Funcionários: R$" + totalPago);
        System.out.println("Balanço da Academia: R$" + calcularBalanco());
    }

    @Override
    public String toString() {
        return "BalancoAcademia{" +
                "totalRecebido=R$" + totalRecebido +
                ", totalPago=R$" + totalPago +
                ", balanço=R$" + calcularBalanco() +
                '}';
    }
}
