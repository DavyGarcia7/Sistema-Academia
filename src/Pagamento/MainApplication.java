package Pagamento;

import Financeiro.AreaFinancas;

public class MainApplication {

    public static void main(String[] args) {
        // Criando uma instância do ProcessadorPagamentoImpl
        ProcessadorPagamento processadorPagamento = new ProcessadorPagamentoImpl();

        // Criando uma instância da AreaFinancas com o processador de pagamento
        AreaFinancas areaFinancas = new AreaFinancas(processadorPagamento);

        // Exemplo de uso do processador
        System.out.println(processadorPagamento.toString());
    }
}
