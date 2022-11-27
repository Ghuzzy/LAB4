package main;

import com.sun.jdi.IntegerValue;
import utils.Validador;

import java.util.HashSet;
import java.util.Objects;

public class Grupo {

    /**
     *  O tema do grupo, o qual identifica sua unicidade.
     */
    private final String tema;

    /**
     * Hashset que armazena os alunos que participam deste grupo.
     */
    private final HashSet<Aluno> alunos;

    /**
     * Tamanho limite do grupo
     */
    private int tamanho;

    /**
     * Constrói um grupo recebendo tema do grupo.
     * Caso receba parametros inválidos, deve retornar erro de argumento illegal.
     *
     * @param tema: tema do grupo
     */
    public Grupo(String tema){
        Validador validador = new Validador();
        validador.validaString(tema, "O tema não pode ser nulo ou vazio");
        this.tema = tema;
        this.alunos = new HashSet<>();
        this.tamanho = Integer.MAX_VALUE;
    }

    /**
     * Constrói um grupo recebendo tema e o tamanho do grupo.
     * Caso receba parametros inválidos, deve retornar erro de argumento illegal.
     *
     * @param tema: tema do grupo
     * @param tamanho: tamanho limite do grupo
     */
    public Grupo(String tema, int tamanho){
        Validador validador = new Validador();
        validador.validaString(tema, "O tema não pode ser nulo ou vazio");
        this.tema = tema;
        this.alunos = new HashSet<>();
        this.tamanho = tamanho;
    }

    /**
     * Sobrescreve o metodo equals de Object, o qual passa a comparar a igualdade do objeto a partir do tema.
     *
     * @param o
     * @return boolean: Retorna se os objetos são diferentes ou iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return tema.equals(grupo.tema);
    }

    /**
     * Sobrescreve o hashcode de Object.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(tema);
    }

    /**
     * Recebe um objeto do tipo main.Aluno e verifica se este faz parte deste grupo.
     *
     * @param alunoACadastrar
     * @return boolean: Retorna se o aluno está ou não no grupo.
     */
    public boolean alunoEmGrupo(Aluno alunoACadastrar) {
        if(this.alunos.contains(alunoACadastrar)){
                    return true;
            }
        return false;
    }

    public HashSet<Aluno> getAlunos() {
        return this.alunos;
    }

    /**
     * Adiciona um novo aluno ao grupo.
     *
     * @param aluno: aluno a ser cadastrado.
     */
    public String adicionaAluno(Aluno aluno) {
        String resp = "";
        if((this.alunos.size() < this.tamanho)) {
            this.alunos.add(aluno);
            resp = "ALUNO ALOCADO!";
        }else {
            resp = "ALUNO NÃO ALOCADO DEVIDO AO LIMITE DE TAMANHO!";
        }
        return resp;
    }

    /**
     * Sobrescreve o metodo toString() de Object.
     *
     * @return String: - tema vagasOcupadas/vagas
     */
    @Override
    public String toString() {
        String resp = "indefinido";
        if(this.tamanho < Integer.MAX_VALUE){
            resp = String.valueOf(this.tamanho);
        }
        return "- " + this.tema + " " + this.alunos.size() + "/" + resp;
    }
}
