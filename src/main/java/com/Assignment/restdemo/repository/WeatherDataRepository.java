package com.Assignment.restdemo.repository;

import com.Assignment.restdemo.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Integer> {

}
