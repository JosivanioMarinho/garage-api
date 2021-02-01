package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.config.JwtTokenUtil;
import com.josivaniomarinho.garageapi.dto.request.CarDTO;
import com.josivaniomarinho.garageapi.dto.request.UserDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.entity.Car;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.exception.UserExistsEmailAndLoginException;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.mapper.CarMapper;
import com.josivaniomarinho.garageapi.mapper.UserMapper;
import com.josivaniomarinho.garageapi.repository.CarRepository;
import com.josivaniomarinho.garageapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private CarRepository carRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    private JwtTokenUtil jwtTokenUtil;

    private String userLogin;

    @Autowired
    public UserService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    //Create user
    public MessageResponseDTO createUser(UserDTO userDTO){
        verifyIfEmailAndLoginExists(userDTO.getEmail(), userDTO.getLogin());

        String passwordEnconded = encodePassword().encode(userDTO.getPassword());

        User userToSave = userMapper.toModel(userDTO);
        userToSave.setPassword(passwordEnconded);

        Car carToSave = new Car();
        for (Car car : userToSave.getCars()){
            car.setUser(userToSave);
            carToSave = car;
        }
        //Save an user and car to user
        User savedUser = userRepository.save(userToSave);
            carRepository.save(carToSave);

        return createMessageResponse("User created with ID ", savedUser.getId());
    }

    //List all users
    public List<UserDTO> listAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Find user by id
    public UserDTO findUserByID(Long id) throws NotFoundException {
        User user = verifyIfExists(id);

        return userMapper.toDTO(user);
    }

    //Delete user by id
    public void deleteUserBYID(Long id) throws NotFoundException {
       verifyIfExists(id);

        userRepository.deleteById(id);
    }

    //Update user by id
    public MessageResponseDTO updateUserByID(Long id, UserDTO userDTO) throws NotFoundException {
        verifyIfExists(id);

        User userToUpdate = userMapper.toModel(userDTO);

        User userUpdated = userRepository.save(userToUpdate);
        return createMessageResponse("User updated with ID ", userUpdated.getId());
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }

    //Return data's user
    public UserDTO userInformations(){
        User userLoged = userRepository.findByLogin(this.userLogin);

        UserDTO userDTO = userMapper.toDTO(userLoged);
        return UserDTO
                .builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .birthday(userDTO.getBirthday())
                .login(userDTO.getLogin())
                .phone(userDTO.getPhone())
                .cars(userDTO.getCars())
                .build();
    }

    private User verifyIfExists(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id));
    }

    private MessageResponseDTO createMessageResponse(String s, Long id){
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private void verifyIfEmailAndLoginExists(String email, String login){
        List<User> users = userRepository.findAll();
        for (User user : users){
            if(user.getEmail().equals(email)){
                throw new UserExistsEmailAndLoginException("Email already exists");
            }else if (user.getLogin().equals(login)) {
                throw new UserExistsEmailAndLoginException("Login already exists");
            }
        }
    }

    private PasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }
}
