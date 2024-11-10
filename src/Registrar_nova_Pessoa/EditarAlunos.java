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

public class EditarAlunos {

    private static final String ARQUIVO_ALUNOS = "pessoas.json";
    private static final Scanner scanner = new Scanner(System.in);

    // Método para editar um aluno existente
    public static void editarAluno() {
        List<SCadastroAluno> alunos = carregarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos registrados para editar.");
            return;
        }

        System.out.print("Digite o ID do aluno que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        SCadastroAluno alunoEncontrado = null;
        for (SCadastroAluno aluno : alunos) {
            if (aluno.getId() == id) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado. Atualize as informações (ou deixe em branco para manter o valor atual):");
            System.out.print("Nome (" + alunoEncontrado.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isBlank()) alunoEncontrado.setNome(novoNome);

            System.out.print("CPF (" + alunoEncontrado.getCpf() + "): ");
            String novoCpf = scanner.nextLine();
            if (!novoCpf.isBlank()) alunoEncontrado.setCpf(novoCpf);

            System.out.print("Email (" + alunoEncontrado.getEmail() + "): ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isBlank()) alunoEncontrado.setEmail(novoEmail);

            System.out.print("Endereço (" + alunoEncontrado.getEndereco() + "): ");
            String novoEndereco = scanner.nextLine();
            if (!novoEndereco.isBlank()) alunoEncontrado.setEndereco(novoEndereco);

            System.out.print("Telefone (" + alunoEncontrado.getTelefone() + "): ");
            String novoTelefone = scanner.nextLine();
            if (!novoTelefone.isBlank()) alunoEncontrado.setTelefone(novoTelefone);

            salvarAlunos(alunos);
            System.out.println("Aluno atualizado com sucesso.");
        } else {
            System.out.println("Aluno com ID " + id + " não encontrado.");
        }
    }

    // Método para carregar alunos do arquivo JSON
    private static List<SCadastroAluno> carregarAlunos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ALUNOS))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            return gson.fromJson(br, listType) != null ? gson.fromJson(br, listType) : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para salvar alunos no arquivo JSON
    private static void salvarAlunos(List<SCadastroAluno> alunos) {
        try (FileWriter writer = new FileWriter(ARQUIVO_ALUNOS)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(alunos));
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }
}
