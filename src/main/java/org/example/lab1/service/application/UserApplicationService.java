package org.example.lab1.service.application;

import org.example.lab1.dto.CreateUserDto;
import org.example.lab1.dto.DisplayUserDto;
import org.example.lab1.dto.LoginUserDto;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
