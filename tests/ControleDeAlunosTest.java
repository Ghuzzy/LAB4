package tests;

import static org.junit.jupiter.api.Assertions.*;

import main.Aluno;
import main.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.ControleDeAlunos;

class ControleDeAlunosTest {
    private ControleDeAlunos controleDeAlunos;

    @BeforeEach
    void setUp(){
        controleDeAlunos = new ControleDeAlunos();
        controleDeAlunos.cadastraAluno( "200", "Lili Camposh","Computação");
        controleDeAlunos.cadastraAluno("202","Angela Ziegler","Medicina");
        controleDeAlunos.cadastraAluno("201","Torbjor Lindholm","Engenharia Mecânica");
    }

    @Test
    void testaCadastraAluno1(){
        Aluno aluno = new Aluno("250","Gabriel Reyes", "Computação");
        assertEquals("CADASTRO REALIZADO", controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação"));
        assertEquals(aluno, controleDeAlunos.getAluno("250"));
    }
    @Test
    void testaCadastraAluno2(){
        controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação");
        assertEquals("MATRÍCULA JÁ CADASTRADA!", controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação"));
    }
    @Test
    void testaCadastraGrupoSemRestricao(){
        assertEquals("CADASTRO REALIZADO", controleDeAlunos.cadastraGrupo("Programação Funcional"));
    }
    @Test
    void testaCadastraGrupoComRestricao(){
        assertEquals("CADASTRO REALIZADO", controleDeAlunos.cadastraGrupo("Objects", 10));
    }
    @Test
    void testaCadastraGrupoJaCadastrado(){
        controleDeAlunos.cadastraGrupo("Listas", 10);
        assertEquals("GRUPO JÁ CADASTRADO", controleDeAlunos.cadastraGrupo("Listas"));
    }

    @Test
    void testaCadastraGrupoSemNome(){
        try{
        controleDeAlunos.cadastraGrupo("");
        fail("Deveria retornar erro de illegal argument.");
        }catch(IllegalArgumentException e){
            assertEquals("O tema não pode ser nulo ou vazio", e.getMessage());
        }
    }
    @Test
    void testaAlocarAluno1(){
        controleDeAlunos.cadastraGrupo("Programação");
        Grupo grupo = controleDeAlunos.getGrupo("Programação");
        Aluno aluno200 = controleDeAlunos.getAluno("200");
        Aluno aluno202 = controleDeAlunos.getAluno("202");

        assertEquals("ALUNO ALOCADO!", controleDeAlunos.alocarAluno("200", "Programação"));
        assertTrue(grupo.alunoEmGrupo(aluno200));
        assertEquals("ALUNO ALOCADO!", controleDeAlunos.alocarAluno("202", "Programação"));
        assertTrue(grupo.alunoEmGrupo(aluno202));
    }
    @Test
    void testaAlocarAlunoJaAlocado(){
        controleDeAlunos.cadastraGrupo("Programação");
        controleDeAlunos.alocarAluno("200", "Programação");
        controleDeAlunos.alocarAluno("202", "Programação");
        Grupo grupo = controleDeAlunos.getGrupo("Programação");

        assertEquals("ALUNO ALOCADO!", controleDeAlunos.alocarAluno("200", "Programação"));
        assertEquals("ALUNO ALOCADO!", controleDeAlunos.alocarAluno("202", "Programação"));
        assertEquals(2, grupo.getAlunos().size());
    }

    @Test
    void testaAlocarAlunoInvalidos(){
        controleDeAlunos.cadastraGrupo("Programação OO");

        assertEquals("ALUNO NÃO CADASTRADO", controleDeAlunos.alocarAluno("400", "Programação OO"));
        assertEquals("GRUPO NÃO CADASTRADO", controleDeAlunos.alocarAluno("200", "Programação Funcional"));
        assertEquals("ALUNO E GRUPO NÃO CADASTRADOS", controleDeAlunos.alocarAluno("400", "Programação Funcional"));
    }

    @Test
    void testaAlocarAlunoEstouro(){
        controleDeAlunos.cadastraGrupo("Programação", 1);
        Grupo grupo = controleDeAlunos.getGrupo("Programação");
        Aluno aluno200 = controleDeAlunos.getAluno("200");
        Aluno aluno202 = controleDeAlunos.getAluno("202");

        assertEquals("ALUNO ALOCADO!", controleDeAlunos.alocarAluno("200", "Programação"));
        assertTrue(grupo.alunoEmGrupo(aluno200));
        assertEquals("ALUNO NÃO ALOCADO DEVIDO AO LIMITE DE TAMANHO!", controleDeAlunos.alocarAluno("202", "Programação"));
        assertFalse(grupo.alunoEmGrupo(aluno202));
    }

    @Test
    void testaPertinencia(){
        controleDeAlunos.cadastraGrupo("Listas");
        controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação");
        controleDeAlunos.alocarAluno("250", "Listas");

        assertEquals("ALUNO PERTENCE AO GRUPO", controleDeAlunos.pertinenteEmGrupo("Listas","250"));
        assertEquals("ALUNO NÃO PERTENCE AO GRUPO", controleDeAlunos.pertinenteEmGrupo("Listas","202"));
    }

    @Test
    void testaPertinenciaInvalidas(){
        controleDeAlunos.cadastraGrupo("Listas");
        controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação");

        assertEquals("GRUPO NÃO CADASTRADO", controleDeAlunos.pertinenteEmGrupo("Prog","250"));
        assertEquals("ALUNO NÃO CADASTRADO", controleDeAlunos.pertinenteEmGrupo("Listas","400"));
        assertEquals("ALUNO E GRUPO NÃO CADASTRADOS", controleDeAlunos.pertinenteEmGrupo("Prog","400"));
    }

    @Test
    void testaExibirGruposAlunoSGrupo(){
        assertEquals("",controleDeAlunos.exibeGrupos("202"));
    }

    @Test
    void testaExibirGruposAluno(){
        controleDeAlunos.cadastraGrupo("Listas", 10);
        controleDeAlunos.cadastraGrupo("Programação OO", 10);
        controleDeAlunos.cadastraAluno("250","Gabriel Reyes", "Computação");

        controleDeAlunos.alocarAluno("250", "Listas");
        controleDeAlunos.alocarAluno("250", "Programação OO");

        assertEquals("- Programação OO 1/10\n- Listas 1/10\n", controleDeAlunos.exibeGrupos("250"));
    }

    @Test
    void testaExibirInfoAlunoInvalido(){
        assertEquals("ALUNO NÃO CADASTRADO.", controleDeAlunos.exibeInfoAluno("169"));
    }
    @Test
    void testaExibirInfoAluno(){
        controleDeAlunos.cadastraAluno("169","George", "arquitetura");
        assertEquals("169 - George - arquitetura", controleDeAlunos.exibeInfoAluno("169"));
    }
    @Test
    void testaRegistrarRespostaAlunoInv(){
        assertEquals("ALUNO NÃO CADASTRADO.", controleDeAlunos.registrarAlunoQRespondeu("135"));
    }
    @Test
    void testaRegistrarRespostaAluno(){
        assertEquals("ALUNO REGISTRADO!", controleDeAlunos.registrarAlunoQRespondeu("202"));
    }
    @Test
    void testaImprimeHistoricoRespostas(){
        controleDeAlunos.registrarAlunoQRespondeu("200");
        controleDeAlunos.registrarAlunoQRespondeu("202");
        controleDeAlunos.registrarAlunoQRespondeu("200");
        String resp = "1. 200 - Lili Camposh - Computação\n2. 202 - Angela Ziegler - Medicina\n3. 200 - Lili Camposh - Computação\n";
        assertEquals(resp, controleDeAlunos.imprimirRegistroDeRespostas());
    }
}
