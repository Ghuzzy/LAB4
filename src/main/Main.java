package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControleDeAlunos controleAlunos = new ControleDeAlunos();

        System.out.println("Carregando cadastros de alunos inicial");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaCadastros("src/alunos_iniciais.csv", controleAlunos);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, controleAlunos, scanner);
        }

    }

    private static String menu(Scanner scanner) {
        System.out.println("\n---\nMENU\n" + "(C)adastrar main.Aluno\n" + "(E)xibir main.Aluno\n" + "(N)ovo main.Grupo\n"
                + "(A)locar main.Aluno no main.Grupo e Verificar pertinência a Grupos\n" + "(O)lhaí quais Grupos o main.Aluno Tá.\n" +"(S)im, quero Fechar o Programa!\n" + "\n" + "Opção>");
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
                System.out.println("(A)locar main.Aluno ou (P)ertinência a main.Grupo?");
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
        System.out.print("\nmain.Grupo: ");
        String tema = scanner.next();
        System.out.print("\nTamanho: ");
        int tamanho = scanner.nextInt();
        System.out.println(controleAlunos.cadastraGrupo(tema, tamanho));
    }

    private static void alocarAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nMatricula: ");
        String matricula = scanner.next();
        System.out.println("\nmain.Grupo: ");
        String grupo = scanner. next();
        System.out.println(controleAlunos.alocarAluno(matricula,grupo));
    }

    private static void pertinenciaAGrupos(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nmain.Grupo: ");
        String grupo = scanner.next();
        System.out.println("\nmain.Aluno: ");
        String aluno = scanner.next();
        System.out.println(controleAlunos.pertinenteEmGrupo(grupo,aluno));
    }

    private static void exibeGruposDoAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("\nmain.Aluno: ");
        String matricula = scanner.next();
        controleAlunos.exibeGrupos(matricula);
    }

    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    private static void carregaCadastros(String arquivoContatos, ControleDeAlunos controleAlunos) throws FileNotFoundException, IOException {
        LeitorDeAlunos leitor = new LeitorDeAlunos();

        int carregados = leitor.carregaContatos(arquivoContatos, controleAlunos);
        System.out.println("Carregamos " + carregados + " registros.");
    }


}
