package Registrar_nova_Pessoa;

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

public class SCadastrofuncionario extends Pessoa {

    private String setor;
    private String horarios;

    public SCadastrofuncionario(String nome, int id, String cpf, String senha, String setor, String horarios) {
        super(nome, cpf, senha);
        this.id = id; // Definindo o ID
        this.setor = setor;
        this.horarios = horarios;
    }

    // Método para cadastrar funcionário
    public static void CadastroF() {
        Scanner scanner = new Scanner(System.in);
        List<SCadastrofuncionario> funcionarios = carregarFuncionarios();

        // Gerar um novo ID baseado no tamanho da lista de funcionários
        int novoId = funcionarios.size() + 1;

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o setor:\n1. Novo Instrutor\n2. Novo Auxiliar de Limpeza\n3. Novo Secretario/Balconista\n4. Sair\nOpção: ");
        String opcao = scanner.nextLine();
        String setor = switch (opcao) {
            case "1" -> "Instrutor";
            case "2" -> "Auxiliar de Limpeza";
            case "3" -> "Secretario/Balconista";
            case "4" -> {
                System.out.println("Saindo do cadastro de funcionários.");
                yield null;
            }
            default -> {
                System.out.println("Opção inválida");
                yield null;
            }
        };

        if (setor == null) {
            return;
        }

        System.out.print("Digite os horários: ");
        String horarios = scanner.nextLine();

        SCadastrofuncionario novoFuncionario = new SCadastrofuncionario(nome, novoId, cpf, senha, setor, horarios);
        funcionarios.add(novoFuncionario);

        salvarFuncionarios(funcionarios);
        System.out.println("Funcionário cadastrado e dados armazenados em funcionarios.json.");
    }

    // Método para carregar lista de funcionários do arquivo JSON
    private static List<SCadastrofuncionario> carregarFuncionarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastrofuncionario>>() {}.getType();
            List<SCadastrofuncionario> funcionarios = gson.fromJson(br, listType);
            return funcionarios != null ? funcionarios : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, começando uma nova lista de funcionários.");
            return new ArrayList<>();
        }
    }

    // Método para salvar lista de funcionários no arquivo JSON
    private static void salvarFuncionarios(List<SCadastrofuncionario> funcionarios) {
        try (FileWriter fileWriter = new FileWriter("funcionarios.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(funcionarios);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
