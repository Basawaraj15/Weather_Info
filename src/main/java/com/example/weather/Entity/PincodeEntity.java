package com.example.weather.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PincodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String pincode;
    private double latitude;
    private double longitude;
    
    
    public String getpincode() {
        return pincode;
    }

    public void setpincode(String pincode) {
        this.pincode = pincode;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public double getlatitude() {
        return latitude;
    }

    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getlongitude() {
        return longitude;
    }

    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }

	public PincodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PincodeEntity(Long id, String pincode, double latitude, double longitude) {
		super();
		this.id = id;
		this.pincode = pincode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PincodeEntity [id=" + id + ", pincode=" + pincode + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

    // Getters and Setters
}
