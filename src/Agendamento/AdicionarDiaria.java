package Agendamento;

import java.util.List;
import java.util.Scanner;

public class AdicionarDiaria {
    private GerenciadorDiaria gerenciadorDiaria; // Gerenciador de diárias
    private Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados

    // Construtor que recebe o GerenciadorDiaria como parâmetro
    public AdicionarDiaria(GerenciadorDiaria gerenciadorDiaria) {
        this.gerenciadorDiaria = gerenciadorDiaria;
    }

    // Método para adicionar diária de um aluno
    public void adicionarDiariaAluno() {
        System.out.print("Digite o ID do aluno: ");
        int alunoId = scanner.nextInt();
        if (gerenciadorDiaria.verificarAlunoRegistrado(alunoId)) {
            System.out.print("Digite o valor da diária: ");
            double valor = scanner.nextDouble();
            gerenciadorDiaria.adicionarDiaria(alunoId, valor);
            System.out.println("Diária adicionada com sucesso.");
        } else {
            System.out.println("Aluno com ID " + alunoId + " não encontrado em pessoas.json.");
        }
    }

    // Método para consultar diárias de um aluno específico
    public void consultarDiariasAluno() {
        System.out.print("Digite o ID do aluno: ");
        int alunoId = scanner.nextInt();
        if (gerenciadorDiaria.verificarAlunoRegistrado(alunoId)) {
            List<DiariaDeAluno> diarias = gerenciadorDiaria.listarDiariaPorAluno(alunoId);
            if (diarias.isEmpty()) {
                System.out.println("Nenhuma diária encontrada para o aluno com ID: " + alunoId);
            } else {
                System.out.println("Diárias do aluno com ID " + alunoId + ":");
                for (DiariaDeAluno diaria : diarias) {
                    System.out.println(diaria); // Imprime diretamente com toString()
                }
            }
        } else {
            System.out.println("Aluno com ID " + alunoId + " não encontrado em pessoas.json.");
        }
    }
}
