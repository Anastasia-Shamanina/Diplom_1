import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import praktikum.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Parameterized.class)
public class BurgerWithParameters {
    private static String ingredientName = "Sicret";
    private static float ingredientPrice = 200f;
    private static String ingredientName1 = "Hot";
    private static float ingredientPrice1 = 100f;

    private final List<Ingredient> ingredients;
    private final List<String> expected;

    public BurgerWithParameters(List<Ingredient> ingredients) {
        this.expected = new ArrayList<>();
        this.ingredients = ingredients;
        for (Ingredient ingredient : ingredients) {
            this.expected.add(String.format("%s, %s, %s%n", ingredient.getType(), ingredient.getName(), ingredient.getPrice()));
        }

    }

    @Parameterized.Parameters
    public static Object[][] getConstructorParams() {
        return new Object[][]{
                {List.of(new Ingredient(IngredientType.SAUCE, ingredientName1, ingredientPrice1), new Ingredient(IngredientType.FILLING, ingredientName, ingredientPrice))}
        };
    }

    Burger burger = new Burger();

    @Test
    public void addIngredientsTest() {
        // Проверяем, что список еще пуст
        assertEquals(0, burger.ingredients.size());
        // Добавляем ингридиенты
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(ingredients.size(), burger.ingredients.size());
        for (int i = 0; i < ingredients.size(); i++) {
            assertEquals(expected.get(i), String.format("%s, %s, %s%n", burger.ingredients.get(i).getType(), burger.ingredients.get(i).getName(), burger.ingredients.get(i).getPrice()));
        }
    }

    @Test
    public void removeIngredientTest() {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        // Удаляем один ингридиент
        burger.removeIngredient(1);
        // Проверяем, что список стал меньше на 1
        assertEquals(1, burger.ingredients.size());
        // Проверяем, что 1 ингредиент удален из списка
        assertFalse(burger.ingredients.contains(ingredients.get(1)));
        // Проверяем, что 0 ингредиент есть в списке
        assertTrue(burger.ingredients.contains(ingredients.get(0)));

    }

    @Test
    public void moveIngredientTest() {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        // Перемещаем первый ингредиент с индексом 1 на позицию с индексом 0
        burger.moveIngredient(1, 0);
        assertEquals(2, burger.ingredients.size());

        assertEquals(expected.get(ingredients.size()-1), String.format("%s, %s, %s%n", burger.ingredients.get(1).getType(), burger.ingredients.get(1).getName(), burger.ingredients.get(1).getPrice()));
        assertEquals(expected.get(0), String.format("%s, %s, %s%n", burger.ingredients.get(0).getType(), burger.ingredients.get(0).getName(), burger.ingredients.get(0).getPrice()));
    }

}
