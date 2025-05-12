package org.example.lab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.example.lab1.dto.CreateUserDto;
import org.example.lab1.dto.DisplayUserDto;
import org.example.lab1.model.dto.login.LoginResponseDto;
import org.example.lab1.model.dto.login.LoginUserDto;
import org.example.lab1.model.exceptions.InvalidCredentialsException;
import org.example.lab1.model.projections.UserProjection;
import org.example.lab1.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for managing users")

public class UserController {

    public final UserApplicationService userApplicationService;


    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto){
        return userApplicationService.register(createUserDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        LoginResponseDto body = userApplicationService.login(loginUserDto).orElseThrow();
        return ResponseEntity.ok(body);
    }

//    @Operation(summary = "User logout", description = "End's the user's session")
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request){
//        request.getSession().invalidate();
//    }

    @GetMapping("/names")
    public List<UserProjection> getAllUserNames(){
        return userApplicationService.getAllUserNames();
    }

    @Operation(summary = "Fetched all users")
    @GetMapping("users")

    public ResponseEntity<?> fetchAll(HttpServletRequest request){
        return ResponseEntity.ok(userApplicationService.fetchAll());
    }

}
