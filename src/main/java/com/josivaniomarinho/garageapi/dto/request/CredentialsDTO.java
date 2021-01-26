package com.josivaniomarinho.garageapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
