/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            Type agendamentoListType = new TypeToken<List<Agendamento>>() {}.getType(); // Mudei de Agenda para Agendamento
            List<Agendamento> agendamentos = gson.fromJson(br, agendamentoListType);
            
            // Exibir os dados
            if (agendamentos != null && !agendamentos.isEmpty()) {
                for (Agendamento agendamento : agendamentos) {
                    // Exibir informações relevantes do agendamento
                    System.out.println("Aluno: " + agendamento.getNomeAluno()); // Certifique-se de ter um getter para nomeAluno
                    System.out.println("Tipo de Aula: " + agendamento.getTipoAula());
                    System.out.println("Data: " + agendamento.getData());
                    System.out.println("Preço: " + agendamento.getPreco());
                    System.out.println("Instrutor: " + agendamento.getInstrutor());
                    System.out.println("Confirmado: " + agendamento.isConfirmado());
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


