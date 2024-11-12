package Agendamento;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ListarAgenda {
    public static void lerAgenda() {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.json"))) {
            Type agendamentoListType = new TypeToken<List<Agendamento>>() {}.getType();
            List<Agendamento> agendamentos = gson.fromJson(br, agendamentoListType);

            // Exibir os dados
            if (agendamentos != null && !agendamentos.isEmpty()) {
                for (Agendamento agendamento : agendamentos) {
                    System.out.println(agendamento); // Utilizando toString() para exibir o agendamento
                    System.out.println("------------------------------");
                }
            } else {
                System.out.println("Nenhum agendamento encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler agenda.json: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        lerAgenda();
    }
}
