package com.josivaniomarinho.garageapi.repository;

import com.josivaniomarinho.garageapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
