import praktikum.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTest {
    public String name = "Sicret";
    public float price = 150.5f;

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,name,price);
        assertEquals(price,ingredient.getPrice(), 0);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,name,price);
        assertEquals(name,ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,name,price);
        assertEquals(IngredientType.FILLING,ingredient.getType());
    }
}
