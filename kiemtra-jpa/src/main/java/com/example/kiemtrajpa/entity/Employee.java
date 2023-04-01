package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "emailAddress", unique = true)
    private String emailAddress;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;


}