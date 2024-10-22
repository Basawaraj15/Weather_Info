package com.example.weather.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weather.Entity.PincodeEntity;

public interface PincodeRepository extends JpaRepository<PincodeEntity, Long> {
    PincodeEntity findByPincode(String pincode);
}
