package Pagamento;

import Financeiro.AreaFinancas;
import Pagamento.ProcessadorPagamentoImpl;

public interface ProcessadorPagamento {
    boolean processarPagamento(Cartao cartao, double valor);
    ProcessadorPagamento processadorPagamento = new ProcessadorPagamentoImpl();
AreaFinancas areaFinancas = new AreaFinancas(processadorPagamento);

}
