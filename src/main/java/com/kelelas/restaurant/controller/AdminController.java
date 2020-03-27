package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.DishService;
import com.kelelas.restaurant.service.IngredientService;
import com.kelelas.restaurant.service.StoriesService;
import com.kelelas.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    StoriesService storiesService;
    DishService dishService;
    UserService userService;
    IngredientService ingredientService;

    public AdminController() {
    }
    @Autowired
    public AdminController(StoriesService storiesService, DishService dishService, IngredientService ingredientService, UserService userService) {
        this.storiesService = storiesService;
        this.dishService = dishService;
        this.userService = userService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/orders_list")
    public String ordersPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("name", name);
        HistoryItem historyItem;
        model.addAttribute("items", storiesService.getLocaleStoriesByStatus(request, 1));
        model.addAttribute("status", "waiting for confirm");
        if (id!=null) {
            historyItem = storiesService.getStoryById(Long.parseLong(id)).get();
            if (historyItem.getStatus() == 1) {
                confirm(historyItem.getDishes_list_eng());
                historyItem.setStatus(2);
                storiesService.save(historyItem);
                model.addAttribute("items", storiesService.getLocaleStoriesByStatus(request, 1));
            }else
                model.addAttribute("error", "error");
        }
        return "/admin/orders_list.html";
    }

    @GetMapping("/statistics")
    public String statisticsPage( HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("name", name);
        model.addAttribute("items",storiesService.getLocaleStories( request));
        return "/admin/statistics.html";
    }

    @GetMapping("/update_ingredients")
    public String cartPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = name(request);
        model.addAttribute("items", ingredientService.getLocaleIngredients(request));
        model.addAttribute("name", name);
        if (id!=null) {
            Ingredient ingredient = ingredientService.getIngredientById(Long.parseLong(id));
            ingredient.setAmount(ingredient.getMax_amount());
            ingredientService.save(ingredient);
            model.addAttribute("items", ingredientService.getLocaleIngredients(request));
        }
        return "/admin/update_ingredients.html";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, HttpServletRequest request, Model model){
        if (filter!=null && !filter.isEmpty()) {
            try {
                // model.addAttribute("items", storiesService.filterByDate(new LocalDate(filter)));
                model.addAttribute("items", storiesService.getLocaleStoriesByUserId(request, Long.parseLong(filter)));
                model.addAttribute("filterValue", filter);
            }catch (Exception e){
                model.addAttribute("items", storiesService.getLocaleStories(request));

            }

        }else {
            model.addAttribute("items", storiesService.getLocaleStories(request));
        }
        return "/admin/statistics.html";

    }

    public String name(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return user.getName_ukr();
        else
            return user.getName_eng();

    }

    public void confirm(String dishes){
        ArrayList<Dish> dishes1 = new ArrayList<>();
        String[] dishesName = dishes.split(", ");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        for(String dishName : dishesName){
            dishes1.add(dishService.getDishByName(dishName));
        }
        for (Dish dish : dishes1){
            ingredientList.add(ingredientService.getIngredientById(dish.getMain_ingredient_id()));
            ingredientList.add(ingredientService.getIngredientById(dish.getOff_ingredient_id()));
        }
        for (Ingredient ingredient: ingredientList){
            ingredient.setAmount(ingredient.getAmount()-1);
        }

    }
}

