package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.dto.request.UserDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.exception.UserExistsEmailAndLoginException;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
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
        verifyIfEmailAndLoginExists(userDTO.getEmail(), userDTO.getLogin());

        User userToSave = userMapper.toModel(userDTO);

        User savedUser = userRepository.save(userToSave);
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

}
