package org.example.lab1.service.domain;
import org.example.lab1.model.enums.Role;
import org.example.lab1.model.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    User register(String username, String password, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);


}
