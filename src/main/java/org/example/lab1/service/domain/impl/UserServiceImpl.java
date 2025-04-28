package org.example.lab1.service.domain.impl;


import org.example.lab1.model.domain.User;
import org.example.lab1.model.enums.Role;
import org.example.lab1.model.exceptions.InvalidCredentialsException;
import org.example.lab1.repository.UserRepository;
import org.example.lab1.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String name, String surname, Role role) {
        if (username == null || username.isEmpty() && password == null || password.isEmpty()){
            throw new InvalidCredentialsException();
        }
        User user = new User(username,passwordEncoder.encode(password),name,surname,role);

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) throws InvalidCredentialsException {
        if (username == null || username.isEmpty() && password == null || password == null){
            throw new RuntimeException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->  new UsernameNotFoundException(username));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }



}
