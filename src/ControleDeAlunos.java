import java.util.HashMap;
import java.util.Map;

public class ControleDeAlunos {
    private HashMap<String, Grupo> grupos = new HashMap<String, Grupo>();
    private HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();


    public String cadastraAluno(String matricula, String nome, String curso) {
        Aluno aluno = new Aluno(matricula,nome,curso);
        if(!this.alunos.containsKey(matricula)) {
            this.alunos.put(matricula, aluno);
            return "CADASTRO REALIZADO";
        }
        return "MATRÍCULA JÁ CADASTRADA!";
    }

    public String exibeInfoAluno(String matricula) {
        if(this.alunos.containsKey(matricula)){
            return this.alunos.get(matricula).toString();
        }
        return "ALUNO NÃO CADASTRADO.";
    }

    public String cadastraGrupo(String tema, int tamanho) {
        if(!this.grupos.containsKey(tema)){
            Grupo grupo = new Grupo(tema, tamanho);
            this.grupos.put(tema, grupo);
            return "CADASTRO REALIZADO";
        }
        return "TEMA JÁ CADASTRADO";
    }

    public String alocarAluno(String matricula, String grupo) {
        if(!this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(matricula))){
            this.grupos.get(grupo).adicionaAluno(this.alunos.get(matricula));
        }
        return "ALUNO ALOCADO!";
    }

    public String pertinenteEmGrupo(String grupo, String aluno) {
        if(this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(aluno))){
            return "ALUNO PERTENCE AO GRUPO";
        }
        return "ALUNO NÃO PERTENCE AO GRUPO";
    }

    public void exibeGrupos(String matricula) {
        System.out.println("Grupos: ");
        for(Map.Entry<String, Grupo> entry : this.grupos.entrySet()) {
            if(entry.getValue().alunoEmGrupo(this.alunos.get(matricula))){
                System.out.println(entry.getValue().toString());
            }
        }

        }
}
