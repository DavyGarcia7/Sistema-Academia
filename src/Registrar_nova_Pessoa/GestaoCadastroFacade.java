package Registrar_nova_Pessoa;

import Pagamento.ProcessadorPagamento;
import java.util.Scanner;

public class GestaoCadastroFacade {

    private final ProcessadorPagamento processadorPagamento;

    public GestaoCadastroFacade(ProcessadorPagamento processadorPagamento) {
        this.processadorPagamento = processadorPagamento;
    }

    public void cadastrarAluno() {
        SCadastroAluno.CadastroA(processadorPagamento);
    }

    public void cadastrarFuncionario() {
        SCadastrofuncionario.CadastroF();
    }

    public void deletarAluno(int id) {
        DeletarAluno.deletarAlunoPorId(id);
    }

    public void deletarFuncionario(int id) {
        DeletarFuncionario.deletarFuncionarioPorId(id);
    }
}
