package Agendamento;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
import Registrar_nova_Pessoa.Pessoa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorDiaria {
    private List<DiariaDeAluno> diarias; // Lista de diárias
    private Gson gson;
    private ProcessadorPagamento processadorPagamento; // Pode ser null se o pagamento automático não for usado
    private static final String ARQUIVO_DIARIA = "Diaria.json"; // Nome correto do arquivo JSON

    // Construtor que aceita ProcessadorPagamento
    public GerenciadorDiaria(ProcessadorPagamento processadorPagamento) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.diarias = carregarDiariaDoArquivo();
        this.processadorPagamento = processadorPagamento;
    }

    // Construtor padrão sem ProcessadorPagamento
    public GerenciadorDiaria() {
        this(null);  // Chama o construtor principal com null
    }

    // Carrega as diárias do arquivo JSON
    private List<DiariaDeAluno> carregarDiariaDoArquivo() {
        try (FileReader reader = new FileReader(ARQUIVO_DIARIA)) {
            Type tipoListaDiaria = new TypeToken<List<DiariaDeAluno>>() {}.getType();
            List<DiariaDeAluno> lista = gson.fromJson(reader, tipoListaDiaria);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar diárias: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Verifica se o aluno está registrado
    public boolean verificarAlunoRegistrado(int alunoId) {
        try (FileReader reader = new FileReader("pessoas.json")) {
            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
            List<Pessoa> alunos = gson.fromJson(reader, listType);
            return alunos.stream().anyMatch(aluno -> aluno.getId() == alunoId);
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return false;
        }
    }

    // Adiciona uma nova diária para um aluno e salva no arquivo JSON
    public void adicionarDiaria(int alunoId, double valorPago) {
        DiariaDeAluno diaria = new DiariaDeAluno(alunoId, valorPago, new Date());
        diarias.add(diaria);
        salvarDiariaParaArquivo();
    }

    // Realiza um pagamento automático de diária para um aluno
    public boolean pagamentoAutomatico(int alunoId, Cartao cartao, double valorDiaria) {
        if (verificarAlunoRegistrado(alunoId)) {
            if (processadorPagamento != null && processadorPagamento.processarPagamento(cartao, valorDiaria)) {
                adicionarDiaria(alunoId, valorDiaria);
                System.out.println("Pagamento automático realizado com sucesso.");
                return true;
            } else {
                System.out.println("Erro no processamento do pagamento.");
            }
        } else {
            System.out.println("Aluno com ID " + alunoId + " não registrado.");
        }
        return false;
    }

    // Salva as diárias no arquivo JSON
    private void salvarDiariaParaArquivo() {
        try (FileWriter writer = new FileWriter(ARQUIVO_DIARIA)) {
            gson.toJson(diarias, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar diárias: " + e.getMessage());
        }
    }

    // Relatório financeiro dos alunos
    public void relatorioFinanceiroAlunos() {
        double totalRecebido = calcularTotalDiarias();
        System.out.println("=== Relatório Financeiro dos Alunos ===");
        System.out.println("Total recebido em diárias: R$" + totalRecebido);
    }

    // Calcula o total de diárias
    public double calcularTotalDiarias() {
        this.diarias = carregarDiariaDoArquivo();
        double total = 0;
        for (DiariaDeAluno diaria : diarias) {
            total += diaria.getValorPago();
        }
        return total;
    }

    // Lista diárias por aluno
    public List<DiariaDeAluno> listarDiariaPorAluno(int alunoId) {
        List<DiariaDeAluno> diariasAluno = new ArrayList<>();
        for (DiariaDeAluno diaria : diarias) {
            if (diaria.getAlunoId() == alunoId) {
                diariasAluno.add(diaria);
            }
        }
        return diariasAluno;
    }
}
