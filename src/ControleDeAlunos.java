import java.util.HashMap;
import java.util.Map;

public class ControleDeAlunos {
    private HashMap<String, Grupo> grupos = new HashMap<String, Grupo>();
    private HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();


    public String cadastraAluno(String matricula, String nome, String curso) {
        Aluno aluno = new Aluno(matricula,nome,curso);
        String resp = "";
        if(!this.alunos.containsKey(matricula)) {
            this.alunos.put(matricula, aluno);
            resp = "CADASTRO REALIZADO";
        }else {
            resp = "MATRÍCULA JÁ CADASTRADA!";
        }
        return resp;
    }

    public String exibeInfoAluno(String matricula) {
        String resp = "";
        if(this.alunos.containsKey(matricula)){
            resp = this.alunos.get(matricula).toString();
        }else {
            resp = "ALUNO NÃO CADASTRADO.";
        }
        return resp;
    }

    public String cadastraGrupo(String tema, int tamanho) {
        String resp = "";
        if(!this.grupos.containsKey(tema)){
            Grupo grupo = new Grupo(tema, tamanho);
            this.grupos.put(tema, grupo);
            resp = "CADASTRO REALIZADO";
        } else {
            resp = "TEMA JÁ CADASTRADO";
        }
        return resp;
    }

    public String alocarAluno(String matricula, String grupo) {
        String resp = "ALUNO ALOCADO!";
        if(!this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(matricula))){
            this.grupos.get(grupo).adicionaAluno(this.alunos.get(matricula));
        }
        return resp;
    }

    public String pertinenteEmGrupo(String grupo, String aluno) {
        String resp = "";
        if(this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(aluno))){
            resp = "ALUNO PERTENCE AO GRUPO";
        }else {
            resp = "ALUNO NÃO PERTENCE AO GRUPO";
        }
        return resp;
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
