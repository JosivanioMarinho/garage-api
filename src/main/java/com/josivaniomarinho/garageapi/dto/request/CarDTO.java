package com.josivaniomarinho.garageapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long id;

    @NotNull(message = "{missing.fields}")
    private Integer year;

    @NotEmpty(message = "{missing.fields}")
    private String licensePlate;

    @NotEmpty(message = "{missing.fields}")
    private String model;

    @NotEmpty(message = "{missing.fields}")
    private String color;
}
