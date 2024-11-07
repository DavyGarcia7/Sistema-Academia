package projetoacademia;

import Agendamento.AreaAgendamento;
import Loja.AreaLoja;
import Registrar_nova_Pessoa.AreaLogin;
import Financeiro.AreaFinancas;
import Pagamento.ProcessadorPagamentoImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        AreaLoja areaLoja = new AreaLoja();
          ProcessadorPagamentoImpl processadorPagamento = new ProcessadorPagamentoImpl();
        AreaFinancas areaFinancas = new AreaFinancas(processadorPagamento);
        AreaLogin areaLogin = new AreaLogin(processadorPagamento);

        
        while (true) {            
            System.out.println("=== Academia do Vale ===");
            System.out.println("1. Registrar nova Pessoa");
            System.out.println("2. Gerenciamento de aulas");
            System.out.println("3. Lojas");
            System.out.println("4. Finanças");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    areaLogin.iniciar(); // Chama o método CadastroA da classe SCadastroAluno
                    break;

                case 2:
                    AreaAgendamento.areaAgendamento();
                    break;

                case 3: 
                    areaLoja.iniciar();
                    break;
                    
                case 4: 
                    areaFinancas.iniciar();
                    break;
                                   
                case 5: 
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    System.exit(0); // Encerra o programa
                    break;
                    
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }       
    }
}




