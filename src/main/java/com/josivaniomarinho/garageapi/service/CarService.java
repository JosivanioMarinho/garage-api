package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.dto.request.CarDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.entity.Car;
import com.josivaniomarinho.garageapi.exception.CarExistsLicensePlateException;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.mapper.CarMapper;
import com.josivaniomarinho.garageapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    private final CarMapper carMapper = CarMapper.INSTANCE;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public MessageResponseDTO createCar(CarDTO carDTO){
        verifyIfLicensePlateExists(carDTO.getLicensePlate());

        Car carToSave = carMapper.toModel(carDTO);

        Car savedCar = carRepository.save(carToSave);
        return createMessageResponse("Car created with ID ", savedCar.getId());
    }

    //List all cars
    public List<CarDTO> listAll(){
        List<Car> carsList = carRepository.findAll();
        return carsList.stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Find car by id
    public CarDTO findCarById(Long id) throws NotFoundException {
        Car car = verifyIfExists(id);

        return carMapper.toDTO(car);
    }

    //Update car by id
    public MessageResponseDTO updateCarById(Long id, CarDTO carDTO) throws NotFoundException {
        verifyIfExists(id);

        Car carToUpdate = carMapper.toModel(carDTO);

        Car updatedCar = carRepository.save(carToUpdate);
        return createMessageResponse("Car updated with ID ", updatedCar.getId());
    }

    //Delete car by id
    public void deleteById(Long id) throws NotFoundException {
        verifyIfExists(id);

        carRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(String s, Long id){
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private Car verifyIfExists(Long id) throws NotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car", id));
    }

    private void verifyIfLicensePlateExists(String licensePlate){
        List<Car> carsList = carRepository.findAll();
        for (Car car : carsList){
            if(car.getLicensePlate().equals(licensePlate)){
                throw new CarExistsLicensePlateException("License plate already exists");
            }
        }
    }

}
