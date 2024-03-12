import praktikum.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BunTest {
    private String name = "Bun Kosmos";
    private float price = 150.5f;

    @Test
    public void getNameTest() {
        Bun bun = new Bun(name, price);
        assertEquals(name,bun.getName());
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun(name, price);
        assertEquals(price,bun.getPrice(),0);
    }

}
