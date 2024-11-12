package Registrar_nova_Pessoa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SCadastrofuncionario extends Pessoa {
    private static int contadorFuncionarios = 0;  // Contador de instâncias
    private String setor;
    private String horarios;

    public SCadastrofuncionario(String nome, int id, String cpf, String senha, String setor, String horarios) {
        super(nome, id, cpf, senha);
        this.setor = setor;
        this.horarios = horarios;
        contadorFuncionarios++;
    }

    public static int getContadorFuncionarios() {
        return contadorFuncionarios;
    }

    @Override
    public String toString() {
        return "SCadastrofuncionario{" +
                "nome='" + getNome() + '\'' +
                ", id=" + getId() +
                ", cpf='" + getCpf() + '\'' +
                ", senha='" + getSenha() + '\'' +
                ", setor='" + setor + '\'' +
                ", horarios='" + horarios + '\'' +
                '}';
    }

    public static void CadastroF() {
        Scanner scanner = new Scanner(System.in);
        List<SCadastrofuncionario> funcionarios = carregarFuncionarios();

        if (funcionarios == null) {
            funcionarios = new ArrayList<>();
        }

        int id = funcionarios.size() + 1;
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        String setor = selecionarSetor(scanner);
        System.out.print("Digite os horários: ");
        String horarios = scanner.nextLine();

        SCadastrofuncionario novoFuncionario = new SCadastrofuncionario(nome, id, cpf, senha, setor, horarios);
        funcionarios.add(novoFuncionario);
        salvarFuncionarios(funcionarios);
        System.out.println("Dados armazenados em funcionarios.json");
    }

    private static String selecionarSetor(Scanner scanner) {
        System.out.print("Digite o setor:\n1. Instrutor\n2. Auxiliar de Limpeza\n3. Secretario/Balconista\nOpção: ");
        return switch (scanner.nextLine()) {
            case "1" -> "Instrutor";
            case "2" -> "Auxiliar de Limpeza";
            case "3" -> "Secretario/Balconista";
            default -> "Setor Desconhecido";
        };
    }

    public static List<SCadastrofuncionario> carregarFuncionarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastrofuncionario>>() {}.getType();
            List<SCadastrofuncionario> funcionarios = gson.fromJson(br, listType);

            if (funcionarios == null) {
                funcionarios = new ArrayList<>();
            }
            return funcionarios;
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarFuncionarios(List<SCadastrofuncionario> funcionarios) {
        try (FileWriter fileWriter = new FileWriter("funcionarios.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(funcionarios));
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
