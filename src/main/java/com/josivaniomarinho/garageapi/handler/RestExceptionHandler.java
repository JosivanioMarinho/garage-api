package com.josivaniomarinho.garageapi.handler;

import com.josivaniomarinho.garageapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.garageapi.exception.UserExistsEmailAndLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    //Exception if email and long exists
    @ExceptionHandler(UserExistsEmailAndLoginException.class)
    public ResponseEntity<?> handlerUnprocessableEntity(UserExistsEmailAndLoginException emailAndLoginException){
        MessageResponseDTO mrDTO = MessageResponseDTO
                .builder()
                .message(emailAndLoginException.getMessage())
                .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .build();

        return new ResponseEntity<>(mrDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining());
        MessageResponseDTO mrDTO = MessageResponseDTO
                .builder()
                .message(fieldMessage)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(mrDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
