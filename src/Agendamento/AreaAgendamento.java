/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agendamento;

import java.util.Scanner;

public class AreaAgendamento {
    public static void areaAgendamento() {
        Scanner scanner = new Scanner(System.in); // Mantenha o scanner aberto até o fim
        Agenda agenda = new Agenda(); // Cria uma nova instância da classe Agenda
    

        while (true) {
           
            System.out.println("\n=== Menu de Agendamento da Academia ===");
            System.out.println("1. Adicionar Agendamento");
            System.out.println("2. Listar Agendamentos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    agenda.agendar(); // Chama o método agendar
                    break;
                case 2:
                    ListarAgenda.lerAgenda();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}


    


