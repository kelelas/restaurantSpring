package com.kelelas.restaurant.controller;


import com.kelelas.restaurant.entity.RoleType;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PageController {
    DishService dishService;
    @Autowired
    public PageController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/afterlogin")
    public RedirectView mainPage(){
        RedirectView redirectView = new RedirectView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (user.getRole().equals(RoleType.ADMIN)){
            redirectView.setUrl("/admin/orders_list");

        }else if (user.getRole().equals(RoleType.USER)){
            redirectView.setUrl("/user/menu");
        }else {
            redirectView.setUrl("/");
        }
        return redirectView;
    }

}
