package com.josivaniomarinho.garageapi.mapper;

import com.josivaniomarinho.garageapi.dto.request.UserDTO;
import com.josivaniomarinho.garageapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "dd-MM-yyyy")
    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
