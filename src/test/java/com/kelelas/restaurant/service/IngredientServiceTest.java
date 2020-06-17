package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.mapper.LocaleIngredientMapper;
import com.kelelas.restaurant.repository.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    LocaleIngredientMapper mapper;

    @InjectMocks
    IngredientService ingredientService;


    private final Long id = 6L;
    private final Ingredient ingredient = Ingredient.builder()
            .amount(100)
            .maxAmount(100)
            .id(id)
            .nameEng("ingredient")
            .nameUkr("інгредієнт")
            .build();
    private final IngredientDTO ingredientEng = IngredientDTO.builder()
            .amount(100)
            .maxAmount(100)
            .id(id)
            .name("ingredient")
            .build();

    @Test
    public void getIngredientById() {
        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
        assertEquals(ingredientService.getIngredientById(id),ingredient);
        verify(ingredientRepository).findById(id);
    }

    @Test
    public void getLocaleIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        List<IngredientDTO> ingredientsDTO = new ArrayList<>();
        ingredientsDTO.add(ingredientEng);

        when(ingredientRepository.findAll()).thenReturn(ingredients);
        when(mapper.dtoMapper(ingredient)).thenReturn(ingredientEng);
        assertEquals(ingredientService.getLocaleIngredients(), ingredientsDTO);
        verify(ingredientRepository).findAll();
    }

    @Test
    public void save() {
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
        ingredientService.save(ingredient);
        verify(ingredientRepository).save(ingredient);
    }


}