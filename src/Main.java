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
                if(option.equals("A")) {
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
        String nome = scanner.nextLine();
        System.out.print("\nCurso: ");
        String curso =  scanner.nextLine();
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
        String tema = scanner.nextLine();
        System.out.print("\nTamanho: ");
        int tamanho = scanner.nextInt();
        System.out.println(controleAlunos.cadastraGrupo(tema, tamanho));
    }

    private static void alocarAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("Matricula: ");
        String matricula = scanner.next();
        System.out.println("Grupo: ");
        String grupo = scanner. nextLine();
        System.out.println(controleAlunos.alocarAluno(matricula,grupo));
    }

    private static void pertinenciaAGrupos(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.println("Aluno: ");
        String aluno = scanner.next();
        System.out.println(controleAlunos.pertinenteEmGrupo(grupo,aluno));
    }

    private static void exibeGruposDoAluno(ControleDeAlunos controleAlunos, Scanner scanner) {
        System.out.println("Aluno: ");
        String matricula = scanner.next();
        controleAlunos.exibeGrupos(matricula);
    }

    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }


}
