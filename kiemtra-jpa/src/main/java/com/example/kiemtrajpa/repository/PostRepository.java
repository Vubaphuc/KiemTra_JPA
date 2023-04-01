package com.example.kiemtrajpa.repository;


import com.example.kiemtrajpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.name = ?1")
    List<Post> findPostByName(String name);


    // NamedQuery
    @Query(nativeQuery = true, value = "findPostByName")
    List<Post> findPostByName_native(@Param("name") String name);
}