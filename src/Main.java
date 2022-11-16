import java.util.Scanner;

public class Main {
    private static String menu(Scanner scanner) {
        System.out.println("\n---\nMENU\n" + "(C)adastrar Aluno\n" + "(E)xibir Aluno\n" + "(N)ovo Grupo\n"
                + "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" + "(O)lhaí quais Grupos o Aluno Tá.\n" +"(S)im, quero Fechar o Programa!\n" + "\n" + "Opção>");
        return scanner.next().toUpperCase();
    }

}
