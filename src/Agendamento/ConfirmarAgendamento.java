package Agendamento;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ConfirmarAgendamento {

    private static final String CAMINHO_ARQUIVO = "agenda.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Método para carregar agendamentos do arquivo JSON
    private static List<Agendamento> carregarAgendamentos() {
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type listType = new TypeToken<List<Agendamento>>() {}.getType();
            List<Agendamento> agendamentos = gson.fromJson(reader, listType);
            return agendamentos != null ? agendamentos : new java.util.ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar agendamentos: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    // Método para salvar agendamentos no arquivo JSON
    private static void salvarAgendamentos(List<Agendamento> agendamentos) {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(agendamentos, writer);
            System.out.println("Agendamentos salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar agendamentos: " + e.getMessage());
        }
    }

    // Método para confirmar um agendamento, com base no ID do aluno e na data do agendamento
    public static boolean confirmarAgendamento(int alunoId) {
        List<Agendamento> agendamentos = carregarAgendamentos();
        
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId()== alunoId) {
                if (!agendamento.isConfirmado()) {
                    agendamento.confirmarAgendamento();
                    System.out.println("Agendamento confirmado com sucesso para o aluno com ID: " + alunoId);
                    salvarAgendamentos(agendamentos); // Salva o JSON após confirmar
                    return true;
                } else {
                    System.out.println("O agendamento já está confirmado para o aluno com ID: " + alunoId);
                    return false;
                }
            }
        }
        
        System.out.println("Agendamento não encontrado para o aluno com ID: " + alunoId );
        return false;
    }

    // Método para cancelar um agendamento
    public static boolean cancelarAgendamento(int alunoId) {
        List<Agendamento> agendamentos = carregarAgendamentos();
        
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId()== alunoId) {
                if (agendamento.isConfirmado()) {
                    agendamento.cancelarAgendamento();
                    System.out.println("Agendamento cancelado com sucesso para o aluno com ID: " + alunoId);
                    salvarAgendamentos(agendamentos); // Salva o JSON após cancelar
                    return true;
                } else {
                    System.out.println("O agendamento já está cancelado para o aluno com ID: " + alunoId);
                    return false;
                }
            }
        }
        
        System.out.println("Agendamento não encontrado para o aluno com ID: " + alunoId);
        return false;
    }
}
