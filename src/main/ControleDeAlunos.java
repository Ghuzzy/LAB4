package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleDeAlunos {

    /**
     * Hashmap que tem como key o tema do grupo e value o próprio grupo.
     */
    private HashMap<String, Grupo> grupos;

    /**
     * Hashmap que tem como key a matricula do aluno e value o próprio aluno.
     */
    private HashMap<String, Aluno> alunos;

    /**
     * Arraylist que guarda o histórico de respostas dos alunos.
     */
    private ArrayList<Aluno> registroRespostas;


    public ControleDeAlunos(){
        this.alunos = new HashMap<String, Aluno>();
        this.grupos  = new HashMap<String, Grupo>();
        this.registroRespostas = new ArrayList<Aluno>();
    }

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
    public String cadastraGrupo(String tema) {
        String resp = "";
        if(!this.grupos.containsKey(tema)){
            Grupo grupo = new Grupo(tema);
            this.grupos.put(tema, grupo);
            resp = "CADASTRO REALIZADO";
        } else {
            resp = "GRUPO JÁ CADASTRADO";
        }
        return resp;
    }
    /**
     * Cadastra um novo main.Grupo em Sistema caso este já não esteja cadastrado.
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
            resp = "GRUPO JÁ CADASTRADO";
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
                resp = this.grupos.get(grupo).adicionaAluno(this.alunos.get(matricula));
        }else if(!(alunoCadastrado(matricula)) && !(grupoCadastrado(grupo))){
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
        }else if(!(alunoCadastrado(aluno)) && !(grupoCadastrado(grupo))){
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
    public String exibeGrupos(String matricula) {
        String resp = "";
        for(Map.Entry<String, Grupo> entry : this.grupos.entrySet()) {
            if(entry.getValue().alunoEmGrupo(this.alunos.get(matricula))){
                resp += (entry.getValue().toString() + "\n");
            }
        }
        return resp;
        }

    /**
     * Retorna se o aluno está cadastrado ou não no sistema.
     * @param matricula: matricula do aluno.
     * @return boolean: valor lógico se aluno está cadastrado em sistema.
     */
    private boolean alunoCadastrado(String matricula){
        if((!this.alunos.containsKey(matricula))){
            return false;
        }
        return true;
    }

    /**
     * Retorna se o grupo está cadastrado ou não no sistema.
     * @param grupo: tema do grupo a ser procurado em sistema.
     * @return boolean: valor lógico se grupo está cadastrado em sistema.
     */
    private boolean grupoCadastrado(String grupo){
        if((!this.grupos.containsKey(grupo))){
            return false;
        }
        return true;
    }

    public Aluno getAluno(String matricula){
        return this.alunos.get(matricula);
    }
    public Grupo getGrupo(String tema){
        return this.grupos.get(tema);
    }

    //bonus

    /**
     * Registra o aluno que respondeu uma questão no sistema. Caso não esteja cadastrado no sitema deve retornar tratamento.
     * @param matricula
     * @return String: Se foi registrado a resposta ou se o aluno não é cadastrado.
     */
    public String registrarAlunoQRespondeu(String matricula){
        String resp = "";

        if(this.alunos.containsKey(matricula)){
            registroRespostas.add(this.alunos.get(matricula));
            resp = "ALUNO REGISTRADO!";
        }else{
            resp = "ALUNO NÃO CADASTRADO.";
        }
        return resp;
    }

    /**
     * Retorna uma String com o histórico dos launos que responderam questões no sistema.
     *
     * @return String : Histórico de respostas.
     */
    public String imprimirRegistroDeRespostas(){
        String resp = "";
        for(int i = 0; i < this.registroRespostas.size(); i++){
            resp += ((i+1) + ". " + this.registroRespostas.get(i).toString() + "\n");
        }
        return resp;
    }

}
