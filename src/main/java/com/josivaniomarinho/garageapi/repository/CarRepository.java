package com.josivaniomarinho.garageapi.repository;

import com.josivaniomarinho.garageapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
}
