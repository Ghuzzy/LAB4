package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Aluno;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {
    Aluno aluno1;
    Aluno aluno2;

    @Test
    void testConstrutor1(){
        try {
            Aluno aluno = new Aluno("", "joao","engenharia");
            fail("Deveria ter lancado excecao de cadastro invalido");
        }catch(IllegalArgumentException e) {
            assertEquals("Matricula não deve ser nula ou vazia.", e.getMessage());
        }
    }
    @Test
    void testConstrutor2(){
        try {
            Aluno aluno = new Aluno("300", "","engenharia");
            fail("Deveria ter lancado excecao de cadastro invalido");
        }catch(IllegalArgumentException e) {
            assertEquals("Nome não deve ser nulo ou vazio.", e.getMessage());
        }
    }

    @Test
    void testConstrutor3(){
        try {
            Aluno aluno = new Aluno("300", "joao","");
            fail("Deveria ter lancado excecao de cadastro invalido");
        }catch(IllegalArgumentException e) {
            assertEquals("Curso não deve ser nulo ou vazio.", e.getMessage());
        }
    }

    @Test
    void testConstrutor4(){
        Aluno aluno = new Aluno("300", "joao","engenharia");
        assertEquals("300",aluno.getMatricula() );
        assertEquals("joao", aluno.getNome());
        assertEquals("engenharia", aluno.getCurso());
    }


    @Test
    void testEquals1() {
        aluno1 = new Aluno("111", "pedro", "arquitetura");
        aluno2 = new Aluno("121", "pedro", "arquitetura");
        assertEquals(false ,aluno1.equals(aluno2));
    }

    @Test
    void testEquals2() {
        aluno1 = new Aluno("111", "pedro", "arquitetura");
        aluno2 = new Aluno("111", "joao", "engenharia");
        assertEquals(true ,aluno1.equals(aluno2));
    }
    @Test
    void testToString() {
        aluno1 = new Aluno("111", "pedro", "arquitetura");
        assertEquals("111 - pedro - arquitetura", aluno1.toString());
    }
}