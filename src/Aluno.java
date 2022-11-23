import utils.Validador;

import java.util.Objects;

public class Aluno {

    /**
     *  Matricula do aluno.
     */
    private String matricula;

    /**
     *  Nome do aluno.
     */
    private String nome;

    /**
     *  Curso do aluno.
     */
    private String curso;

    /**
     *  Validador de strings, para retornar erro em casos de receber parametros inválidos.
     */
    private Validador validador = new Validador();

    /**
     *  Constrói um objeto do tipo Aluno e recebe como parametros matricula, nome e curso do aluno
     *  Caso receba parametros inválidos deve retornar erro de argumento illegal.
     *
     * @param matricula: Representa o numero de matricula do aluno, na qual identifica sua unicidade.
     * @param nome: Nome completo do aluno.
     * @param curso: Curso no qual o aluno está matriculado.
     */
    public Aluno(String matricula, String nome, String curso){
        this.validador.validaString(matricula, "Matricula não deve ser nula ou vazia.");
        this.validador.validaString(nome, "Nome não deve ser nulo ou vazio.");
        this.validador.validaString(curso, "Curso não deve ser nulo ou vazio.");
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;

    }

    /**
     * Sobrescreve o metodo equals para que o objeto seja considerado igual caso possua matriculas iguais.
     * @param o
     * @return boolean: Retorna se os objetos são diferentes ou iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula.equals(aluno.matricula);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    /**
     * Sobrescreve o toString() da superclass Object, e retorna no formato de matricula - nome - curso
     *
     * @return String: matricula - nome - curso
     */
    @Override
    public String toString(){
        return this.matricula + " - " + this.nome + " - " + this.curso;
    }
}
