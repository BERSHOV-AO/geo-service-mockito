package geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTests {

    GeoServiceImpl sut;

    @BeforeEach
    public void beforeEach() {
        sut = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        sut = null;
    }

    @ParameterizedTest
    @MethodSource
    void test_byIp(String ip, Location expected) {

        // act
        Location result = sut.byIp(ip);

        // assert
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    public static Stream<Arguments> test_byIp() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null,
                        null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA,
                        "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA,
                        " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    void test_byCoordinates() {

        int a = -100, b = -800;
        Exception exception = assertThrows(RuntimeException.class, () -> sut.byCoordinates(a, b));
        assertEquals("Not implemented", exception.getMessage());
    }
}
