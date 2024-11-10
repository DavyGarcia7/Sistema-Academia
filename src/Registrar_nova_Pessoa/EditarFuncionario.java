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

public class EditarFuncionario {

    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.json";
    private static final Scanner scanner = new Scanner(System.in);

    // Método para editar um funcionário existente com base no ID
    public static void editarFuncionarios(int id) {
        List<SCadastrofuncionario> funcionarios = carregarFuncionarios();
        
        if (funcionarios == null || funcionarios.isEmpty()) {  // Verifica se a lista é nula ou vazia
            System.out.println("Não há funcionários registrados para editar.");
            return;
        }

        SCadastrofuncionario funcionarioEncontrado = null;
        for (SCadastrofuncionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionarioEncontrado = funcionario;
                break;
            }
        }

        if (funcionarioEncontrado != null) {
            System.out.println("Funcionário encontrado. Atualize as informações (ou deixe em branco para manter o valor atual):");
            System.out.print("Nome (" + funcionarioEncontrado.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isBlank()) funcionarioEncontrado.setNome(novoNome);

            System.out.print("CPF (" + funcionarioEncontrado.getCpf() + "): ");
            String novoCpf = scanner.nextLine();
            if (!novoCpf.isBlank()) funcionarioEncontrado.setCpf(novoCpf);

            System.out.print("Setor (" + funcionarioEncontrado.getSetor() + "): ");
            String novoSetor = scanner.nextLine();
            if (!novoSetor.isBlank()) funcionarioEncontrado.setSetor(novoSetor);

            System.out.print("Horários (" + funcionarioEncontrado.getHorarios() + "): ");
            String novosHorarios = scanner.nextLine();
            if (!novosHorarios.isBlank()) funcionarioEncontrado.setHorarios(novosHorarios);

            salvarFuncionarios(funcionarios);
            System.out.println("Funcionário atualizado com sucesso.");
        } else {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
        }
    }

    // Método para carregar lista de funcionários do arquivo JSON
    private static List<SCadastrofuncionario> carregarFuncionarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SCadastrofuncionario>>() {}.getType();
            List<SCadastrofuncionario> funcionarios = gson.fromJson(br, listType);

            // Garantir que a lista seja inicializada mesmo se estiver vazia
            if (funcionarios == null) {
                funcionarios = new ArrayList<>();
            }
            return funcionarios;
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
            return new ArrayList<>();  // Retorna lista vazia em caso de erro
        }
    }

    // Método para salvar lista de funcionários no arquivo JSON
    private static void salvarFuncionarios(List<SCadastrofuncionario> funcionarios) {
        try (FileWriter writer = new FileWriter(ARQUIVO_FUNCIONARIOS)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(funcionarios));
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }
}
