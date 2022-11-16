package utils;

public class Validador {
    public void validaStringVazia(String valor, String msg){
        if("".equals(valor)) {
            throw new IllegalArgumentException(msg);
        }
    }
    public void validaStringNula(String valor, String msg){
        if(valor == null){
            throw new NullPointerException(msg);
        }
    }
    public void validaString(String valor, String msg) {
        this.validaStringNula(valor, msg);
        this.validaStringVazia(valor, msg);
    }
}
