package org.example.lab1.service.application.impl;

import org.example.lab1.dto.CreateUserDto;
import org.example.lab1.dto.DisplayUserDto;
import org.example.lab1.model.dto.login.LoginUserDto;
import org.example.lab1.model.domain.User;
import org.example.lab1.model.dto.login.LoginResponseDto;
import org.example.lab1.model.projections.UserProjection;
import org.example.lab1.security.JwtHelper;
import org.example.lab1.service.application.UserApplicationService;
import org.example.lab1.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
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
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto.username(),loginUserDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));

    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return userService.getAllUserNames();
    }

    @Override
    public List<User> fetchAll() {
        return userService.fetchAll();
    }
}
