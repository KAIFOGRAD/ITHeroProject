package com.ithero.geomap.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ithero.geomap.Entity.User.Role;
import com.ithero.geomap.Entity.User.User;
import com.ithero.geomap.Repository.UserRepository;

public class UserService {

    private final UserRepository userRepository; 

    private final PasswordEncoder passwordEncoder; 

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userDTO) {

      //  if (isUserExists(userDTO)) {
       //     throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
//        }

        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); 
        user.setRole(Role.ROLE_USER); 

        return userRepository.save(user);

    }

    public boolean isUserExists(User userDTO) {

        return userRepository.existsByLogin(userDTO.getLogin());

    }
}
