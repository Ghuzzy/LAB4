import utils.Validador;

import java.util.Objects;

public class Aluno {
    private String matricula;
    private String nome;
    private String curso;
    private Validador validador = new Validador();;

    public Aluno(String matricula, String nome, String curso){
        this.validador.validaString(matricula, "Matricula não deve ser nula ou vazia.");
        this.validador.validaString(nome, "Nome não deve ser nulo ou vazio.");
        this.validador.validaString(curso, "Curso não deve ser nulo ou vazio.");
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;

    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula.equals(aluno.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString(){
        return this.matricula + " - " + this.nome + " - " + this.curso;
    }
}
