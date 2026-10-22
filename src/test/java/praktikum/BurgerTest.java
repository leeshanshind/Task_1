package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
@DisplayName("Бургер")
class BurgerTest {
    private static final String BUN_NAME = "black bun";
    private static final String SAUCE_NAME = "hot sauce";
    private static final String FILLING_NAME = "cutlet";

    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Булочка устанавливается в бургер")
    void setBunsSetsBun() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    @DisplayName("Ингредиент добавляется в конец списка")
    void addIngredientAddsToEnd() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(List.of(sauce, filling), burger.ingredients);
    }

    @Test
    @DisplayName("Ингредиент удаляется по индексу")
    void removeIngredientRemovesByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(List.of(filling), burger.ingredients);
    }

    @Test
    @DisplayName("Удаление единственного ингредиента оставляет список пустым")
    void removeOnlyIngredientLeavesListEmpty() {
        burger.addIngredient(sauce);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    @DisplayName("Ингредиент перемещается на новую позицию")
    void moveIngredientChangesPosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(List.of(filling, sauce), burger.ingredients);
    }

    @ParameterizedTest(name = "булка {0}, соус {1}, начинка {2} -> {3}")
    @CsvSource({
            "100, 100, 300, 600",
            "0, 0, 0, 0",
            "0.5, 1.25, 2.75, 5",
            "988, 90, 3000, 5066"
    })
    @DisplayName("Цена = две булочки + сумма ингредиентов")
    void getPriceCountsTwoBunsAndIngredients(float bunPrice, float saucePrice, float fillingPrice,
                                             float expectedPrice) {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    @DisplayName("Цена бургера без ингредиентов = две булочки")
    void getPriceWithoutIngredientsCountsOnlyBuns() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice());
    }

    @Test
    @DisplayName("Чек содержит булочки, ингредиенты в порядке добавления и цену")
    void getReceiptContainsBunsIngredientsAndPrice() {
        when(bun.getName()).thenReturn(BUN_NAME);
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getName()).thenReturn(SAUCE_NAME);
        when(sauce.getType()).thenReturn(SAUCE);
        when(sauce.getPrice()).thenReturn(100f);
        when(filling.getName()).thenReturn(FILLING_NAME);
        when(filling.getType()).thenReturn(FILLING);
        when(filling.getPrice()).thenReturn(300f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format("(==== %s ====)%n", BUN_NAME)
                + String.format("= sauce %s =%n", SAUCE_NAME)
                + String.format("= filling %s =%n", FILLING_NAME)
                + String.format("(==== %s ====)%n", BUN_NAME)
                + String.format("%nPrice: %f%n", 600f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
