import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Validador;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {
    Validador validador;
    @BeforeEach
    void setUp(){
        this.validador = new Validador();
    }
    @Test
    void testaValidaStringVazia(){
        try{
            validador.validaStringVazia("", "A string não deve ser vazia.");
        }catch(IllegalArgumentException e){
            assertEquals("A string não deve ser vazia.", e.getMessage());
        }
    }
    @Test
    void testaValidaStringNull(){
        try{
            validador.validaStringNula(null, "A string não deve ser nula.");
        }catch(NullPointerException e){
            assertEquals("A string não deve ser nula.", e.getMessage());
        }
    }
    @Test
    void testaValidaString1(){
        try{
            validador.validaString("", "A string não deve ser vazia.");
        }catch(IllegalArgumentException e){
            assertEquals("A string não deve ser vazia.", e.getMessage());
        }
    }
    @Test
    void testaValidaString2(){
        try{
            validador.validaString(null, "A string não deve ser nula.");
        }catch(NullPointerException e){
            assertEquals("A string não deve ser nula.", e.getMessage());
        }
    }

}
