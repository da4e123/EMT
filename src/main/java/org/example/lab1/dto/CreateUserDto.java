package org.example.lab1.dto;

import org.example.lab1.model.domain.User;
import org.example.lab1.model.enums.Role;

public record CreateUserDto(String username, String password, String name, String surname, Role role) {

    public User toUser(){
        return new User(username,password,name,surname,role);
    }
}
