package Loja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author davy-garcia
 */

public class EscolhaSetor {
    private GerenciamentoProduto gerenciamentoProduto;
    private ConsultaEstoque consultaEstoque;
    private Scanner scanner;

    public EscolhaSetor() {
        List<ProdutosLoja> produtos = new ArrayList<>();
        this.gerenciamentoProduto = new GerenciamentoProduto(produtos);
        this.consultaEstoque = new ConsultaEstoque(produtos);
        this.scanner = new Scanner(System.in);
    }

    public void escolhasetor() {
        while (true) {
            System.out.println("=== Sistema de Gerenciamento de Loja ===");
            System.out.println("1. Loja");
            System.out.println("2. Lanchonete");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 7:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarProduto() {
        System.out.print("Digite o setor (1 para Loja, 2 para Lanchonete): ");
        int setor = scanner.nextInt();
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        ProdutosLoja produto = new ProdutosLoja(id, nome, quantidade, preco);
        gerenciamentoProduto.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso e salvo no arquivo.");
    }

    private void removerProduto() {
        System.out.print("Digite o ID do produto a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        if (gerenciamentoProduto.removerProdutoPorId(id)) {
            System.out.println("Produto removido com sucesso e atualizado no arquivo.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void atualizarQuantidade() {
        System.out.print("Digite o ID do produto a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.print("Digite a nova quantidade: ");
        int novaQuantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (gerenciamentoProduto.atualizarQuantidade(id, novaQuantidade)) {
            System.out.println("Quantidade atualizada com sucesso e salva no arquivo.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarProdutos() {
        consultaEstoque.listarEstoque();
    }

    private void exportarParaJson() {
        System.out.println("Produtos já estão sendo salvos automaticamente em 'data/loja.json'.");
    }

    private void importarDeJson() {
        System.out.println("Importando produtos do arquivo 'data/loja.json'...");
        gerenciamentoProduto.carregarProdutosDeArquivo();
        System.out.println("Produtos importados com sucesso.");
    }

    public static void main(String[] args) {
        EscolhaSetor loja = new EscolhaSetor();
        loja.escolhasetor();
    }
}
