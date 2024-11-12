package Agendamento;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamentoImpl;
import Pagamento.ProcessadorPagamento;
import Registrar_nova_Pessoa.SCadastroAluno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    private String[] salas = {"spinning", "musculação", "fit dance", "pilates"};
    private GerenciadorMensalidades gerenciadorMensalidades;
    private List<SCadastroAluno> alunos;
    private Scanner scanner;
    private ProcessadorPagamento processadorPagamento;
    private int nextId; // Contador para IDs de agendamentos

    // Variáveis de instância para armazenar o alunoId e preço
    private int alunoId;
    private double preco;

    // Instanciação do processador de pagamento e inicialização das listas
    public Agenda() {
        this.processadorPagamento = new ProcessadorPagamentoImpl();
        this.gerenciadorMensalidades = new GerenciadorMensalidades(processadorPagamento);
        this.alunos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.nextId = obterProximoId(); // Inicializa o contador de ID
    }

    // Método para obter o próximo ID com base no último ID usado no arquivo JSON
    

    public void agendar() {
        carregarAlunos();

        System.out.println("=== Sistema de Agendamento ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno carregado.");
            return;
        }

        System.out.println("Selecione o aluno para o agendamento:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i + 1) + ". " + alunos.get(i).getNome());
        }
        System.out.print("Escolha um número: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.nextLine();
            return;
        }

        int alunoIndex = scanner.nextInt() - 1;
        if (alunoIndex < 0 || alunoIndex >= alunos.size()) {
            System.out.println("Aluno inválido.");
            return;
        }

        SCadastroAluno aluno = alunos.get(alunoIndex);
        this.alunoId = aluno.getId();
        scanner.nextLine(); 

        System.out.print("Tipo de Aula: " + String.join(", ", salas) + ": ");
        String tipoAula = scanner.nextLine();
        System.out.print("Data do Agendamento (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.print("Preço: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida para o preço.");
            scanner.nextLine();
            return;
        }

        preco = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Instrutor: ");
        String instrutor = scanner.nextLine();

        System.out.print("Digite o número do cartão: ");
        String numeroCartao = scanner.nextLine();
        System.out.print("Digite a data de validade do cartão (MM/AA): ");
        String validade = scanner.nextLine();
        System.out.print("Digite o nome do titular do cartão: ");
        String nomeTitular = scanner.nextLine();
        System.out.print("Digite o código de segurança do cartão: ");
        int codigoSeguranca = scanner.nextInt();

        if (processadorPagamento.processarPagamento(new Cartao(numeroCartao, validade, nomeTitular, codigoSeguranca), preco)) {
            // Usa `nextId` para criar um ID único para o novo agendamento
            Agendamento agendamento = new Agendamento(aluno, nextId, tipoAula, data, preco, instrutor);
            nextId++; // Incrementa o ID para o próximo agendamento
            List<Agendamento> agendamentos = carregarAgendamentos();
            agendamentos.add(agendamento);

            System.out.println("Agendamento adicionado com sucesso para " + aluno.getNome());
            salvarAgendamentos(agendamentos);
        } else {
            System.out.println("Erro ao processar pagamento. Agendamento não concluído.");
        }
    }

    // Método para confirmar um agendamento
    public void confirmarAgendamento() {
        System.out.print("Digite o ID do aluno para confirmar o agendamento: ");
        int alunoId = scanner.nextInt();
      

        if (ConfirmarAgendamento.confirmarAgendamento(alunoId)) {
            System.out.println("Agendamento confirmado com sucesso.");
        } else {
            System.out.println("Falha ao confirmar o agendamento.");
        }
    }

    // Método para cancelar um agendamento
    public void cancelarAgendamento() {
        System.out.print("Digite o ID do aluno para cancelar o agendamento: ");
        int alunoId = scanner.nextInt();
        

        if (ConfirmarAgendamento.cancelarAgendamento(alunoId)) {
            System.out.println("Agendamento cancelado com sucesso.");
        } else {
            System.out.println("Falha ao cancelar o agendamento.");
        }
    }

    private void carregarAlunos() {
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            alunos = gson.fromJson(br, listType);
            if (alunos == null) {
                alunos = new ArrayList<>();
            }
            System.out.println("Alunos carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            alunos = new ArrayList<>();
        }
    }

    private List<Agendamento> carregarAgendamentos() {
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Agendamento>>() {}.getType();
            List<Agendamento> agendamentos = gson.fromJson(br, listType);
            return agendamentos != null ? agendamentos : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar agendamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void salvarAgendamentos(List<Agendamento> agendamentos) {
        try (FileWriter fileWriter = new FileWriter("agenda.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(agendamentos);
            fileWriter.write(json);
            fileWriter.flush();
            System.out.println("Agendamentos salvos em agenda.json");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os agendamentos: " + e.getMessage());
        }
    }

    public void adicionarMensalidadeAula() {
        if (gerenciadorMensalidades.verificarAlunoRegistrado(alunoId)) {
            gerenciadorMensalidades.adicionarMensalidade(alunoId, preco);
            System.out.println("Mensalidade adicionada com sucesso para o aluno com ID: " + alunoId);
        } else {
            System.out.println("Aluno com ID " + alunoId + " não encontrado em pessoas.json.");
        }
    }
    private int obterProximoId() {
        List<Agendamento> agendamentos = carregarAgendamentos();
        int maxId = 0;
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() > maxId) {
                maxId = agendamento.getId();
            }
        }
        return maxId + 1; // Próximo ID será o maior ID existente + 1
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Agenda{\n");
        List<Agendamento> agendamentos = carregarAgendamentos();
        for (Agendamento agendamento : agendamentos) {
            sb.append(agendamento.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
