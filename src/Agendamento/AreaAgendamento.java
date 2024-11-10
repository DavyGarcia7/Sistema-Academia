/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agendamento;

import java.util.Scanner;


public class AreaAgendamento {

    private static String[] args;
    public static void areaAgendamento() {
        Scanner scanner = new Scanner(System.in); // Mantenha o scanner aberto até o fim
        GerenciadorMensalidades gerenciadorMensalidades = new GerenciadorMensalidades(); // Instância de GerenciadorMensalidades
        AdicionarMensalidade adicionarMensalidade = new AdicionarMensalidade(gerenciadorMensalidades); // Passa o gerenciador para AdicionarMensalidade
        Agenda agenda = new Agenda(); // Cria uma nova instância da classe Agenda

        while (true) {
            
            System.out.println("\n=== Menu de Agendamento da Academia ===");
            System.out.println("1. Adicionar Mensalidade de Aluno");
            System.out.println("2. Consultar Mensalidades de Aluno");
            System.out.println("3. Confirmar Agendamento");
            System.out.println("4. Adicionar Agendamento");
            System.out.println("5. Listar Agendamentos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarMensalidade.adicionarMensalidadeAluno(); // Chama o método adicionarMensalidadeAluno
                    break;
                case 2:
                    adicionarMensalidade.consultarMensalidadesAluno(); // Chama o método consultarMensalidadesAluno
                    break;
                case 3:
                    //ConfirmarAgendamento.confirmar(args);
                    break;
                case 4:
                    agenda.agendar(); // Chama o método agendar
                    agenda.adicionarMensalidadeAula();
                    break;
                case 5:
                    ListarAgenda.lerAgenda(); // Chama o método de listar agendamentos
                    break;
                case 6:                   
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}




    


