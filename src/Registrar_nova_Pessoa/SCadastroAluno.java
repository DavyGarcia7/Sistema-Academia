package Registrar_nova_Pessoa;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
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
import java.util.InputMismatchException;

public class SCadastroAluno extends Pessoa {

    private String email;
    private String endereco;
    private String telefone;

    // Construtor de SCadastroAluno que espera `id` como int
    public SCadastroAluno(String nome, int id, String cpf, String senha, String email, String endereco, String telefone) {
        super(nome, id, cpf, senha);  // Chama o construtor da superclasse Pessoa
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

    // Método para cadastrar um novo aluno com processamento de pagamento
    public static void CadastroA(ProcessadorPagamento processadorPagamento) {
        Scanner scanner = new Scanner(System.in);
        List<SCadastroAluno> alunos = new ArrayList<>();

        // Carregar alunos existentes do arquivo JSON
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

        // Gerar um ID único como int
        int id = alunos.size() + 1;
        
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

        // Solicitar informações de pagamento
        System.out.print("Digite o número do cartão: ");
        String numeroCartao = scanner.nextLine();
        
        System.out.print("Digite a data de validade do cartão (MM/AA): ");
        String validade = scanner.nextLine();
        
        System.out.print("Digite o nome do titular do cartão: ");
        String nomeTitular = scanner.nextLine();

        System.out.print("Digite o código de segurança do cartão: ");
        int codigoSeguranca = lerInteiro(scanner);

        Cartao cartao = new Cartao(numeroCartao, validade, nomeTitular, codigoSeguranca);

        // Processamento de pagamento antes de adicionar o aluno
        if (processadorPagamento.processarPagamento(cartao, 50.0)) {
            SCadastroAluno novoAluno = new SCadastroAluno(nome, id, cpf, senha, email, endereco, telefone);
            alunos.add(novoAluno);

            // Salvar lista atualizada de alunos no arquivo JSON
            try (FileWriter fileWriter = new FileWriter("pessoas.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                fileWriter.write(gson.toJson(alunos));
                fileWriter.flush();
                System.out.println("Dados armazenados em pessoas.json");
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Pagamento não aprovado. Cadastro não concluído.");
        }
    }

    // Método seguro para leitura do código de segurança do cartão
    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida! Por favor, digite um número inteiro: ");
                scanner.nextLine();  // Limpar entrada inválida
            }
        }
    }
}
