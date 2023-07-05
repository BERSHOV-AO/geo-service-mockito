package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTests {

    @Test
    void test_send_Russia() {

        String expected = "Добро пожаловать";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA,
                "Lenina", 15));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class))).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_send_USA() {

        String expected = "Welcome";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA,
                " 10th Avenue", 32));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class))).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_send_brazil() {

        String brazilIp = "177.207.208.35";

        String expected = "Welcome";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Sao Jose", Country.BRAZIL,
                "Rua Maria Bernardina", 117));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class))).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, brazilIp);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }
}
