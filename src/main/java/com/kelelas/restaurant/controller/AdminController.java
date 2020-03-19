package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @RequestMapping("/orders_list")
    public String ordersPage(){
        return "/admin/orders_list.html";
    }

    @GetMapping("/bill_confirm")
    public String billConfirmPage(){
        return "/admin/bill_confirm.html";
    }

    @GetMapping("/statistics")
    public String statisticsPage(){
        return "/admin/statistics.html";
    }

    @GetMapping("/update_ingredients")
    public String cartPage(){
        return "/admin/update_ingredients.html";
    }


}
