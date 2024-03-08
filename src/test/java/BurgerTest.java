import praktikum.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private List<Ingredient> ingredients;

    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;
    @Mock
    private Ingredient ingredient3;

    private float bunPrice = 100f;
    private String ingredientName = "Sicret";
    private String ingredientType = "filling";
    private float ingredientPrice = 200f;
    private float expectedPrice = 800f;
    private String bunName = "Bun Kosmos";
    private boolean expected = false;

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredients.size()).thenReturn(3);
        for (int i = 0; i < ingredients.size(); i++) {
            Mockito.when(ingredients.get(i)).thenReturn(new Ingredient(IngredientType.FILLING, ingredientName, ingredientPrice));
            burger.addIngredient(ingredients.get(i));
        }

        assertEquals(burger.getPrice(), expectedPrice, 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(bun.getName()).thenReturn(bunName);

        Mockito.when(ingredients.size()).thenReturn(3);
        for (int i = 0; i < ingredients.size(); i++) {
            Mockito.when(ingredients.get(i)).thenReturn(new Ingredient(IngredientType.FILLING, ingredientName, ingredientPrice));
            burger.addIngredient(ingredients.get(i));
        }

        StringBuilder check = new StringBuilder(String.format("(==== %s ====)%n", bunName));

        for (Ingredient ingredient : burger.ingredients) {
            check.append(String.format("= %s %s =%n", ingredientType.toLowerCase(),
                    ingredientName));
        }
        check.append(String.format("(==== %s ====)%n", bunName));
        check.append(String.format("%nPrice: %f%n", expectedPrice));
        assertEquals(burger.getReceipt(), check.toString());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 1);
        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(1)); // проверяем, что индексы поменялись местами
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient3, burger.ingredients.get(2));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(expected,burger.ingredients.contains(1));
    }
}
