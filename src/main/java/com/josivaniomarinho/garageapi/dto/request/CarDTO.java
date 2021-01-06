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

    @NotNull(message = "{year.not.empty}")
    private Integer year;

    @NotEmpty(message = "{licensePlate.not.empty}")
    private String licensePlate;

    @NotEmpty(message = "{model.not.empty}")
    private String model;

    @NotEmpty(message = "{color.not.empty}")
    private String color;
}
