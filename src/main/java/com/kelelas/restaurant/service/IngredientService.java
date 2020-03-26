package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

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
    public List<IngredientDTO> getLocaleIngredients(HttpServletRequest request) {
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
        return ingredientRepository.getUkrIngredients();
        else
            return ingredientRepository.getEngIngredients();
    }
    public void save(Ingredient item){
        ingredientRepository.save(item);
    }
}
