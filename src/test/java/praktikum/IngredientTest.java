package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@DisplayName("Ингредиент")
class IngredientTest {

    static Stream<Arguments> ingredientData() {
        return Stream.of(
                arguments(SAUCE, "hot sauce", 100f),
                arguments(SAUCE, "", 0f),
                arguments(FILLING, "cutlet", 0.01f),
                arguments(FILLING, null, -1f),
                arguments(FILLING, "Мясо бессмертных моллюсков Protostomia", Float.MAX_VALUE)
        );
    }

    @ParameterizedTest(name = "type = {0}")
    @MethodSource("ingredientData")
    @DisplayName("Ингредиент возвращает переданный тип")
    void getTypeReturnsType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest(name = "name = {1}")
    @MethodSource("ingredientData")
    @DisplayName("Ингредиент возвращает переданное название")
    void getNameReturnsName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest(name = "price = {2}")
    @MethodSource("ingredientData")
    @DisplayName("Ингредиент возвращает переданную цену")
    void getPriceReturnsPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(price, ingredient.getPrice());
    }
}
