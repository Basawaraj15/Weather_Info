package com.example.weather.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weather.Entity.WeatherEntity;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    WeatherEntity findByPincodeIdAndForDate(Long pincodeId, LocalDate forDate);
}
