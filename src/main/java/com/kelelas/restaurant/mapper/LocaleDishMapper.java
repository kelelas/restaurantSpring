package com.kelelas.restaurant.mapper;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LocaleDishMapper extends LocalizedMapper<Dish, DishDTO> {
    @Override
    protected DishDTO toEngDto(Dish entity) {
        DishDTO dishDTO = DishDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getNameEng())
                .price(entity.getPrice())
                .ingredients(new ArrayList<>())
                .build();
        for (Ingredient ingredient : entity.getIngredients()){
            IngredientDTO ingredientDTO = IngredientDTO.builder()
                    .id(ingredient.getId())
                    .amount(ingredient.getAmount())
                    .maxAmount(ingredient.getMaxAmount())
                    .name(ingredient.getNameEng())
                    .build();
            dishDTO.getIngredients().add(ingredientDTO);
        }
        return dishDTO;
    }

    @Override
    protected DishDTO toUkrDto(Dish entity) {
        DishDTO dishDTO = DishDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getNameUkr())
                .price(entity.getPrice())
                .ingredients(new ArrayList<>())
                .build();
        for (Ingredient ingredient : entity.getIngredients()){
            IngredientDTO ingredientDTO = IngredientDTO.builder()
                    .id(ingredient.getId())
                    .amount(ingredient.getAmount())
                    .maxAmount(ingredient.getMaxAmount())
                    .name(ingredient.getNameUkr())
                    .build();
            dishDTO.getIngredients().add(ingredientDTO);
        }
        return dishDTO;
    }
}
