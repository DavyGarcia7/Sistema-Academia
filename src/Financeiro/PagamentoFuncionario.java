package Financeiro;

import java.util.Date;

public class PagamentoFuncionario {
    private int funcionarioId;
    private double valorPago;
    private Date dataPagamento;

    public PagamentoFuncionario(int funcionarioId, double valorPago, Date dataPagamento) {
        this.funcionarioId = funcionarioId;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    // Getters e Setters
    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
