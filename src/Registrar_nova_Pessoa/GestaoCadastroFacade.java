package Registrar_nova_Pessoa;

import Pagamento.Cartao;
import Pagamento.ProcessadorPagamento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

    public void editarAluno(int id) {
        EditarAlunos.editarAluno(id);
    }

    public void editarFuncionario(int id) {
        EditarFuncionario.editarFuncionarios(id);
    }

    // Sobrescrevendo o método toString() para a classe GestaoCadastroFacade
    @Override
    public String toString() {
        return "GestaoCadastroFacade{" +
                "processadorPagamento=" + processadorPagamento +
                '}';
    }
}
