package com.josivaniomarinho.garageapi.repository;

import com.josivaniomarinho.garageapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}