import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClienteTest {

    @Test
    public void quandoCPFNaoTiver13DigitosDeveLancarExcecao() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Cliente("602017569020", "João Bezerra da Silva")
        );
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Cliente("6020175690", "João Bezerra da Silva")
        );
    }

    @Test
    public void dadoDoisClientesComMesmoCPFQuandoCompararDeveSerIgual(){
        Cliente c1 = new Cliente("12345678901", "João Carlos");
        Cliente c2 = new Cliente("12345678901", "Mario da Silva");

        Assertions.assertEquals(c1, c2);
    }
}
