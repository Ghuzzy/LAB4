package utils;

public class Validador {
    /**
     * Recebe uma String qualquer e retorna erro se a String for vazia.
     *
     * @param valor: String a ser analizada
     * @param msg: mensagem que deve ser retornada ao cair no erro.
     */
    public void validaStringVazia(String valor, String msg){
        if("".equals(valor)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Recebe uma String qualquer e retorna erro se a String for nula.
     *
     * @param valor: String a ser analizada
     * @param msg: mensagem que deve ser retornada ao cair no erro.
     */
    public void validaStringNula(String valor, String msg){
        if(valor == null){
            throw new NullPointerException(msg);
        }
    }

    /**
     * Recebe uma String qualquer e retorna erro se a String for vazia ou nula.
     *
     * @param valor: String a ser analizada
     * @param msg: mensagem que deve ser retornada ao cair no erro.
     */
    public void validaString(String valor, String msg) {
        this.validaStringNula(valor, msg);
        this.validaStringVazia(valor, msg);
    }
}
