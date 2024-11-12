package Registrar_nova_Pessoa;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SCadastroAluno extends Pessoa {
    private static int contadorAlunos = 0;  // Contador de instâncias
    private String email;
    private String endereco;
    private String telefone;
    

    public SCadastroAluno(String nome, int id, String cpf, String senha, String email, String endereco, String telefone) {
        super(nome, id, cpf, senha);
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        contadorAlunos++;
    }

    // Getters e Setters adicionais

    public static int getContadorAlunos() {
        return contadorAlunos;
    }

    // Sobrescrevendo o método toString() para a classe SCadastroAluno
    @Override
    public String toString() {
        return "SCadastroAluno{" +
                "nome='" + getNome() + '\'' +
                ", id=" + getId() +
                ", cpf='" + getCpf() + '\'' +
                ", senha='" + getSenha() + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    // Método para cadastro de aluno
    public static void CadastroA(ProcessadorPagamento processadorPagamento) {
        Scanner scanner = new Scanner(System.in);
        List<SCadastroAluno> alunos = carregarAlunos();

        // Verificar se a lista foi carregada corretamente, caso contrário inicializar uma nova lista vazia
        if (alunos == null) {
            alunos = new ArrayList<>();
        }

        // Gerar um ID único
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

        Cartao cartao = coletarDadosCartao(scanner);
        if (processadorPagamento.processarPagamento(cartao, 50.0)) {
            SCadastroAluno novoAluno = new SCadastroAluno(nome, id, cpf, senha, email, endereco, telefone);
            alunos.add(novoAluno);
            salvarAlunos(alunos);
            System.out.println("Dados armazenados em pessoas.json");
        } else {
            System.out.println("Pagamento não aprovado. Cadastro não concluído.");
        }
    }

    public static List<SCadastroAluno> carregarAlunos() {
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.json"))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            List<SCadastroAluno> alunos = gson.fromJson(br, listType);

            if (alunos == null) {
                alunos = new ArrayList<>();
            }
            return alunos;
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarAlunos(List<SCadastroAluno> alunos) {
        try (FileWriter fileWriter = new FileWriter("pessoas.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(alunos));
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private static Cartao coletarDadosCartao(Scanner scanner) {
        System.out.print("Digite o número do cartão: ");
        String numeroCartao = scanner.nextLine();
        System.out.print("Digite a data de validade do cartão (MM/AA): ");
        String validade = scanner.nextLine();
        System.out.print("Digite o nome do titular do cartão: ");
        String nomeTitular = scanner.nextLine();
        System.out.print("Digite o código de segurança do cartão: ");
        int codigoSeguranca = scanner.nextInt();
        return new Cartao(numeroCartao, validade, nomeTitular, codigoSeguranca);
    }
}
