package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.dto.request.CarDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.entity.Car;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.exception.CarExistsLicensePlateException;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.mapper.CarMapper;
import com.josivaniomarinho.garageapi.repository.CarRepository;
import com.josivaniomarinho.garageapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    private final CarMapper carMapper = CarMapper.INSTANCE;

    private String userLogin;

    private UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public MessageResponseDTO createCar(CarDTO carDTO){
        verifyIfLicensePlateExists(carDTO.getLicensePlate());

        Car car = carMapper.toModel(carDTO);

        User userLoged = userRepository.findByLogin(this.userLogin);
            car.setUser(userLoged);

            carRepository.save(car);
        /*
        List<Car> carsToSave = userLoged.getCars();
        carsToSave.add(car);
        userLoged.setCars(carsToSave);

        userRepository.save(userLoged);
        */

        return createMessageResponse("Car created for user witch ID ", userLoged.getId());
    }

    //List all cars
    public List<CarDTO> listAll(){
        User userLoged = userRepository.findByLogin(this.userLogin);

        List<Car> carsList = userLoged.getCars();
        return carsList.stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Find car by id
    public CarDTO findCarById(Long id) throws NotFoundException {
        Car car = verifyIfExists(id);

        User userLoged = userRepository.findByLogin(this.userLogin);
        List<Car> carsList = userLoged.getCars();

        for (Car car1 : carsList){
            if (car1.getId().equals(car.getId())){
                return carMapper.toDTO(car);
            }
        }
        return null;
    }

    //Update car by id
    public MessageResponseDTO updateCarById(Long id, CarDTO carDTO) throws NotFoundException {
        verifyIfExists(id);

        User userLogged = userRepository.findByLogin(this.userLogin);
        List<Car> cars = userLogged.getCars();

        //Checks if the legged in user has the car with the indicated ID
        for (Car car1 : cars){
            if (car1.getId().equals(id)){
                Car carToUpdate = carMapper.toModel(carDTO);
                carToUpdate.setUser(userLogged);

                Car updatedCar = carRepository.save(carToUpdate);
                return createMessageResponse("Car updated with ID ", updatedCar.getId());
            }
        }
        throw new NotFoundException("This user does not have a car with the ID "+ id);
    }

    //Delete car by id
    public void deleteById(Long id) throws NotFoundException {
        Car car = verifyIfExists(id);

        User userLoged = userRepository.findByLogin(this.userLogin);
        List<Car> cars = userLoged.getCars();

        for (Car car1 : cars){
            if (car1.getId().equals(car.getId())){

                System.out.println("Quantidade de carros antes de excluir: "+cars.size());
                carRepository.deleteById(car.getId());
                cars = carRepository.findAll();
                System.out.println("Quantidade de carros: "+cars.size());
            }
        }
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }

    private MessageResponseDTO createMessageResponse(String s, Long id){
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private Car verifyIfExists(Long id) throws NotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car ", id));
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
