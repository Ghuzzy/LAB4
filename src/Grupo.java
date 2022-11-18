import utils.Validador;

import java.util.Objects;

public class Grupo {
    private final String tema;
    private final Aluno[] alunos;

    private int indice = 0;

    public Grupo(String tema, int tamanho){
        Validador validador = new Validador();
        validador.validaString(tema, "O tema não pode ser nulo ou vazio");
        this.tema = tema;
        this.alunos = new Aluno[tamanho];
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
        for(Aluno aluno : this.alunos){
            if(!(aluno == null)){
                if( aluno.equals(alunoACadastrar)){
                    return true;
                }
            }
        }
        return false;
    }

    public void adicionaAluno(Aluno aluno) {
        if(indice < this.alunos.length) {
            this.alunos[indice] = aluno;
            this.indice += 1;
        }
    }

    @Override
    public String toString() {
        if(indice < this.alunos.length) {
            return "- " + this.tema + " " + (indice + 1) + "/" + this.alunos.length;
        }
        return "- " + this.tema + " " + indice + "/" + this.alunos.length;
    }
}
