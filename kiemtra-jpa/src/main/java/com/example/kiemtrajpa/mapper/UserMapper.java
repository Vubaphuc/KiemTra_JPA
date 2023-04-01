package com.example.kiemtrajpa.mapper;

import com.example.kiemtrajpa.dto.UserDto;
import com.example.kiemtrajpa.entity.User;

public class UserMapper {

    public static UserDto toUserDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
