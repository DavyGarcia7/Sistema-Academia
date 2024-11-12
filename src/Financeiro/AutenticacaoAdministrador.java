package Financeiro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AutenticacaoAdministrador {
    private static final String ARQUIVO_ADMINISTRADORES = "administradores.json";
    private List<Administrador> administradores;
    private Gson gson;

    public AutenticacaoAdministrador() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.administradores = carregarAdministradores();
    }

    // Carregar administradores do arquivo JSON
   private List<Administrador> carregarAdministradores() {
    try (FileReader reader = new FileReader(ARQUIVO_ADMINISTRADORES)) {
        Type listType = new TypeToken<List<Administrador>>() {}.getType();
        List<Administrador> admins = gson.fromJson(reader, listType);
        return admins != null ? admins : new ArrayList<>();
    } catch (IOException e) {
        System.out.println("Arquivo não encontrado. Criando um novo arquivo: " + e.getMessage());
        salvarAdministradores(); // Cria um arquivo vazio se ele não existe
        return new ArrayList<>();
    }
}


    // Salvar administradores no arquivo JSON
  private void salvarAdministradores() {
    try (FileWriter writer = new FileWriter(ARQUIVO_ADMINISTRADORES)) {
        gson.toJson(administradores, writer);
    } catch (IOException e) {
        System.out.println("Erro ao salvar administradores: " + e.getMessage());
    }
}
    // Registrar um novo administrador
    public void registrarAdministrador(int id, String nome, String senha) {
        Administrador novoAdmin = new Administrador(id, nome, senha);
        administradores.add(novoAdmin);
        salvarAdministradores();
        System.out.println("Administrador registrado com sucesso!");
    }

    // Autenticar administrador
    public boolean autenticar(int id, String senha) {
        for (Administrador admin : administradores) {
            if (admin.getId() == id && admin.getSenha().equals(senha)) {
                System.out.println("Autenticação bem-sucedida.");
                return true;
            }
        }
        System.out.println("Falha na autenticação. ID ou senha incorretos.");
        return false;
    }

    // Listar todos os administradores
    public void listarAdministradores() {
        if (administradores.isEmpty()) {
            System.out.println("Nenhum administrador registrado.");
        } else {
            System.out.println("=== Lista de Administradores ===");
            for (Administrador admin : administradores) {
                System.out.println("ID: " + admin.getId() + ", Nome: " + admin.getNome());
            }
        }
    }
}
