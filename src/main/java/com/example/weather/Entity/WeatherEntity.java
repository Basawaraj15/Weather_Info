package com.example.weather.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pincodeId;  // Foreign key reference to PincodeEntity
    private LocalDate forDate;
    @Column(length = 2000)
    private String weatherInfo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPincodeId() {
		return pincodeId;
	}
	public void setPincodeId(Long pincodeId) {
		this.pincodeId = pincodeId;
	}
	public LocalDate getForDate() {
		return forDate;
	}
	public void setForDate(LocalDate forDate) {
		this.forDate = forDate;
	}
	public String getWeatherInfo() {
		return weatherInfo;
	}
	public void setWeatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	@Override
	public String toString() {
		return "WeatherEntity [id=" + id + ", pincodeId=" + pincodeId + ", forDate=" + forDate + ", weatherInfo="
				+ weatherInfo + "]";
	}
	public WeatherEntity(Long id, Long pincodeId, LocalDate forDate, String weatherInfo) {
		super();
		this.id = id;
		this.pincodeId = pincodeId;
		this.forDate = forDate;
		this.weatherInfo = weatherInfo;
	}
	public WeatherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
}
