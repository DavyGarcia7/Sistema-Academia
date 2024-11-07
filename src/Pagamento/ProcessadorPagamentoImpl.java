package Pagamento;

public class ProcessadorPagamentoImpl implements ProcessadorPagamento {

    @Override
    public boolean processarPagamento(Cartao cartao, double valor) {
        // Aqui vamos simular uma verificação de pagamento
        System.out.println("Processando pagamento de R$" + valor + " para o cartão " + cartao.getNumeroCartao());
        
        // Simulação: aceita pagamentos se o valor for menor que R$100, por exemplo
        if (valor <= 100) {
            System.out.println("Pagamento aprovado.");
            return true;
        } else {
            System.out.println("Pagamento recusado.");
            return false;
        }
    }
}
