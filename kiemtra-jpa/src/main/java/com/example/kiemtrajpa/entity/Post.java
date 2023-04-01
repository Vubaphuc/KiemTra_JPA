package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
@NamedQuery(name = "findPostByName",
            query = "select p from Post p where p.name = :name")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    private String title;




    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

}