import praktikum.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;
    @Mock
    private Ingredient ingredient3;


    private float bunPrice = 100f;
    private String ingredientName = "Sicret";
    private String ingredientType = "FILLING";
    private float ingredientPrice = 200f;
    private float expectedPrice = (bunPrice * 2) + ingredientPrice;
    private String bunName = "Bun Kosmos";
    private boolean expected = false;

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.addIngredient(ingredient1);
        when(ingredient1.getPrice()).thenReturn(ingredientPrice);

        assertEquals(burger.getPrice(), expectedPrice, 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(bun.getName()).thenReturn(bunName);

        burger.addIngredient(ingredient1);
        when(ingredient1.getPrice()).thenReturn(ingredientPrice);
        when(ingredient1.getName()).thenReturn(ingredientName);
        when(ingredient1.getType()).thenReturn(IngredientType.valueOf(ingredientType));

        String check = String.format(("(==== %s ====)%n"), bunName) +
                "= filling Sicret =" +
                String.format("%n(==== %s ====)%n", bunName) +
                String.format("%nPrice: 400,000000%n", expectedPrice);

        assertEquals(check, burger.getReceipt());
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
        assertEquals(expected, burger.ingredients.contains(1));
    }
}
