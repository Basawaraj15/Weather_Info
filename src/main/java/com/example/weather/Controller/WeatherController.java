package com.example.weather.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.Entity.WeatherEntity;
import com.example.weather.Service.WeatherService;

import java.time.LocalDate;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/api/weather")
    public ResponseEntity<?> getWeather(
            @RequestParam String pincode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate forDate) {
        WeatherEntity weatherEntity = weatherService.getWeatherForPincodeAndDate(pincode, forDate);
        return ResponseEntity.ok(weatherEntity);
    }
}
