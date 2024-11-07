package Financeiro;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
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

public class GerenciadorMensalidades {
    private List<MensalidadeAluno> mensalidades;
    private Gson gson;
    private ProcessadorPagamento processadorPagamento; // Pode ser null se o pagamento automático não for usado
    private static final String ARQUIVO_MENSALIDADES = "mensalidades.json";

    // Construtor que aceita ProcessadorPagamento
    public GerenciadorMensalidades(ProcessadorPagamento processadorPagamento) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mensalidades = carregarMensalidadesDoArquivo();
        this.processadorPagamento = processadorPagamento;
    }

    // Construtor padrão sem ProcessadorPagamento (opcional, para flexibilidade)
    public GerenciadorMensalidades() {
        this(null);  // Chama o construtor principal com null
    }

    // Carrega as mensalidades do arquivo JSON
    private List<MensalidadeAluno> carregarMensalidadesDoArquivo() {
        try (FileReader reader = new FileReader(ARQUIVO_MENSALIDADES)) {
            Type tipoListaMensalidade = new TypeToken<List<MensalidadeAluno>>() {}.getType();
            List<MensalidadeAluno> lista = gson.fromJson(reader, tipoListaMensalidade);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar mensalidades: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Verifica se o aluno está registrado
    public boolean verificarAlunoRegistrado(int alunoId) {
        try (FileReader reader = new FileReader("alunos.json")) {
            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
            List<Pessoa> alunos = gson.fromJson(reader, listType);
            return alunos.stream().anyMatch(aluno -> aluno.getId() == alunoId);
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return false;
        }
    }

    // Adiciona uma nova mensalidade para um aluno e salva no arquivo JSON
    public void adicionarMensalidade(int alunoId, double valorPago) {
        MensalidadeAluno mensalidade = new MensalidadeAluno(alunoId, valorPago, new Date());
        mensalidades.add(mensalidade);
        salvarMensalidadesParaArquivo();
    }

    // Realiza um pagamento automático de mensalidade para um aluno
    public boolean pagamentoAutomatico(int alunoId, Cartao cartao, double valorMensalidade) {
        if (verificarAlunoRegistrado(alunoId)) {
            if (processadorPagamento != null && processadorPagamento.processarPagamento(cartao, valorMensalidade)) {
                adicionarMensalidade(alunoId, valorMensalidade);
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

    // Salva as mensalidades no arquivo JSON
    private void salvarMensalidadesParaArquivo() {
        try (FileWriter writer = new FileWriter(ARQUIVO_MENSALIDADES)) {
            gson.toJson(mensalidades, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar mensalidades: " + e.getMessage());
        }
    }

    // Relatório financeiro dos alunos
    public void relatorioFinanceiroAlunos() {
        double totalRecebido = calcularTotalMensalidades();
        System.out.println("=== Relatório Financeiro dos Alunos ===");
        System.out.println("Total recebido em mensalidades: R$" + totalRecebido);
    }

    // Calcula o total de mensalidades
    public double calcularTotalMensalidades() {
        double total = 0;
        for (MensalidadeAluno mensalidade : mensalidades) {
            total += mensalidade.getValorPago();
        }
        return total;
    }
      public List<MensalidadeAluno> listarMensalidadesPorAluno(int alunoId) {
        List<MensalidadeAluno> mensalidadesAluno = new ArrayList<>();
        for (MensalidadeAluno mensalidade : mensalidades) {
            if (mensalidade.getAlunoId() == alunoId) {
                mensalidadesAluno.add(mensalidade);
            }
        }
        return mensalidadesAluno;
    }
}

