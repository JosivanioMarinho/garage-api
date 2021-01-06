package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.dto.request.UserDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.exception.UserNotFoudException;
import com.josivaniomarinho.garageapi.mapper.UserMapper;
import com.josivaniomarinho.garageapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Create user
    public MessageResponseDTO createUser(UserDTO userDTO){
        User userToSave = userMapper.toModel(userDTO);

        User savedUser = userRepository.save(userToSave);
        return MessageResponseDTO
                .builder()
                .message("User created with ID " + savedUser.getId())
                .build();
    }

    //List all users
    public List<UserDTO> listAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Find user by id
    public UserDTO findUserByID(Long id) throws UserNotFoudException {
        User user = verifyIfExists(id);

        return userMapper.toDTO(user);
    }

    //Delete user by id
    public void deleteUserBYID(Long id) throws UserNotFoudException {
       verifyIfExists(id);

        userRepository.deleteById(id);
    }

    private User verifyIfExists(Long id) throws UserNotFoudException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoudException(id));
    }

}
