package projetoacademia;

import Financeiro.FinanceiroFacade;
import Agendamento.AreaAgendamento;
import Loja.AreaLoja;
import Registrar_nova_Pessoa.AreaLogin;
import Registrar_nova_Pessoa.SCadastroAluno;
import Registrar_nova_Pessoa.SCadastrofuncionario;
import Financeiro.AreaFinancas;
import Financeiro.AutenticacaoAdministrador;
import Pagamento.ProcessadorPagamento;
import Pagamento.ProcessadorPagamentoImpl;
import Agendamento.GerenciadorMensalidades;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciar o processador de pagamento uma única vez
        ProcessadorPagamento processadorPagamento = new ProcessadorPagamentoImpl();

        // Autenticação e módulos dependentes
        AutenticacaoAdministrador autenticacao = new AutenticacaoAdministrador();
        Scanner scanner = new Scanner(System.in);

        // Carregar alunos e funcionários
        List<SCadastroAluno> alunos = SCadastroAluno.carregarAlunos();
        List<SCadastrofuncionario> funcionarios = SCadastrofuncionario.carregarFuncionarios();

        // Instanciando o GerenciadorMensalidades para ser compartilhado pela catraca e financeiro
        GerenciadorMensalidades gerenciadorMensalidades = new GerenciadorMensalidades(processadorPagamento);

        // Instanciando diferentes áreas da academia
        AreaLoja areaLoja = new AreaLoja();
        FinanceiroFacade financeiroFacade = new FinanceiroFacade(processadorPagamento);
        
        // Criar a área de login e registrar a catraca
        AreaLogin areaLogin = new AreaLogin(processadorPagamento, alunos, funcionarios, gerenciadorMensalidades);
        
        AreaFinancas areaFinancas = new AreaFinancas(processadorPagamento); 
        AreaAgendamento areaAgendamento = new AreaAgendamento(processadorPagamento);

        // Loop principal do sistema
        while (true) {
            System.out.println("=== Academia do Vale ===");
            System.out.println("1. Registrar nova Pessoa");
            System.out.println("2. Gerenciamento de aulas");
            System.out.println("3. Loja");
            System.out.println("4. Finanças");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    areaLogin.iniciar(); // Chama o método iniciar da classe AreaLogin
                    break;

                case 2:
                    areaAgendamento.areaAgendamento(); // Chama o método iniciar da classe AreaAgendamento
                    break;

                case 3:
                    areaLoja.iniciar(); // Chama o método iniciar da classe AreaLoja
                    break;

                case 4:
                    areaFinancas.iniciar(); // Chama o método iniciar da classe AreaFinancas
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
