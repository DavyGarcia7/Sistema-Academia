package Registrar_nova_Pessoa;

import Agendamento.GerenciadorMensalidades;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Catraca {
    private List<SCadastroAluno> alunos;
    private List<SCadastrofuncionario> funcionarios;
    private GerenciadorMensalidades gerenciadorMensalidades;
    private Set<Integer> pessoasPresentes; // IDs de pessoas atualmente na academia

    public Catraca(List<SCadastroAluno> alunos1, List<SCadastrofuncionario> funcionarios1, GerenciadorMensalidades gerenciadorMensalidades) {
        this.alunos = carregarAlunos(); // Carrega os alunos de pessoas.json
        this.funcionarios = carregarFuncionarios(); // Carrega os funcionários de funcionarios.json
        this.gerenciadorMensalidades = gerenciadorMensalidades;
        this.pessoasPresentes = new HashSet<>(); // Usamos um Set para evitar duplicidade
    }

    // Registra a entrada de uma pessoa e a adiciona à lista de presentes
    public void registrarEntrada(int id, String tipoPessoa) {
        if (pessoasPresentes.contains(id)) {
            System.out.println("A pessoa com ID " + id + " já está dentro da academia.");
            return;
        }

        if (tipoPessoa.equalsIgnoreCase("aluno")) {
            SCadastroAluno aluno = buscarAluno(id);
            if (aluno != null) {
                pessoasPresentes.add(id);
                System.out.println("Entrada liberada para o aluno " + aluno.getNome() + ". Pessoas na academia: " + pessoasPresentes.size());
            } else if (aluno == null) {
                System.out.println("Aluno não encontrado.");
            }
        } else if (tipoPessoa.equalsIgnoreCase("funcionario")) {
            SCadastrofuncionario funcionario = buscarFuncionario(id);
            if (funcionario != null) {
                pessoasPresentes.add(id);
                System.out.println("Entrada liberada para o funcionário " + funcionario.getNome() + ". Pessoas na academia: " + pessoasPresentes.size());
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }

    // Registra a saída de uma pessoa e a remove da lista de presentes
    public void registrarSaida(int id) {
        if (pessoasPresentes.contains(id)) {
            pessoasPresentes.remove(id);
            System.out.println("Saída registrada para a pessoa com ID " + id + ". Pessoas na academia: " + pessoasPresentes.size());
        } else {
            System.out.println("A pessoa com ID " + id + " não está presente na academia.");
        }
    }

    // Método para exibir o número de pessoas presentes na academia
    public int verificarOcupacaoAtual() {
        System.out.println("Número de pessoas atualmente na academia: " + pessoasPresentes.size());
        return pessoasPresentes.size();
    }

    // Método auxiliar para buscar um aluno pelo ID
    private SCadastroAluno buscarAluno(int id) {
        for (SCadastroAluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    // Método auxiliar para buscar um funcionário pelo ID
    private SCadastrofuncionario buscarFuncionario(int id) {
        for (SCadastrofuncionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    // Carrega a lista de alunos do arquivo JSON pessoas.json
    private List<SCadastroAluno> carregarAlunos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.json"))) {
            Type listType = new TypeToken<List<SCadastroAluno>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return null;
        }
    }

    // Carrega a lista de funcionários do arquivo JSON funcionarios.json
    private List<SCadastrofuncionario> carregarFuncionarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("funcionarios.json"))) {
            Type listType = new TypeToken<List<SCadastrofuncionario>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
            return null;
        }
    }
}
