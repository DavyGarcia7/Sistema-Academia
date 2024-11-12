package Agendamento;

import Pagamento.ProcessadorPagamento;
import Pagamento.ProcessadorPagamentoImpl;
import java.util.Scanner;

public class AreaAgendamento {

    private final ProcessadorPagamento processadorPagamento;

    // Construtor que aceita um ProcessadorPagamento como parâmetro
    public AreaAgendamento(ProcessadorPagamento processadorPagamento) {
        this.processadorPagamento = processadorPagamento;
    }

    public static void areaAgendamento() {
        Scanner scanner = new Scanner(System.in);
        
        // Instanciação de classes necessárias com `processadorPagamento`
        ProcessadorPagamento processadorPagamento = new ProcessadorPagamentoImpl();
        GerenciadorMensalidades gerenciadorMensalidades = new GerenciadorMensalidades(processadorPagamento);
        GerenciadorDiaria gerenciadorDiaria = new GerenciadorDiaria(processadorPagamento);
        
        AdicionarMensalidade adicionarMensalidade = new AdicionarMensalidade(gerenciadorMensalidades);
        AdicionarDiaria adicionarDiaria = new AdicionarDiaria(gerenciadorDiaria);
        Agenda agenda = new Agenda();

        while (true) {
            System.out.println("\n=== Menu de Agendamento da Academia ===");
            System.out.println("1. Adicionar Mensalidade de Aluno");
            System.out.println("2. Consultar Mensalidades de Aluno");
            System.out.println("3. Adicionar Diaria de Aluno");
            System.out.println("4. Consultar Diaria de Aluno");
            System.out.println("5. Confirmar Agendamento");
            System.out.println("6. Cancelar Agendamento");
            System.out.println("7. Adicionar Agendamento");
            System.out.println("8. Listar Agendamentos");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarMensalidade.adicionarMensalidadeAluno();
                    break;
                case 2:
                    adicionarMensalidade.consultarMensalidadesAluno();
                    break;
                case 3:
                    adicionarDiaria.adicionarDiariaAluno();
                    break;
                case 4:
                    adicionarDiaria.consultarDiariasAluno();
                    break;
                case 5:
                    agenda.confirmarAgendamento();  // Chama o método de confirmação de agendamento
                    break;
                case 6:
                    agenda.cancelarAgendamento();   // Chama o método de cancelamento de agendamento
                    break;
                case 7:
                    agenda.agendar();
                    agenda.adicionarMensalidadeAula();
                    break;
                case 8:
                    System.out.println(agenda); // Exibe todos os agendamentos
                    break;
                case 9:
                    System.out.println("Saindo do sistema de agendamento.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
