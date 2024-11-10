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

    public SCadastroAluno(String nome, int id, String cpf, String senha, String email, String endereco, String telefone) {
        super(nome, cpf, senha);
        this.id = id;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Método para cadastro de aluno
    public static void CadastroA(ProcessadorPagamento processadorPagamento) {
        Scanner scanner = new Scanner(System.in);
        List<SCadastroAluno> alunos = carregarAlunos();

        // Gera um novo ID baseado no tamanho da lista de alunos
        int novoId = alunos.size() + 1;

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

        System.out.print("Digite o código de segurança do cartão: ");
        int codigoSeguranca = lerInteiro(scanner);

        Cartao cartao = new Cartao(numeroCartao, validade, nomeTitular, codigoSeguranca);

        if (processadorPagamento.processarPagamento(cartao, 50.0)) {
            SCadastroAluno novoAluno = new SCadastroAluno(nome, novoId, cpf, senha, email, endereco, telefone);
            alunos.add(novoAluno);

            salvarAlunos(alunos);
            System.out.println("Aluno cadastrado e dados armazenados em pessoas.json.");
        } else {
            System.out.println("Pagamento não aprovado. Cadastro não concluído.");
        }
    }

    // Método seguro para ler um número inteiro do scanner
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

    // Método para carregar alunos do arquivo JSON
    private static List<SCadastroAluno> carregarAlunos() {
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            List<SCadastroAluno> alunos = gson.fromJson(br, listType);
            return alunos != null ? alunos : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, começando uma nova lista de alunos.");
            return new ArrayList<>();
        }
    }

    // Método para salvar lista de alunos no arquivo JSON
    private static void salvarAlunos(List<SCadastroAluno> alunos) {
        try (FileWriter writer = new FileWriter("pessoas.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(alunos));
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }
}
