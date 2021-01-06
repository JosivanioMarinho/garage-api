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

    @NotEmpty(message = "{firstName.not.empty}")
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "{lastName.not.empty}")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "{email.not.empty}")
    @Email(message = "{email.valid.email}")
    private String email;

    @NotEmpty(message = "{birthday.not.empty}")
    private String birthday;

    @NotEmpty(message = "{login.not.empty}")
    private String login;

    @NotEmpty(message = "{password.not.empty}")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "{phone.not.empty}")
    @Size(min = 9, max = 12)
    private String phone;

    @Valid
    @NotEmpty
    private List<CarDTO> cars;
}
