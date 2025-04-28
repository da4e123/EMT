package org.example.lab1.service.application.impl;

import org.example.lab1.dto.CreateUserDto;
import org.example.lab1.dto.DisplayUserDto;
import org.example.lab1.dto.LoginUserDto;
import org.example.lab1.model.domain.User;
import org.example.lab1.service.application.UserApplicationService;
import org.example.lab1.service.domain.UserService;
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
        User user = userService.register(createUserDto.username(),
                createUserDto.password(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role());

        return Optional.of(DisplayUserDto.from(user));
    }


    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(loginUserDto.username(),loginUserDto.password())));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
