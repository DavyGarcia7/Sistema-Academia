package Registrar_nova_Pessoa;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.InputMismatchException;

public class SCadastroAluno extends Pessoa {

    private String email;
    private String endereco;
    private String telefone;

    public SCadastroAluno(String nome, String id, String cpf, String senha, String email, String endereco, String telefone) {
        super(nome, id, cpf, senha);
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static void CadastroA(ProcessadorPagamento processadorPagamento) {
        Scanner scanner = new Scanner(System.in);
        List<SCadastroAluno> alunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            alunos = gson.fromJson(br, listType);

            if (alunos == null) {
                alunos = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, começando uma nova lista.");
            alunos = new ArrayList<>();
        }

        String id = "ID" + (alunos.size() + 1);
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o número do cartão: ");
        String numeroCartao = scanner.nextLine();
        System.out.print("Digite a data de validade do cartão (MM/AA): ");
        String validade = scanner.nextLine();
        System.out.print("Digite o nome do titular do cartão: ");
        String nomeTitular = scanner.nextLine();

        // Usar método seguro para ler o código de segurança como um número inteiro
        System.out.print("Digite o código de segurança do cartão: ");
        int codigoSeguranca = lerInteiro(scanner);

        Cartao cartao = new Cartao(numeroCartao, validade, nomeTitular, codigoSeguranca);

        if (processadorPagamento.processarPagamento(cartao, 50.0)) {
            alunos.add(new SCadastroAluno(nome, id, cpf, senha, email, endereco, telefone));

            try (FileWriter fileWriter = new FileWriter("pessoas.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(alunos);
                fileWriter.write(json);
                fileWriter.flush();
                System.out.println("Dados armazenados em pessoas.json");

            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Pagamento não aprovado. Cadastro não concluído.");
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida! Por favor, digite um número inteiro: ");
                scanner.nextLine();
            }
        }
    }
}
