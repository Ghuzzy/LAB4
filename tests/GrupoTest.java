package tests;

import static org.junit.jupiter.api.Assertions.*;

import main.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Aluno;

class GrupoTest {
    private Grupo grupo1;
    private Aluno aluno1;
    private Aluno aluno2;
    private Aluno aluno3;
    private Aluno aluno4;

    @Test
    void testaConstrutor1(){
        Grupo grupo = new Grupo("classes");
        assertEquals(grupo.toString(), "- classes 0/indefinido");
    }
    @Test
    void testaConstrutor2(){
        Grupo grupo = new Grupo("classes", 10);
        assertEquals(grupo.toString(), "- classes 0/10");
    }
    @Test
    void testaConstrutorInvalido1(){
        try{
            Grupo grupo = new Grupo(null);
            fail("Deveria retornar cadastro inválido");
        }catch(NullPointerException e){
            assertEquals("O tema não pode ser nulo ou vazio", e.getMessage());
        }
    }
    @Test
    void testaConstrutorInvalido2(){
        try{
            Grupo grupo = new Grupo("");
            fail("Deveria retornar cadastro inválido");
        }catch(IllegalArgumentException e){
            assertEquals("O tema não pode ser nulo ou vazio", e.getMessage());
        }
    }
    @BeforeEach
    void setUp(){
        grupo1 = new Grupo("classes", 3);
        aluno1 = new Aluno( "250" ,"Gabriel Reyes","Computação");
        aluno2 = new Aluno( "200", "Lili Camposh","Computação");
        aluno3 = new Aluno("202","Angela Ziegler","Medicina");
        aluno4 = new Aluno("201","Torbjor Lindholm","Engenharia Mecânica");
    }
    @Test
    void testaAdicionaAluno(){
        assertEquals(0, grupo1.getAlunos().size(),"" );
        grupo1.adicionaAluno(aluno1);
        assertEquals(1, grupo1.getAlunos().size(),"" );
    }
    @Test
    void testaAdicionaAlunoEstouro(){
        grupo1.adicionaAluno(aluno1);
        grupo1.adicionaAluno(aluno2);
        grupo1.adicionaAluno(aluno3);
        grupo1.adicionaAluno(aluno4);
        assertEquals(3, grupo1.getAlunos().size(), "");
    }
    @Test
    void testaAlunoEmGrupo(){
        grupo1.adicionaAluno(aluno1);
        assertEquals(true, grupo1.alunoEmGrupo(aluno1), "Deve retornar que aluno está no grupo.");
    }

    @Test
    void testaAlunoEmGrupo2(){
        assertEquals(false, grupo1.alunoEmGrupo(aluno1), "Deve retornar que aluno não está no grupo");
    }

    @Test
    void testaEquals1(){
        Grupo grupo = new Grupo("classes", 15);
        assertEquals(true, grupo.equals(grupo1), "Deve retornar verdadeiro pois os temas são iguais");
    }
    @Test
    void testaEquals2(){
        Grupo grupo = new Grupo("herança", 15);
        assertEquals(false, grupo.equals(grupo1), "Deve retornar false pois os temas não são iguais");
    }
    @Test
    void testaToStringGrupo1(){
        Grupo grupo = new Grupo("classes");
        assertEquals("- classes 0/indefinido", grupo.toString(), "Deve retornar no formato - tema ocupados/livres");
        grupo.adicionaAluno(aluno1);
        assertEquals("- classes 1/indefinido", grupo.toString(), "Deve retornar no formato - tema ocupados/livres");
    }
    @Test
    void testaToStringGrupo2(){
        Grupo grupo = new Grupo("classes", 10);
        assertEquals("- classes 0/10", grupo.toString(), "Deve retornar no formato - tema ocupados/livres");
        grupo.adicionaAluno(aluno1);
        assertEquals("- classes 1/10", grupo.toString(), "Deve retornar no formato - tema ocupados/livres");
    }
}
