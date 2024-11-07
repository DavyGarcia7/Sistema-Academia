package Pagamento;

public class Cartao {
    private int id;
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;

    public Cartao(String numeroCartao, String nomeTitular, String dataValidade, int id) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
    }

    public int getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", dataValidade='" + dataValidade + '\'' +
                '}';
    }
}
