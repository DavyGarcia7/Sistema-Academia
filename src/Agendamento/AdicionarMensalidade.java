    package Agendamento;

    import java.util.List;
    import java.util.Scanner;

    public class AdicionarMensalidade {
        private GerenciadorMensalidades gerenciadorMensalidades; // Adiciona o gerenciador de mensalidades
        private Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados

        // Construtor que recebe o GerenciadorMensalidades como parâmetro
        public AdicionarMensalidade(GerenciadorMensalidades gerenciadorMensalidades) {
            this.gerenciadorMensalidades = gerenciadorMensalidades;
        }

        // Método para adicionar mensalidade de um aluno
        public void adicionarMensalidadeAluno() {
            System.out.print("Digite o ID do aluno: ");
            int alunoId = scanner.nextInt();
            if (gerenciadorMensalidades.verificarAlunoRegistrado(alunoId)) {
                System.out.print("Digite o valor da mensalidade: ");
                double valor = scanner.nextDouble();
                gerenciadorMensalidades.adicionarMensalidade(alunoId, valor);
                System.out.println("Mensalidade adicionada com sucesso.");
            } else {
                System.out.println("Aluno com ID " + alunoId + " não encontrado em pessoas.json.");
            }
        }

        // Método para consultar mensalidades de um aluno específico
        public void consultarMensalidadesAluno() {
            System.out.print("Digite o ID do aluno: ");
            int alunoId = scanner.nextInt();
            if (gerenciadorMensalidades.verificarAlunoRegistrado(alunoId)) {
                List<MensalidadeAluno> mensalidades = gerenciadorMensalidades.listarMensalidadesPorAluno(alunoId);
                if (mensalidades.isEmpty()) {
                    System.out.println("Nenhuma mensalidade encontrada para o aluno com ID: " + alunoId);
                } else {
                    System.out.println("Mensalidades do aluno com ID " + alunoId + ":");
                    for (MensalidadeAluno mensalidade : mensalidades) {
                        System.out.println(mensalidade); // Imprimindo diretamente com toString()
                    }
                }
            } else {
                System.out.println("Aluno com ID " + alunoId + " não encontrado em pessoas.json.");
            }
        }
    }
    