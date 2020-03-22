package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.config.Regex;
import com.kelelas.restaurant.dto.UserDTO;
import com.kelelas.restaurant.entity.RoleType;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class RegistrationController {
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/registration")
    public RedirectView RegFormController(@RequestParam(value = "error", required = false) String error, UserDTO user, Model model) {
        RedirectView redirectView = new RedirectView();
        if (verify(user)) {
            boolean resultOfSave = registrationService.saveNewUser(User.builder()
                    .name_ukr(user.getName_ukr())
                    .name_eng(user.getName_eng())
                    .email(user.getEmail())
                    .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                    .role(RoleType.USER)
                    .isActive(true)
                    .balance(5000)
                    .build());
            if (resultOfSave) {
                log.info("{}", user);
                redirectView.setUrl("/login");
            } else {
                redirectView.setUrl("/registration?error=userAlreadyExist");
            }
        }else
            redirectView.setUrl("/registration?regex=error");
        return redirectView;
    }
    public boolean verify(UserDTO user){
        return user.getEmail().matches(Regex.EMAIL_REGEX)
                && user.getName_eng().matches(Regex.NAME_REGEX)
                && user.getName_ukr().matches(Regex.NAME_UKR_REGEX)
                && user.getPassword().matches(Regex.PASSWORD_REGEX);

    }
}
