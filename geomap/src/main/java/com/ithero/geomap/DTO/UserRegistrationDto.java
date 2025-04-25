package com.ithero.geomap.DTO;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank(message = "Логин обязателен")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
    private String login;
    
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;
    
}