package com.josivaniomarinho.garageapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private String message;
    private int httpStatus;
}
