package com.josivaniomarinho.garageapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class JwtResponseDTO implements Serializable {

    private final String jwtToken;

}
