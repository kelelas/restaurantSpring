package com.kelelas.restaurant.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("isAnonymous()")
@Controller
public class GuestController {
    @GetMapping("/")
    public String mainPage(){
        return "/guest/main.html";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model){
        model.addAttribute("error", error!=null);
        model.addAttribute("logout", logout!=null);
        return "/guest/login.html";
    }

    @GetMapping("/registration")
    public String regPage(){
        return "/guest/registration.html";
    }
}
