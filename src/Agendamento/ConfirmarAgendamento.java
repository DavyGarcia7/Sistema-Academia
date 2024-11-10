/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agendamento;

/**
 *
 * @author davy-garcia
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

///public class ConfirmarAgendamento {

    //private static String nomeAluno;

    public static void confirmar(String[] args) {
        try {
            atualizarStatus("agenda.json");
        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever o arquivo JSON: " + e.getMessage());
        }
    }

    public static void atualizarStatus(String caminhoArquivo, String confirmado) throws IOException {
        FileReader reader = new FileReader(caminhoArquivo);
        JsonArray agendaArray = JsonParser.parseReader(reader).getAsJsonArray();
        reader.close();

        Scanner scanner = new Scanner(System.in);

        // Exibe todos os nomes e IDs para o usuário
        System.out.println("Lista de contatos:");
        for (JsonElement elemento : agendaArray) {
            JsonObject contato = elemento.getAsJsonObject();
            int id = contato.get("id").getAsInt();
            String nomeAluno = contato.get("nomeAluno").getAsString();
            boolean confirmado = contato.get("confirmado").getAsBoolean();
            System.out.println("ID: " + id + " - Nome: " + nomeAluno + " - confirmado: " + confirmado);
        }

        // Pede ao usuário para escolher um ID
        System.out.print("\nDigite o ID do contato para mudar o status para true: ");
        int idEscolhido = scanner.nextInt();

        // Procura o ID e altera o status para true
        boolean idEncontrado = false;
        for (JsonElement elemento : agendaArray) {
            JsonObject contato = elemento.getAsJsonObject();
            if (contato.get("id").getAsInt() == idEscolhido) {
                contato.add("confirmado", new JsonPrimitive(true));
                idEncontrado = true;
                System.out.println("Status do ID " + idEscolhido + " alterado para true.");
                break;
            }
        }

        if (!idEncontrado) {
            System.out.println("ID não encontrado.");
            return;
        }

        // Grava as mudanças no arquivo JSON
        FileWriter writer = new FileWriter(caminhoArquivo);
        writer.write(agendaArray.toString());
        writer.close();
    }
}

    

