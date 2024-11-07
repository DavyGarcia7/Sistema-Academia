package Agendamento;

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
    private final List<Agendamento> agendamentos;
    private List<SCadastroAluno> alunos;
    private Scanner scanner;

    public Agenda() {
        this.agendamentos = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.scanner = new Scanner(System.in); // Inicialize o Scanner aqui
    }

    public void agendar() { // Alterado de static para instância
        carregarAlunos(); // Método chamado corretamente

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
            scanner.nextLine(); // Limpa a entrada
            return; 
        }

        int alunoIndex = scanner.nextInt() - 1;

        if (alunoIndex < 0 || alunoIndex >= alunos.size()) {
            System.out.println("Aluno inválido.");
            return;
        }

        SCadastroAluno aluno = alunos.get(alunoIndex);
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Tipo de Aula (spinning, musculação, fit dance, pilates): ");
        String tipoAula = scanner.nextLine();
        System.out.print("Data do Agendamento (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.print("Preço: ");

        if (!scanner.hasNextDouble()) { 
            System.out.println("Entrada inválida para o preço.");
            scanner.nextLine(); // Limpa a entrada
            return; 
        }

        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Instrutor: ");
        String instrutor = scanner.nextLine();

        Agendamento agendamento = new Agendamento(aluno, tipoAula, data, preco, instrutor);
        agendamentos.add(agendamento);
        System.out.println("Agendamento adicionado com sucesso para " + aluno.getNome());
        salvarAgendamentos();
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

    private void salvarAgendamentos() {
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
}

