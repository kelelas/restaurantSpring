package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.exception.DBException;
import com.kelelas.restaurant.mapper.LocaleIngredientMapper;
import com.kelelas.restaurant.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Vladyslav Osypchuk
 * @version 4.2
 */
@Slf4j
@Service
public class IngredientService {
    /**
     * field with link to ingredientRepository class
     */
    IngredientRepository ingredientRepository;
    /**
     * field with mapper for localize ingredients
     */
    LocaleIngredientMapper mapper;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, LocaleIngredientMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    /**
     * @return ingredient from DB
     * @param id (id of ingredient)
     */
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(DBException::new);
    }

    /**
     * method use mapper to localize ingredients
     * @return array of all localized ingredients from DB
     */
    public List<IngredientDTO> getLocaleIngredients() {
        return  ingredientRepository.findAll().stream()
                .map(mapper::dtoMapper).collect(Collectors.toList());
    }

    /**
     * method saved ingredient to DB
     * @param item (the ingredient witch we updating)
     */
    public void save(Ingredient item){
        ingredientRepository.save(item);
    }
}
