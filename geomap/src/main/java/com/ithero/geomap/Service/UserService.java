package com.ithero.geomap.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ithero.geomap.DTO.UserRegistrationDto;
import com.ithero.geomap.Entity.User.Role;
import com.ithero.geomap.Entity.User.User;
import com.ithero.geomap.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByLogin(registrationDto.getLogin())) {
            throw new IllegalArgumentException("Login already exists");
        }
        
        User user = new User();
        user.setLogin(registrationDto.getLogin());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        
        return userRepository.save(user);
    }
}