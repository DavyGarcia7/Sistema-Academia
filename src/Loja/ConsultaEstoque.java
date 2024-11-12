package Loja;

import java.util.List;

public class ConsultaEstoque {
    private List<ProdutosLoja> produtos;

    public ConsultaEstoque(List<ProdutosLoja> produtos) {
        this.produtos = produtos;
    }

    // Método para verificar a quantidade de um produto pelo ID
    public int verificarQuantidade(int id) {
        for (ProdutosLoja produto : produtos) {
            if (produto.getId() == id) {
                return produto.getQuantidade();
            }
        }
        return -1; // Produto não encontrado
    }

    // Método para listar todos os produtos no estoque
    public void listarEstoque() {
        System.out.println("Estoque atual:");
        for (ProdutosLoja produto : produtos) {
            System.out.println(produto); // Chamando o toString() da classe ProdutosLoja
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConsultaEstoque{produtos=[\n");
        for (ProdutosLoja produto : produtos) {
            builder.append(produto.toString()).append(",\n");
        }
        builder.append("]}");
        return builder.toString();
    }
}
