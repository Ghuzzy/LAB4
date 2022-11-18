import utils.Validador;

import java.util.HashSet;
import java.util.Objects;

public class Grupo {
    private final String tema;
    private final HashSet<Aluno> alunos;

    private int tamanho;

    public Grupo(String tema, int tamanho){
        Validador validador = new Validador();
        validador.validaString(tema, "O tema não pode ser nulo ou vazio");
        this.tema = tema;
        this.alunos = new HashSet<>();
        this.tamanho = tamanho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return tema.equals(grupo.tema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tema);
    }

    public boolean alunoEmGrupo(Aluno alunoACadastrar) {
        if(this.alunos.contains(alunoACadastrar)){
                    return true;
            }
        return false;
    }

    public void adicionaAluno(Aluno aluno) {
        if(this.alunos.size() < this.tamanho) {
            this.alunos.add(aluno);
        }
    }

    @Override
    public String toString() {
        return "- " + this.tema + " " + this.alunos.size() + "/" + this.tamanho;
    }
}
