package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.DishService;
import com.kelelas.restaurant.service.StoriesService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    DishService dishService;
    StoriesService storiesService;
    private List<Long> listOfOrders= new ArrayList<>();
    public UserController() {
    }
    @Autowired
    public UserController(DishService dishService, StoriesService storiesService) {
        this.dishService = dishService;
        this.storiesService = storiesService;
    }


    @GetMapping("/menu")
    public String menuPage(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("name", name);
        if (id!=null)
        listOfOrders.add(Long.parseLong(id));
        for (Long i : listOfOrders){
            System.out.println(i);
        }
        return "/user/menu.html";
    }

    @GetMapping("/bill")
    public String billPage(){
        return "/user/bill.html";
    }

    @GetMapping("/history")
    public String historyPage(Model model){
        model.addAttribute("items", storiesService.getAllStories());
        return "/user/history.html";
    }

    @GetMapping("/cart")
    public String cartPage(Model model){
        List<Optional<Dish>> dishes = new  ArrayList<>();
        for (Long i : listOfOrders){
            dishes.add(dishService.getDishById(i));
        }
        model.addAttribute("dishes", dishes);
        return "/user/cart.html";
    }

    @GetMapping("/refill")
    public String refillPage(){
        return "/user/refill.html";
    }


public String name(HttpServletRequest request){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
        return user.getName_ukr();
    else
        return user.getName_eng();

}

    @PostMapping(value = "/refill")
    public RedirectView refillMethod(@RequestParam(value = "refilled", required = false) String refiled, Model model){
        RedirectView redirectView = new RedirectView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        int countOfMoney = user.getBalance();
        user.setBalance(countOfMoney + 1000);
        redirectView.setUrl("/refill?refilled=true");
        return redirectView;
    }
}
