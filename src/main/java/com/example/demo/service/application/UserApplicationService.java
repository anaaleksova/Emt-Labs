package com.example.demo.service.application;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.DisplayUserDto;
import com.example.demo.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}

