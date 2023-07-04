package i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTests {

    LocalizationServiceImpl sut;

    @BeforeEach
    public void beforeEach() {
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        sut = null;
    }

    @ParameterizedTest
    @MethodSource
    void test_locale(Country country, String expected) {

        // act
        String result = sut.locale(country);

        // assert
        assertEquals(expected, result);
    }

    public static Stream<Arguments> test_locale() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }
}
