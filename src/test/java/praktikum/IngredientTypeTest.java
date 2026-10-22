package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тип ингредиента")
class IngredientTypeTest {

    @Test
    @DisplayName("Есть только два типа: соус и начинка")
    void valuesContainSauceAndFilling() {
        assertArrayEquals(new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                IngredientType.values());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"SAUCE", "FILLING"})
    @DisplayName("Тип находится по названию")
    void valueOfReturnsTypeByName(String name) {
        assertEquals(name, IngredientType.valueOf(name).name());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"BUN", "sauce", ""})
    @DisplayName("Неизвестный тип не находится по названию")
    void valueOfThrowsForUnknownName(String name) {
        assertThrows(IllegalArgumentException.class, () -> IngredientType.valueOf(name));
    }
}
