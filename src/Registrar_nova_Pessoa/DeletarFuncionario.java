package Registrar_nova_Pessoa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DeletarFuncionario {
    private static final String NOME_ARQUIVO = "funcionarios.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void deletarFuncionarioPorId(int id) {
        List<Pessoa> listaFuncionarios = lerArquivoJson();

        if (listaFuncionarios != null) {
            // Usando um iterador para evitar problemas de concorrência ao remover elementos da lista
            Iterator<Pessoa> iterator = listaFuncionarios.iterator();
            boolean funcionarioRemovido = false;

            while (iterator.hasNext()) {
                Pessoa funcionario = iterator.next();
                if (funcionario.getId() == id) {  // Comparação com o ID do funcionário
                    iterator.remove();
                    funcionarioRemovido = true;
                    break;
                }
            }

            if (funcionarioRemovido) {
                salvarArquivoJson(listaFuncionarios);
                System.out.println("Funcionário com ID " + id + " deletado com sucesso.");
            } else {
                System.out.println("Funcionário com ID " + id + " não encontrado.");
            }
        } else {
            System.out.println("Erro ao carregar a lista de funcionários.");
        }
    }

    private static List<Pessoa> lerArquivoJson() {
        try (FileReader reader = new FileReader(NOME_ARQUIVO)) {
            Type tipoListaPessoa = new TypeToken<ArrayList<Pessoa>>() {}.getType();
            return gson.fromJson(reader, tipoListaPessoa);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    private static void salvarArquivoJson(List<Pessoa> listaFuncionarios) {
        try (FileWriter writer = new FileWriter(NOME_ARQUIVO)) {
            gson.toJson(listaFuncionarios, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
