package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.config.ConstantBundle;
import com.kelelas.restaurant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    UserService userService;
    DishService dishService;
    HistoryService storiesService;
    UserPageService userPageService;
    IngredientService ingredientService;
    BillService billService;

    @Autowired
    public UserController(BillService billService, UserPageService userPageService, IngredientService ingredientService, UserService userService, DishService dishService, HistoryService storiesService) {
        this.userPageService = userPageService;
        this.userService = userService;
        this.dishService = dishService;
        this.storiesService = storiesService;
        this.ingredientService = ingredientService;
        this.billService=billService;
    }



    @GetMapping("/menu")
    public String menuPage(Model model){
        model.addAttribute("user", userPageService.user());
        model.addAttribute("dishes", dishService.getLocaleDishes());
        return "/user/menu.html";
    }

    @PostMapping(value = "/addToOrder{orderId}")
    public String updateOrder(@PathVariable String orderId){
        try {
            userPageService.addDishToOrder(Long.parseLong(orderId));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/user/menu";
    }


    @GetMapping("/bill")
    public String billPage(@RequestParam(name = "id", required = false) String id,  Model model){
        model.addAttribute("items", billService.getLocaleBillsByStatusAndUserId( (long) ConstantBundle.getIntProperty("status.waitingForPay"), userPageService.user().getId()));
        model.addAttribute("sum", userPageService.sum());
        model.addAttribute("user", userPageService.user());

        return "/user/bill.html";
    }

    @PostMapping(value = "/payForOrder{orderId}")
    public String payForOrder(@PathVariable String orderId){
        try {
            userPageService.payForOrderById(orderId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/user/bill";
    }


    @GetMapping("/history")
    public String historyPage( Model model, @PageableDefault(size = 5, sort = "date", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("user", userPageService.user());
        model.addAttribute("items", storiesService.getLocaleStoriesByUserId(userPageService.user().getId(), pageable));
        return "/user/history.html";
    }

    @GetMapping("/cart")
    public String cartPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "ok", required = false) String ok, Model model){
            model.addAttribute("order", userPageService.localDishes());
            model.addAttribute("user", userPageService.user());
            model.addAttribute("sum", userPageService.sum());
        return "/user/cart.html";
    }

    @PostMapping(value = "/confirm")
    public String confirm(){
        try {
            userPageService.confirm();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/user/cart?success=true";
    }

    @PostMapping(value = "/deleteFromOrder{orderId}")
    public String deleteFromOrder(@PathVariable String orderId){
        try {
            userPageService.deleteDishFromOrder(Long.parseLong(orderId));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/user/cart";
    }

    @GetMapping("/refill")
    public String refillPage(@RequestParam(value = "ok", required = false) String ok, Model model){
        model.addAttribute("user", userPageService.user());
        if (ok!=null) {
            userPageService.refillUserBalance();
        }
        return "/user/refill.html";
    }

}
