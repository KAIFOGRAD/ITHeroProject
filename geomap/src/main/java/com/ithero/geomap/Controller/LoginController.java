package com.ithero.geomap.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ithero.geomap.Entity.User.User;
import com.ithero.geomap.Repository.UserRepository;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder; 

    @Autowired
    public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, Model model) {
        User user = userRepository.findByLogin(login).getFirst();
       

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("message", "Login successful!");
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "login"; 
        }
    }
}
