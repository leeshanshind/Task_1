package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Булочка")
class BunTest {

    static Stream<Arguments> bunData() {
        return Stream.of(
                arguments("black bun", 100f),
                arguments("", 0f),
                arguments(null, -1f),
                arguments("Флюоресцентная булка R2-D3", 0.01f),
                arguments("x".repeat(255), Float.MAX_VALUE)
        );
    }

    @ParameterizedTest(name = "name = {0}")
    @MethodSource("bunData")
    @DisplayName("Булочка возвращает переданное название")
    void getNameReturnsName(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @ParameterizedTest(name = "price = {1}")
    @MethodSource("bunData")
    @DisplayName("Булочка возвращает переданную цену")
    void getPriceReturnsPrice(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice());
    }
}
