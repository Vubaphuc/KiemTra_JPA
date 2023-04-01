package com.example.kiemtrajpa.service;


import com.example.kiemtrajpa.dto.UserDto;
import com.example.kiemtrajpa.entity.User;
import com.example.kiemtrajpa.mapper.UserMapper;
import com.example.kiemtrajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

}
