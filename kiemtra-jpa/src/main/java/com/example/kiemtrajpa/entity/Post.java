package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
@NamedQuery(name = "findPostByName",
            query = "select p from Post p where p.name = :name")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    private String title;

    @Column(name = "email")
    private String email;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (updatedDate == null) {
            updatedDate = LocalDateTime.now();
        }
    }

}