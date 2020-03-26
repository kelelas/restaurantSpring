package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    UserService userService;
    DishService dishService;
    StoriesService storiesService;
    IngredientService ingredientService;

    ArrayList<Long> dishesId = new  ArrayList<>();
    ArrayList<Ingredient> ingredientList = new ArrayList<>();
    int sum = 0;
    public UserController() {
    }
    @Autowired
    public UserController(IngredientService ingredientService, UserService userService, DishService dishService, StoriesService storiesService) {
        this.userService = userService;
        this.dishService = dishService;
        this.storiesService = storiesService;
        this.ingredientService = ingredientService;
    }



    @GetMapping("/menu")
    public String menuPage(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("dishes", dishService.getLocaleDishes(request));
        model.addAttribute("name", name);
        if (id!=null)
            addDishToOrder(Long.parseLong( id));
        return "/user/menu.html";
    }

    @GetMapping("/bill")
    public String billPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request,  Model model){
        String name = name(request);
        model.addAttribute("name", name);
        HistoryItem historyItem;
        model.addAttribute("items", storiesService.getLocaleStoriesByStatusAndUserId(request, 2, user().getId()));
        //where status == 2
        model.addAttribute("status", "waiting for pay");
        if (id!=null){
            historyItem = storiesService.getStoryById(Long.parseLong(id)).get();
            // historyItem = storiesService.getStoryById(Long.parseLong(id)).isPresent() ? storiesService.getStoryById(Long.parseLong(id)).get() : log.info("{Почтовый адрес уже существует}");;
            if (historyItem.getStatus() == 2) {
                historyItem.setStatus(3);
                storiesService.save(historyItem);
                model.addAttribute("items", storiesService.getLocaleStoriesByStatusAndUserId(request, 2, user().getId()));
            }else
                model.addAttribute("error", "error");
            //update status from 2 to 3
        }
        return "/user/bill.html";
    }

    @GetMapping("/history")
    public String historyPage(HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("name", name);
        model.addAttribute("items", storiesService.getLocaleStoriesByUserId(request, user().getId()));
        //where stories.userId = user.id
        //if status == 1 than addAttribute("status", "waiting for confirm")
        //if status == 2 than addAttribute("status", "waiting for pay")
        //if status == 3 than addAttribute("status", "finished")
        return "/user/history.html";
    }

    @GetMapping("/cart")
    public String cartPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "ok", required = false) String ok, HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("name", name);
            model.addAttribute("order", dishes(request));
            model.addAttribute("sum", sum);
        if (id!=null) {
            deleteDishFromOrder(Long.parseLong(id));
            model.addAttribute("order", dishes(request));
            model.addAttribute("sum", sum);
        }
        if (ok!=null && !dishesId.isEmpty() ) {
            if (user().getBalance()>=sum ) {
                saveToStory();
                confirm(request);
                model.addAttribute("order", dishes(request));
                model.addAttribute("sum", sum);
            }else
                return "redirect:/user/refill";
        }
        return "/user/cart.html";
    }

    @GetMapping("/refill")
    public String refillPage(@RequestParam(value = "ok", required = false) String ok, HttpServletRequest request, Model model){
        String name = name(request);
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
        if (!dishesId.contains(id))
            dishesId.add(id);
        sum();
    }
    public void deleteDishFromOrder(Long id){
        if (dishesId.contains(id))
            dishesId.remove(id);
        sum();
    }
    public void sum(){
        sum=0;
        for (Long id : dishesId){
            sum += dishService.getDishById(id).get().getPrice();
        }
    }
    public void confirm(HttpServletRequest request){
        User user = user();
        for (DishDTO dish: dishes(request)) {
            ingredientList.add(ingredientService.getIngredientById(dish.getMain_ingredient_id()));
            ingredientList.add(ingredientService.getIngredientById(dish.getOff_ingredient_id()));
        }
        for (Ingredient ingredient: ingredientList){
            ingredient.setAmount(ingredient.getAmount()-1);
        }
        user.setBalance(user.getBalance() - sum);
        userService.save(user);
        dishesId.clear();
        ingredientList.clear();
        sum=0;
    }
    public List<DishDTO> dishes(HttpServletRequest request){
        ArrayList<DishDTO> dishes = new ArrayList<>();
        for (Long id : dishesId){
            dishes.add(dishService.getOneLocaleDish(request, id));
        }
        return dishes;
    }
    public void saveToStory(){
        StringBuilder listOfUkrNames = new StringBuilder();
        StringBuilder listOfEngNames = new StringBuilder();
        for (Long id : dishesId){
            listOfEngNames.append(dishService.getDishById(id).get().getName_eng()).append(", ");
            listOfUkrNames.append(dishService.getDishById(id).get().getName_ukr()).append(", ");
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
    public String name(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return user.getName_ukr();
        else
            return user.getName_eng();

    }
}
