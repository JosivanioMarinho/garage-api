package com.josivaniomarinho.garageapi.controller;

import com.josivaniomarinho.garageapi.dto.request.CarDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createCar(@RequestBody @Valid CarDTO carDTO){
        return carService.createCar(carDTO);
    }

    @GetMapping
    public List<CarDTO> listAll(){
        return carService.listAll();
    }

    @GetMapping("/{id}")
    public CarDTO findCarById(@PathVariable Long id) throws NotFoundException {
        return carService.findCarById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateCarById(@PathVariable Long id, @RequestBody @Valid CarDTO carDTO) throws NotFoundException {
        return carService.updateCarById(id, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        carService.deleteById(id);
    }
}
