package com.josivaniomarinho.garageapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}