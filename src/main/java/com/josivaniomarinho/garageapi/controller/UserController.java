package com.josivaniomarinho.garageapi.controller;

import com.josivaniomarinho.garageapi.dto.request.UserDTO;
import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.exception.UserNotFoundException;
import com.josivaniomarinho.garageapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createdUser(@RequestBody @Valid UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> listAll(){
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDTO findUserByID(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserByID(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateUserBYID(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException {
        return userService.updateUserByID(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByID(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUserBYID(id);
    }
}
