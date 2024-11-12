package Agendamento;

import java.util.Date;

public class DiariaDeAluno {
    private int alunoId;
    private double valorPago;
    private Date dataPagamento;

    public DiariaDeAluno(int alunoId, double valorPago, Date dataPagamento) {
        this.alunoId = alunoId;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    // Getters e Setters
    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
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
    public String toString(){
        return"DiariaAluno" +" "+ alunoId +" "+ valorPago +" "+ dataPagamento;
    }
}
