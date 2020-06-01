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

@Slf4j
@Service
public class IngredientService {
    IngredientRepository ingredientRepository;
    LocaleIngredientMapper mapper;
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, LocaleIngredientMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(DBException::new);
    }

    public List<IngredientDTO> getLocaleIngredients() {
        return  ingredientRepository.findAll().stream()
                .map(mapper::dtoMapper).collect(Collectors.toList());
    }

    public void save(Ingredient item){
        ingredientRepository.save(item);
    }
}
