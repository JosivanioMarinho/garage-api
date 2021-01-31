package com.josivaniomarinho.garageapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;


    /*
    @OneToMany(mappedBy = "USER")
    @JoinTable(name = "USER_CAR",
            joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName="id") },
        inverseJoinColumns = {
            @JoinColumn(name = "CAR_ID", referencedColumnName="id")}
    )
    */
    //@OneToMany(mappedBy = "USER",fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "user")
    private List<Car> cars;
}