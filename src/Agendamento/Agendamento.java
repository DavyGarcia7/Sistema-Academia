package Agendamento;


import Registrar_nova_Pessoa.SCadastroAluno;

public class Agendamento {
    private String nomeAluno; // Armazena o nome do aluno
    private String tipoAula;
    private String data;
    private double preco;
    private String instrutor;
    private boolean confirmado;

    public Agendamento(SCadastroAluno aluno, String tipoAula, String data, double preco, String instrutor) {
        this.nomeAluno = aluno.getNome();
        this.tipoAula = tipoAula;
        this.data = data;
        this.preco = preco;
        this.instrutor = instrutor;
        this.confirmado = false;
    }

    public void confirmarAgendamento() {
        this.confirmado = true;
        System.out.println("Agendamento confirmado para " + nomeAluno + " na data " + data);
    }

    public void cancelarAgendamento() {
        this.confirmado = false;
        System.out.println("Agendamento cancelado para " + nomeAluno);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(String tipoAula) {
        this.tipoAula = tipoAula;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }
}



