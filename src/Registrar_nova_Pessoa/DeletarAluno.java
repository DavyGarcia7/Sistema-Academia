    package Registrar_nova_Pessoa;

    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;
    import com.google.gson.reflect.TypeToken;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.lang.reflect.Type;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Iterator;

    public class DeletarAluno {
        private static final String NOME_ARQUIVO = "pessoas.json";
        private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public static void deletarAlunoPorId(int id) {
            List<Pessoa> listaAlunos = lerArquivoJson();

            if (listaAlunos != null) {
                // Usando um iterador para evitar problemas de concorrência ao remover elementos da lista
                Iterator<Pessoa> iterator = listaAlunos.iterator();
                boolean alunoRemovido = false;

                while (iterator.hasNext()) {
                    Pessoa aluno = iterator.next();
                    if (aluno.getId() == id) {  // Comparação com o ID do aluno
                        iterator.remove();
                        alunoRemovido = true;
                        break;
                    }
                }

                if (alunoRemovido) {
                    salvarArquivoJson(listaAlunos);
                    System.out.println("Aluno com ID " + id + " deletado com sucesso.");
                } else {
                    System.out.println("Aluno com ID " + id + " não encontrado.");
                }
            } else {
                System.out.println("Erro ao carregar a lista de alunos.");
            }
        }

        private static List<Pessoa> lerArquivoJson() {
            try (FileReader reader = new FileReader(NOME_ARQUIVO)) {
                Type tipoListaPessoa = new TypeToken<ArrayList<Pessoa>>() {}.getType();
                return gson.fromJson(reader, tipoListaPessoa);
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
            return null;
        }

        private static void salvarArquivoJson(List<Pessoa> listaAlunos) {
            try (FileWriter writer = new FileWriter(NOME_ARQUIVO)) {
                gson.toJson(listaAlunos, writer);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        }
    }




