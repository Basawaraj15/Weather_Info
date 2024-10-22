package serviceTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.weather.Entity.GeocodingResponse;
import com.example.weather.Entity.PincodeEntity;
import com.example.weather.Service.WeatherService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testFetchWeatherDataFromAPI() {
        // Mocking the RestTemplate response for weather data
        String mockWeatherData = "{ 'weather': 'Clear Sky' }";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockWeatherData);

        String weatherData = weatherService.fetchWeatherDataFromAPI(19.0760, 72.8777);
        assertNotNull(weatherData);
        assertTrue(weatherData.contains("Clear Sky"));
    }

    @Test
    public void testFetchAndSavePincodeData() {
        // Mocking the RestTemplate response for Geocoding API
        GeocodingResponse mockGeocodingResponse = new GeocodingResponse();
        mockGeocodingResponse.setLat(19.0760);
        mockGeocodingResponse.setLon(72.8777);

        when(restTemplate.getForObject(anyString(), eq(GeocodingResponse.class))).thenReturn(mockGeocodingResponse);

        PincodeEntity pincodeEntity = weatherService.fetchAndSavePincodeData("400001");
        assertNotNull(pincodeEntity);
        assertEquals(19.0760, pincodeEntity.getlatitude());
        assertEquals(72.8777, pincodeEntity.getlongitude());
    }
}
