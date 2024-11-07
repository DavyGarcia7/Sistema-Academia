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
    
    // Construtor ajustado para incluir setor e horarios
    public SCadastrofuncionario(String nome, String id, String cpf, String senha, String setor, String horarios) {
        super(nome, id, cpf, senha);
        this.setor = setor;
        this.horarios = horarios;
    }

    // Getters e Setters
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
    
    public static void CadastroF() {
        Scanner scanner = new Scanner(System.in);
        List<SCadastrofuncionario> funcionarios = new ArrayList<>();
        
        // Tentar ler o arquivo existente
        try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastrofuncionario>>() {}.getType();
            funcionarios = gson.fromJson(br, listType);
        } catch (IOException e) {
            // Se o arquivo não existir, começamos com uma nova lista
            System.out.println("Arquivo não encontrado, começando uma nova lista.");
        }

        // Solicitar dados do funcionário
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite o ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Digite o setor:\n1. Novo Instrutor\n2. Novo Auxiliar de Limpeza\n3. Novo Secretario/Balconista\n4. Sair\nOpção: ");
            String opção = scanner.nextLine();
            String setor = switch (opção) {
                case "1" -> "Instrutor";
                case "2" -> "Auxiliar de Limpeza";
                case "3" -> "Secretario/Balconista";
                case "4" -> "Sair";
                default -> "Opção inválida";
            };

            if (!setor.equals("Opção inválida") && !setor.equals("Sair")) {
                 System.out.println("Setor selecionado: " + setor);
            } else if (setor.equals("Opção inválida")) {
                System.out.println("Opção inválida");
            }
        
        
        
        System.out.print("Digite os horários: ");
        String horarios = scanner.nextLine();
        
        // Criar um objeto SCadastrofuncionario e adicionar à lista
        funcionarios.add(new SCadastrofuncionario(nome, id, cpf, senha, setor, horarios));

        // Escrever de volta para o arquivo
        try (FileWriter fileWriter = new FileWriter("funcionarios.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(funcionarios);
            fileWriter.write(json);
            fileWriter.flush();
            System.out.println("Dados armazenados em funcionarios.json");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
