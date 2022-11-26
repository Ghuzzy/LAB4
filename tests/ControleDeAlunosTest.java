package tests;

import static org.junit.jupiter.api.Assertions.*;

import main.Aluno;
import main.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.ControleDeAlunos;

class ControleDeAlunosTest {
    private Grupo grupo1;
    private Aluno aluno1;
    private Aluno aluno2;
    private Aluno aluno3;
    private Aluno aluno4;

    @BeforeEach
    void setUp(){
        aluno1 = new Aluno( "250" ,"Gabriel Reyes","Computação");
        aluno2 = new Aluno( "200", "Lili Camposh","Computação");
        aluno3 = new Aluno("202","Angela Ziegler","Medicina");
        aluno4 = new Aluno("201","Torbjor Lindholm","Engenharia Mecânica");



    }


}
