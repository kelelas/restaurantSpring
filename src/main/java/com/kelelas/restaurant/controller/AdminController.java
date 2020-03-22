package com.kelelas.restaurant.controller;

import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.service.DishService;
import com.kelelas.restaurant.service.IngredientService;
import com.kelelas.restaurant.service.LocaleService;
import com.kelelas.restaurant.service.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    StoriesService storiesService;
    DishService dishService;
    LocaleService localeService;
    IngredientService ingredientService;

    public AdminController() {
    }
    @Autowired
    public AdminController(StoriesService storiesService, DishService dishService, LocaleService localeService, IngredientService ingredientService) {
        this.storiesService = storiesService;
        this.dishService = dishService;
        this.localeService = localeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/orders_list")
    public String ordersPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
        HistoryItem historyItem;
        model.addAttribute("items", localeService.getStoriesLocaleByStatus(request, 1));
        model.addAttribute("status", "waiting for confirm");
        if (id!=null) {
            historyItem = storiesService.getStoryById(Long.parseLong(id)).get();
            if (historyItem.getStatus() == 1) {
                historyItem.setStatus(2);
                storiesService.save(historyItem);
                model.addAttribute("items", localeService.getStoriesLocaleByStatus(request, 1));
            }else
                model.addAttribute("error", "error");
        }
        return "/admin/orders_list.html";
    }

    @GetMapping("/statistics")
    public String statisticsPage( HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("name", name);
        model.addAttribute("items", localeService.getStoriesLocaleByStatus(request, 3));
        return "/admin/statistics.html";
    }

    @GetMapping("/update_ingredients")
    public String cartPage(@RequestParam(name = "id", required = false) String id, HttpServletRequest request, Model model){
        String name = localeService.name(request);
        model.addAttribute("items", localeService.ingredientLocale(request));
        model.addAttribute("name", name);
        if (id!=null) {
            Ingredient ingredient = ingredientService.getIngredientById(Long.parseLong(id));
            ingredient.setAmount(ingredient.getMax_amount());
            ingredientService.save(ingredient);
            model.addAttribute("items", localeService.ingredientLocale(request));
        }
        return "/admin/update_ingredients.html";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, HttpServletRequest request, Model model){
        if (filter!=null && !filter.isEmpty()) {
            try {
                // model.addAttribute("items", storiesService.filterByDate(new LocalDate(filter)));
                model.addAttribute("items", localeService.getStoryLocaleByUserId(request, Long.parseLong(filter)));
                model.addAttribute("filterValue", filter);
            }catch (Exception e){
                model.addAttribute("items", localeService.storyLocale(request));

            }

        }else {
            model.addAttribute("items", localeService.storyLocale(request));
        }
        return "/admin/statistics.html";

    }

}

