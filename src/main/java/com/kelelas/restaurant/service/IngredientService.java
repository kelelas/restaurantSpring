package com.kelelas.restaurant.service;

import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IngredientService {
    IngredientRepository ingredientRepository;
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public Ingredient getIngredientById(Long id){
        return ingredientRepository.getById(id);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    public void save(Ingredient item){
        ingredientRepository.save(item);
    }
}
