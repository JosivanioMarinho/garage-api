package com.josivaniomarinho.garageapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "{missing.fields}")
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "{missing.fields}")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "{missing.fields}")
    @Email(message = "{invalid.fields}")
    private String email;

    @NotEmpty(message = "{missing.fields}")
    private String birthday;

    @NotEmpty(message = "{missing.fields}")
    private String login;

    @NotEmpty(message = "{missing.fields}")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "{missing.fields}")
    @Size(min = 9, max = 12)
    private String phone;

    @Valid
    @NotEmpty
    private List<CarDTO> cars;
}
