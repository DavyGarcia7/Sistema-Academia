package Loja;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GerenciamentoProduto {
    private List<ProdutosLoja> produtos;
    private Gson gson;
    private final String DIRECTORY_PATH = "data"; // Diretório da pasta
    private final String FILE_PATH = DIRECTORY_PATH + "/loja.json"; // Caminho do arquivo JSON

    public GerenciamentoProduto(List<ProdutosLoja> produtos) {
        this.produtos = produtos;
        this.gson = new Gson();
        carregarProdutosDeArquivo(); // Carregar produtos do arquivo ao inicializar
    }

    // Método para adicionar um novo produto ao estoque e salvar no arquivo JSON
    public void adicionarProduto(ProdutosLoja produto) {
        produtos.add(produto);
        salvarProdutosParaArquivo();
    }

    // Método para remover um produto do estoque pelo ID e salvar no arquivo JSON
    public boolean removerProdutoPorId(int id) {
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);
        if (removido) {
            salvarProdutosParaArquivo();
        }
        return removido;
    }

    // Método para atualizar a quantidade de um produto no estoque e salvar no arquivo JSON
    public boolean atualizarQuantidade(int id, int novaQuantidade) {
        for (ProdutosLoja produto : produtos) {
            if (produto.getId() == id) {
                produto.setQuantidade(novaQuantidade);
                salvarProdutosParaArquivo();
                return true;
            }
        }
        return false;
    }

    // Método para salvar a lista de produtos no arquivo JSON
    private void salvarProdutosParaArquivo() {
        // Verificar se a pasta 'data' existe; se não, criar a pasta
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria a pasta 'data'
        }

        // Salvar no arquivo JSON
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(produtos, writer);
            System.out.println("Produtos salvos com sucesso em " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos no arquivo: " + e.getMessage());
        }
    }

    // Método para carregar a lista de produtos do arquivo JSON
    public void carregarProdutosDeArquivo() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FILE_PATH)) {
                Type produtoListType = new TypeToken<List<ProdutosLoja>>(){}.getType();
                produtos.clear();
                List<ProdutosLoja> produtosCarregados = gson.fromJson(reader, produtoListType);
                if (produtosCarregados != null) {
                    produtos.addAll(produtosCarregados);
                }
                System.out.println("Produtos carregados do arquivo " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("Erro ao carregar produtos do arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo 'loja.json' não encontrado. Iniciando com estoque vazio.");
        }
    }
}
