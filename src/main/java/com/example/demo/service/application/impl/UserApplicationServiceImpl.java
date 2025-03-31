package com.example.demo.service.application.impl;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.DisplayUserDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.model.domain.User;
import com.example.demo.service.application.UserApplicationService;
import com.example.demo.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}
