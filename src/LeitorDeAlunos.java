import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeAlunos {

    private static final int COLUNA_MATRICULA = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_CURSO = 2;


    /**
     * Lê informações de alunos de um arquivo CSV e os cadastra no sistema controle de alunos.
     *
     * @param arquivoAlunos    Caminho para arquivo contendo alunos.
     * @param controleDeAlunos O sistema de alunos a manipular.
     * @return O número de alunos adicionados.
     * @throws IOException           Caso não tenhamos permissão de ler o arquivo.
     * @throws FileNotFoundException Caso o arquivo não exista.
     */
    public int carregaContatos(String arquivoAlunos, ControleDeAlunos controleDeAlunos) throws FileNotFoundException, IOException {
        int carregados = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoAlunos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                carregados += 1;
                if (carregados == 1) {
                    // pulamos a primeira linha, o cabeçalho
                    continue;
                }
                String[] campos = linha.split(",");
                processaLinhaCsvAlunos(campos, controleDeAlunos);
            }
        }

        return carregados;
    }



    /**
     * Coloca na agenda os dados de uma linha do arquivo de agenda inicial.
     *
     * @param campos           As informações lidas do csv.
     * @param controleDeAlunos o sistema de alunos a manipular.
     */
    private void processaLinhaCsvAlunos(String[] campos, ControleDeAlunos controleDeAlunos) {
        String matricula = campos[COLUNA_MATRICULA].trim();
        String nome = campos[COLUNA_NOME].trim();
        String curso = campos[COLUNA_CURSO].trim();

        controleDeAlunos.cadastraAluno(matricula, nome, curso);
    }

}