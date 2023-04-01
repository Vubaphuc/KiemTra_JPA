package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.name = ?1")
    List<Post> findPostByName(String name);
}