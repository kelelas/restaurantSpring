package com.kelelas.restaurant.service;

import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.entity.locale.DishLocale;
import com.kelelas.restaurant.entity.locale.IngredientLocale;
import com.kelelas.restaurant.entity.locale.StoryItemLocale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class LocaleService {
    DishService dishService;
    IngredientService ingredientService;
    StoriesService storiesService;
//    HttpServletRequest request;
    @Autowired
    public LocaleService(DishService dishService, IngredientService ingredientService, StoriesService storiesService) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.storiesService = storiesService;
    }




    public String name(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return user.getName_ukr();
        else
            return user.getName_eng();

    }


    public List<DishLocale> dishLocale(HttpServletRequest request){
        List<Dish> list = dishService.getAllDishes();
        List<DishLocale> dishLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
            for (Dish dish : list){
                dishLocaleList.add(DishLocale.builder()
                .image(dish.getImage())
                .name(dish.getName_ukr())
                .price(dish.getPrice())
                .id(dish.getId())
                .main_ingredient(ingredientService.getIngredientById(dish.getMain_ingredient_id()).getName_ukr())
                .off_ingredient(ingredientService.getIngredientById(dish.getOff_ingredient_id()).getName_ukr())
                .build());
            }
        }else {
            for (Dish dish : list){
                dishLocaleList.add(DishLocale.builder()
                        .image(dish.getImage())
                        .name(dish.getName_eng())
                        .price(dish.getPrice())
                        .id(dish.getId())
                        .main_ingredient(ingredientService.getIngredientById(dish.getMain_ingredient_id()).getName_eng())
                        .off_ingredient(ingredientService.getIngredientById(dish.getOff_ingredient_id()).getName_eng())
                        .build());
            }
        }
     return dishLocaleList;
    }
    public Optional<DishLocale> getDishById(HttpServletRequest request, Long id){
        Dish dish = dishService.getDishById(id).get();
        Optional<DishLocale> dishLocale;
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))) {
            dishLocale = Optional.of(DishLocale.builder()
                    .image(dish.getImage())
                    .name(dish.getName_ukr())
                    .price(dish.getPrice())
                    .id(dish.getId())
                    .main_ingredient(ingredientService.getIngredientById(dish.getMain_ingredient_id()).getName_ukr())
                    .off_ingredient(ingredientService.getIngredientById(dish.getOff_ingredient_id()).getName_ukr())
                    .build());
        }else {
            dishLocale = Optional.of(DishLocale.builder()
                    .image(dish.getImage())
                    .name(dish.getName_eng())
                    .price(dish.getPrice())
                    .id(dish.getId())
                    .main_ingredient(ingredientService.getIngredientById(dish.getMain_ingredient_id()).getName_eng())
                    .off_ingredient(ingredientService.getIngredientById(dish.getOff_ingredient_id()).getName_eng())
                    .build());
        }
        return dishLocale;
    }





    public List<StoryItemLocale> storyLocale(HttpServletRequest request){
        List<HistoryItem> list =storiesService.getAllStories();
        List<StoryItemLocale> storiesLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_ukr())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }else {
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_eng())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }
        return storiesLocaleList;
    }
    public Optional<StoryItemLocale> getStoryById(HttpServletRequest request, Long id){
        HistoryItem historyItem = storiesService.getStoryById(id).get();
        Optional<StoryItemLocale> storyItemLocale;
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
                storyItemLocale = Optional.of(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_ukr())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
        }else {
                storyItemLocale = Optional.of(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_eng())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
        }
        return storyItemLocale;
    }

    public List<StoryItemLocale> getStoryLocaleByUserId(HttpServletRequest request, Long userid){
        List<HistoryItem> list =storiesService.getStoryByUserId(userid);
        List<StoryItemLocale> storiesLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_ukr())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }else {
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_eng())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }
        return storiesLocaleList;
    }
    public List<StoryItemLocale> getStoriesLocaleByStatus(HttpServletRequest request, int status){
        List<HistoryItem> list =storiesService.getStoriesByStatus(status);
        List<StoryItemLocale> storiesLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_ukr())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }else {
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_eng())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }
        return storiesLocaleList;
    }
    public List<StoryItemLocale> getStoriesByStatusAndId(HttpServletRequest request, int status, Long id){
        List<HistoryItem> list =storiesService.getStoriesByStatusAndId(status, id);
        List<StoryItemLocale> storiesLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_ukr())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }else {
            for (HistoryItem historyItem : list){
                storiesLocaleList.add(StoryItemLocale.builder()
                        .date(historyItem.getDate())
                        .dishes(historyItem.getDishes_list_eng())
                        .id(historyItem.getId())
                        .price(historyItem.getPrice())
                        .status(historyItem.getStatus())
                        .userId(historyItem.getUserid())
                        .build());
            }
        }
        return storiesLocaleList;
    }




    public List<IngredientLocale> ingredientLocale(HttpServletRequest request){
    List<Ingredient> list =ingredientService.getAllIngredients();
    List<IngredientLocale> ingredientLocaleList = new ArrayList<>();
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua"))){
        for (Ingredient ingredient : list){
            ingredientLocaleList.add(IngredientLocale.builder()
                    .id(ingredient.getId())
                    .amount(ingredient.getAmount())
                    .max_amount(ingredient.getMax_amount())
                    .name(ingredient.getName_ukr())
                    .build());
        }
    }else {
            for (Ingredient ingredient : list){
               ingredientLocaleList.add(IngredientLocale.builder()
                        .id(ingredient.getId())
                        .amount(ingredient.getAmount())
                        .max_amount(ingredient.getMax_amount())
                        .name(ingredient.getName_eng())
                        .build());
        }
    }
        return ingredientLocaleList;
}
}
