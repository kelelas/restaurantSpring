package com.kelelas.restaurant.mapper;

import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class LocaleIngredientMapper extends LocalizedMapper<Ingredient, IngredientDTO> {
    @Override
    protected IngredientDTO toEngDto(Ingredient entity) {
        return IngredientDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .maxAmount(entity.getMaxAmount())
                .name(entity.getNameEng())
                .build();
    }

    @Override
    protected IngredientDTO toUkrDto(Ingredient entity) {
        return IngredientDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .maxAmount(entity.getMaxAmount())
                .name(entity.getNameUkr())
                .build();
    }
}
