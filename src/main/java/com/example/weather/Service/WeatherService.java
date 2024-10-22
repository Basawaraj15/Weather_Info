package com.example.weather.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weather.Entity.GeocodingResponse;
import com.example.weather.Entity.PincodeEntity;
import com.example.weather.Entity.WeatherEntity;
import com.example.weather.Repository.PincodeRepository;
import com.example.weather.Repository.WeatherRepository;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String openWeatherApiKey = "1ab34cdf6678b74f5475a1ac9452247b";

    public WeatherEntity getWeatherForPincodeAndDate(String pincode, LocalDate forDate) {
        PincodeEntity pincodeEntity = pincodeRepository.findByPincode(pincode);

        if (pincodeEntity == null) {
            // Call OpenWeather API to get lat/long and save it in the database
            pincodeEntity = fetchAndSavePincodeData(pincode);
        }

        // Check if the weather data for this date is already cached
        WeatherEntity cachedWeather = weatherRepository.findByPincodeIdAndForDate(pincodeEntity.getid(), forDate);
        if (cachedWeather != null) {
            return cachedWeather;
        }

        // Fetch weather data from OpenWeather API if not cached
        String weatherData = fetchWeatherDataFromAPI(pincodeEntity.getlatitude(), pincodeEntity.getlongitude());
        
        // Save the weather data
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setPincodeId(pincodeEntity.getid());
        weatherEntity.setForDate(forDate);
        weatherEntity.setWeatherInfo(weatherData);
        
        return weatherRepository.save(weatherEntity);
    }

    public PincodeEntity fetchAndSavePincodeData(String pincode) {
        // Example URL for getting lat/long for the pincode
        String geocodingUrl = "http://api.openweathermap.org/geo/1.0/zip?zip=" + pincode + ",IN&appid=" + openWeatherApiKey;
        // Make API call
        GeocodingResponse response = restTemplate.getForObject(geocodingUrl, GeocodingResponse.class);

        PincodeEntity pincodeEntity = new PincodeEntity();
        pincodeEntity.setpincode(pincode);
        pincodeEntity.setlatitude(response.getLat());
        pincodeEntity.setlongitude(response.getLon());

        return pincodeRepository.save(pincodeEntity);
    }

    public String fetchWeatherDataFromAPI(double latitude, double longitude) {
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + openWeatherApiKey;
        return restTemplate.getForObject(weatherUrl, String.class);
    }
}
