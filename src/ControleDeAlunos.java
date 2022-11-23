import java.util.HashMap;
import java.util.Map;

public class ControleDeAlunos {

    /**
     * Hashmap que tem como key o tema do grupo e value o próprio grupo.
     */
    private HashMap<String, Grupo> grupos = new HashMap<String, Grupo>();

    /**
     * Hashmap que tem como key a matricula do aluno e value o próprio aluno.
     */
    private HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();


    /**
     * Cadastra um novo aluno no Sistema. Deve retornar que o cadastro foi realizado,
     * ou caso o aluno já esteja cadastrado retornar que a matricula já foi castrada.
     *
     * @param matricula: matricula do aluno a ser cadastrado.
     * @param nome: nome do aluno a ser cadastrado.
     * @param curso: curso o qual o aluno esta matriculado.
     * @return String: Se o cadastro foi realizado ou não.
     */
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

    /**
     * Imprime as informações do aluno caso ele esteja cadastrado.
     *
     * @param matricula
     * @return String: - matricula - nome - curso.
     */
    public String exibeInfoAluno(String matricula) {
        String resp = "";
        if(this.alunos.containsKey(matricula)){
            resp = this.alunos.get(matricula).toString();
        }else {
            resp = "ALUNO NÃO CADASTRADO.";
        }
        return resp;
    }

    /**
     * Cadastra um novo Grupo em Sistema caso este já não esteja cadastrado.
     *
     * @param tema: tema do grupo a ser cadastrado.
     * @param tamanho: tamanho limite do grupo.
     * @return String: Se o cadastrado foi ou não realizado.
     */
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

    /**
     * Aloca um aluno do Sistema em um dos grupos do Sistema, caso o aluno e o grupo existam.
     *
     * @param matricula: matricula do aluno a ser alocado.
     * @param grupo: grupo o qual o aluno deve ser alocado.
     * @return String: Sempre retorna que o aluno foi alocado.
     */
    public String alocarAluno(String matricula, String grupo) {
        String resp = "";
        if (alunoCadastrado(matricula) && grupoCadastrado(grupo)){
         if (!this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(matricula))) {
                this.grupos.get(grupo).adicionaAluno(this.alunos.get(matricula));
                resp = "ALUNO ALOCADO!";
            } else {
                resp = "ALUNO ALOCADO!";
            }
        }else if(!(alunoCadastrado(matricula) && grupoCadastrado(grupo))){
            resp = "ALUNO E GRUPO NÃO CADASTRADOS";
        }else if(!alunoCadastrado(matricula)){
            resp = "ALUNO NÃO CADASTRADO";
        }else{
            resp = "GRUPO NÃO CADASTRADO";
        }
        return resp;
    }



    /**
     * Verifica a pertinencia de um determinado aluno em determinado grupo.
     *
     * @param grupo: grupo o qual vai ser verificado a presença do aluno.
     * @param aluno: aluno a ser procurado.
     * @return String: Se o aluno está ou não no grupo.
     */
    public String pertinenteEmGrupo(String grupo, String aluno) {
        String resp = "";
        if(alunoCadastrado(aluno) && grupoCadastrado(grupo)) {
            if (this.grupos.get(grupo).alunoEmGrupo(this.alunos.get(aluno))) {
                resp = "ALUNO PERTENCE AO GRUPO";
            } else {
                resp = "ALUNO NÃO PERTENCE AO GRUPO";
            }
        }else if(!(alunoCadastrado(aluno) && grupoCadastrado(grupo))){
            resp = "ALUNO E GRUPO NÃO CADASTRADOS";
        }else if(!alunoCadastrado(aluno)){
            resp = "ALUNO NÃO CADASTRADO";
        }else{
            resp = "GRUPO NÃO CADASTRADO";
        }
        return resp;
    }

    /**
     * Exibe os grupos que determinado aluno está incluso.
     *
     * @param matricula: matricula do aluno a exibir grupos.
     */
    public void exibeGrupos(String matricula) {
        System.out.println("Grupos: ");
        for(Map.Entry<String, Grupo> entry : this.grupos.entrySet()) {
            if(entry.getValue().alunoEmGrupo(this.alunos.get(matricula))){
                System.out.println(entry.getValue().toString());
            }
        }
        }

    private boolean alunoCadastrado(String matricula){
        if((!this.alunos.containsKey(matricula))){
            return false;
        }
        return true;
    }
    private boolean grupoCadastrado(String grupo){
        if((!this.grupos.containsKey(grupos))){
            return false;
        }
        return true;
    }

}
