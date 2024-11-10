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
        // Implementação correta do cadastro de funcionário
        SCadastrofuncionario.CadastroF(); // Chama o método de cadastro de funcionário
    }

    public void deletarAluno(int id) {
        DeletarAluno.deletarAlunoPorId(id);
    }

    public void deletarFuncionario(int id) {
        DeletarFuncionario.deletarFuncionarioPorId(id);
    }

    public void editarAluno(String id) {
        EditarAlunos.editarAluno();
    }

    public void editarFuncionario(String id) {
        EditarFuncionario.editarFuncionarios();
    }
}