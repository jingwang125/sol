package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.UserDto;

@Mapper
public interface UserMapper {
    void insertUser(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto findById(Long id);
    int countByEmail(String email);
}
