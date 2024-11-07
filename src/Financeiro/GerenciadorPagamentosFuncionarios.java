package Financeiro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import Registrar_nova_Pessoa.Pessoa;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorPagamentosFuncionarios {
    private List<PagamentoFuncionario> pagamentos;
    private Gson gson;
    private static final String ARQUIVO_PAGAMENTOS = "pagamentos_funcionarios.json";

    public GerenciadorPagamentosFuncionarios() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.pagamentos = carregarPagamentosDoArquivo();
    }

    // Carrega os pagamentos do arquivo JSON
    private List<PagamentoFuncionario> carregarPagamentosDoArquivo() {
        try (FileReader reader = new FileReader(ARQUIVO_PAGAMENTOS)) {
            Type tipoListaPagamento = new TypeToken<List<PagamentoFuncionario>>() {}.getType();
            List<PagamentoFuncionario> lista = gson.fromJson(reader, tipoListaPagamento);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar pagamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Verifica se o funcionário está registrado
    public boolean verificarFuncionarioRegistrado(int funcionarioId) {
        try (FileReader reader = new FileReader("funcionarios.json")) {
            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
            List<Pessoa> funcionarios = gson.fromJson(reader, listType);
            return funcionarios.stream().anyMatch(funcionario -> funcionario.getId() == funcionarioId);
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
            return false;
        }
    }

    // Lista todos os pagamentos feitos para um funcionário específico
    public List<PagamentoFuncionario> listarPagamentosPorFuncionario(int funcionarioId) {
        List<PagamentoFuncionario> resultado = new ArrayList<>();
        for (PagamentoFuncionario pagamento : pagamentos) {
            if (pagamento.getFuncionarioId() == funcionarioId) {
                resultado.add(pagamento);
            }
        }
        return resultado;
    }

    // Exibe um relatório financeiro de todos os pagamentos feitos aos funcionários
    public void relatorioFinanceiroFuncionarios() {
        double totalPago = calcularTotalPagamentos();
        System.out.println("=== Relatório Financeiro dos Funcionários ===");
        System.out.println("Total pago aos funcionários: R$" + totalPago);
    }

    // Calcula o total de pagamentos
    public double calcularTotalPagamentos() {
        double total = 0;
        for (PagamentoFuncionario pagamento : pagamentos) {
            total += pagamento.getValorPago();
        }
        return total;
    }

    // Adiciona um novo pagamento para um funcionário e salva no arquivo JSON
    public void adicionarPagamento(int funcionarioId, double valorPago) {
        PagamentoFuncionario pagamento = new PagamentoFuncionario(funcionarioId, valorPago, new Date());
        pagamentos.add(pagamento);
        salvarPagamentosParaArquivo();
    }

    // Salva os pagamentos no arquivo JSON
    private void salvarPagamentosParaArquivo() {
        try (FileWriter writer = new FileWriter(ARQUIVO_PAGAMENTOS)) {
            gson.toJson(pagamentos, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar pagamentos: " + e.getMessage());
        }
    }
}
