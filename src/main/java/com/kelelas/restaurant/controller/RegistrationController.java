package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.config.Regex;
import com.kelelas.restaurant.dto.UserDTO;
import com.kelelas.restaurant.entity.RoleType;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class RegistrationController {
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public String RegFormController(UserDTO user) {
        if (verify(user)) {
            try {
                userService.save(User.builder()
                        .nameUkr(user.getNameUkr())
                        .nameEng(user.getNameEng())
                        .email(user.getEmail())
                        .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                        .role(RoleType.USER)
                        .isActive(true)
                        .balance(5000)
                        .build());
                return "redirect:/login";
            } catch (Exception e) {
                return "redirect:/registration?error=userAlreadyExist";
            }
        }else
            return "redirect:/registration?regex=error";
    }
    public boolean verify(UserDTO user){
        return user.getEmail().matches(Regex.EMAIL_REGEX)
                && user.getNameEng().matches(Regex.NAME_REGEX)
                && user.getNameUkr().matches(Regex.NAME_UKR_REGEX)
                && user.getPassword().matches(Regex.PASSWORD_REGEX);

    }
}
