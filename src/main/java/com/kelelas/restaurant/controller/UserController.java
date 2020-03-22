package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    UserService userService;
    DishService dishService;
    StoriesService storiesService;
    LocaleService localeService;
    IngredientService ingredientService;

    ArrayList<Optional<Dish>> dishes = new  ArrayList<>();
    ArrayList<Ingredient> ingredientList = new ArrayList<>();
    int sum = 0;
    public UserController() {
    }
    @Autowired
    public UserController(IngredientService ingredientService, UserService userService, DishService dishService, StoriesService storiesService, LocaleService localeService) {
        this.userService = userService;
        this.dishService = dishService;
        this.storiesService = storiesService;
        this.localeService = localeService;
        this.ingredientService = ingredientService;
    }



    @GetMapping("/menu")
    public String menuPage(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("dishes", localeService.dishLocale(request));
        model.addAttribute("name", name);
        if (id!=null)
            addDishToOrder(Long.parseLong(id));
        return "/user/menu.html";
    }

    @GetMapping("/bill")
    public String billPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request,  Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
        HistoryItem historyItem;
        model.addAttribute("items", localeService.getStoriesByStatusAndId(request, 2, user().getId()));
        //where status == 2
        model.addAttribute("status", "waiting for pay");
        if (id!=null){
            historyItem = storiesService.getStoryById(Long.parseLong(id)).get();
            if (historyItem.getStatus() == 2) {
                historyItem.setStatus(3);
                storiesService.save(historyItem);
                model.addAttribute("items", localeService.getStoriesByStatusAndId(request, 2, user().getId()));
            }else
                model.addAttribute("error", "error");
            //update status from 2 to 3
        }
        return "/user/bill.html";
    }

    @GetMapping("/history")
    public String historyPage(HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
        model.addAttribute("items", localeService.getStoryLocaleByUserId(request, user().getId()));
        //where stories.userId = user.id
        //if status == 1 than addAttribute("status", "waiting for confirm")
        //if status == 2 than addAttribute("status", "waiting for pay")
        //if status == 3 than addAttribute("status", "finished")
        return "/user/history.html";
    }

    @GetMapping("/cart")
    public String cartPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "ok", required = false) String ok, HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
            model.addAttribute("order", dishes);
            model.addAttribute("sum", sum);
        if (id!=null) {
            deleteDishFromOrder(Long.parseLong(id));
            model.addAttribute("sum", sum);
        }
        if (ok!=null && !dishes.isEmpty() ) {
            if (user().getBalance()>=sum ) {
                saveToStory();
                confirm();
                model.addAttribute("sum", sum);
            }else
                return "redirect:/user/refill";
        }
        return "/user/cart.html";
    }

    @GetMapping("/refill")
    public String refillPage(@RequestParam(value = "ok", required = false) String ok, HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
        User user = user();
        if (ok!=null) {
            user.setBalance(user.getBalance() + 1000);
            userService.save(user);
        }
        return "/user/refill.html";
    }

    public User user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    public void addDishToOrder( Long id){
        Optional<Dish> dish = dishService.getDishById(id);
        if (!dishes.contains(dish))
            dishes.add(dish);
        sum();
    }
    public void deleteDishFromOrder(Long id){
        Optional<Dish> dish = dishService.getDishById(id);
        if (dishes.contains(dish))
            dishes.remove(dish);
        sum();
    }
    public void sum(){
        sum=0;
        for (Optional<Dish> dish : dishes){
            sum += dish.get().getPrice();
        }
    }
    public void confirm(){
        User user = user();
        for (Optional<Dish> dish: dishes) {
            ingredientList.add(ingredientService.getIngredientById(dish.get().getMain_ingredient_id()));
            ingredientList.add(ingredientService.getIngredientById(dish.get().getOff_ingredient_id()));
        }
        for (Ingredient ingredient: ingredientList){
            ingredient.setAmount(ingredient.getAmount()-1);
        }
        user.setBalance(user.getBalance() - sum);
        userService.save(user);
        dishes.clear();
        ingredientList.clear();
        sum=0;
    }
    public void saveToStory(){
        StringBuilder listOfUkrNames = new StringBuilder();
        StringBuilder listOfEngNames = new StringBuilder();
        for (Optional<Dish> dish : dishes){
            listOfEngNames.append(dish.get().getName_eng()).append(", ");
            listOfUkrNames.append(dish.get().getName_ukr()).append(", ");
        }
        listOfEngNames = new StringBuilder(listOfEngNames.substring(0, listOfEngNames.length() - 2));

        listOfUkrNames = new StringBuilder(listOfUkrNames.substring(0, listOfUkrNames.length() - 2));
        HistoryItem historyItem = HistoryItem.builder()
                .date(LocalDateTime.now())
                .price(sum)
                .userid(user().getId())
                .dishes_list_eng(listOfEngNames.toString())
                .dishes_list_ukr(listOfUkrNames.toString())
                .status(1)
                .build();
        storiesService.save(historyItem);
    }

}
