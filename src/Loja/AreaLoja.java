package Loja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AreaLoja {
    private GerenciamentoProduto gerenciamentoProduto;
    private ConsultaEstoque consultaEstoque;
    private Scanner scanner;

    public AreaLoja() {
        List<ProdutosLoja> produtos = new ArrayList<>();
        this.gerenciamentoProduto = new GerenciamentoProduto(produtos);
        this.consultaEstoque = new ConsultaEstoque(produtos);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean executando = true;

        while (executando) {
            System.out.println("=== Sistema de Gerenciamento de Loja ===");
            System.out.println("1. Adicionar novo Produto");
            System.out.println("2. Remover Produto");
            System.out.println("3. Atualizar Quantidade de Produto");
            System.out.println("4. Listar Produtos no Estoque");
            System.out.println("5. Exportar Produtos para JSON");
            System.out.println("6. Importar Produtos de JSON");
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
                case 3:
                    atualizarQuantidade();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    exportarParaJson();
                    break;
                case 6:
                    importarDeJson();
                    break;
                case 7:
                    System.out.println("Saindo do sistema...");
                    executando = false; // Finaliza o loop
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void adicionarProduto() {
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
}
