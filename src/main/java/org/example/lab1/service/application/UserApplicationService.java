package org.example.lab1.service.application;

import org.example.lab1.dto.CreateUserDto;
import org.example.lab1.dto.DisplayUserDto;
import org.example.lab1.model.dto.login.LoginUserDto;
import org.example.lab1.model.domain.User;
import org.example.lab1.model.dto.login.LoginResponseDto;
import org.example.lab1.model.projections.UserProjection;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    List<UserProjection> getAllUserNames();

    List<User> fetchAll();
}
