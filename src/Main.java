import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControleDeAlunos controleAlunos = new ControleDeAlunos();
        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, controleAlunos, scanner);
        }

    }

    private static String menu(Scanner scanner) {
        System.out.println("\n---\nMENU\n" + "(C)adastrar Aluno\n" + "(E)xibir Aluno\n" + "(N)ovo Grupo\n"
                + "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" + "(O)lhaí quais Grupos o Aluno Tá.\n" +"(S)im, quero Fechar o Programa!\n" + "\n" + "Opção>");
        return scanner.next().toUpperCase();
    }

    private static void comando(String opcao, ControleDeAlunos controleAlunos, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraAluno(controleAlunos, scanner);
                break;
            case "E":
                exibeAluno(controleAlunos, scanner);
                break;
            case "N":
                cadastraGrupo(controleAlunos, scanner);
                break;
            case "A":
                System.out.println("(A)locar Aluno ou (P)ertinência a Grupo?");
                String option = scanner.next();
                if(option.toUpperCase(Locale.ROOT).equals("A")) {
                    alocarAluno(controleAlunos, scanner);
                }else{
                    pertinenciaAGrupos(controleAlunos, scanner);
                }
                break;
            case "O":
                exibeGruposDoAluno(controleAlunos, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }

    private static void cadastraAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.print("\nMatricula: ");
        String matricula = scanner.next();
        System.out.print("\nNome: ");
        String nome = scanner.next();
        System.out.print("\nCurso: ");
        String curso =  scanner.next();
        System.out.println(controleAlunos.cadastraAluno(matricula,nome,curso));
    }


    private static void exibeAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.print("\nMatricula: ");
        String matricula = scanner.next();
        String infoAluno = controleAlunos.exibeInfoAluno(matricula);
        System.out.println(infoAluno);

    }

    private static void cadastraGrupo(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.print("\nGrupo: ");
        String tema = scanner.next();
        System.out.print("\nTamanho: ");
        int tamanho = scanner.nextInt();
        System.out.println(controleAlunos.cadastraGrupo(tema, tamanho));
    }

    private static void alocarAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nMatricula: ");
        String matricula = scanner.next();
        System.out.println("\nGrupo: ");
        String grupo = scanner. next();
        System.out.println(controleAlunos.alocarAluno(matricula,grupo));
    }

    private static void pertinenciaAGrupos(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nGrupo: ");
        String grupo = scanner.next();
        System.out.println("\nAluno: ");
        String aluno = scanner.next();
        System.out.println(controleAlunos.pertinenteEmGrupo(grupo,aluno));
    }

    private static void exibeGruposDoAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nAluno: ");
        String matricula = scanner.next();
        controleAlunos.exibeGrupos(matricula);
    }

    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }



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
}
