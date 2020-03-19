package com.kelelas.restaurant.controller;


import com.kelelas.restaurant.entity.RoleType;
import com.kelelas.restaurant.entity.User;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PageController {

    @GetMapping("/afterlogin")
    public RedirectView mainPage(Model model){
        RedirectView redirectView = new RedirectView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (user.getRole().equals(RoleType.ADMIN)){
            redirectView.setUrl("/admin/statistics");

        }else if (user.getRole().equals(RoleType.USER)){
            redirectView.setUrl("/user/menu");
        }else {
            redirectView.setUrl("/");
        }
        return redirectView;
    }

//    public RoleType role(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        return user.getRole();
//    }
}
