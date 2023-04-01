package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.dto.UserDto;
import com.example.kiemtrajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.example.kiemtrajpa.dto.UserDto(u.id,u.name,u.email) from User u")
    List<UserDto> findAllUserDto();
}