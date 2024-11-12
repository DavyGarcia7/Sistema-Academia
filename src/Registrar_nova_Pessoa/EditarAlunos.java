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

    // Método para editar um aluno existente com base no ID
    public static void editarAluno(int id) {
        List<SCadastroAluno> alunos = carregarAlunos();

        if (alunos == null || alunos.isEmpty()) {  // Verificar se a lista é nula ou vazia
            System.out.println("Não há alunos registrados para editar.");
            return;
        }

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
            List<SCadastroAluno> alunos = gson.fromJson(br, listType);

            // Garantir que a lista seja inicializada mesmo se estiver vazia
            if (alunos == null) {
                alunos = new ArrayList<>();
            }
            return alunos;
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return new ArrayList<>();  // Retorna lista vazia em caso de erro
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

    @Override
    public String toString() {
        return "EditarAlunos{" +
                "ARQUIVO_ALUNOS='" + ARQUIVO_ALUNOS + '\'' +
                ", scanner=" + scanner +
                '}';
    }
}
