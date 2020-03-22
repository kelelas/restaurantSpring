package com.kelelas.restaurant.entity.locale;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Builder
@Getter
public class IngredientLocale {
    private Long id;
    private String name;
    private int amount;
    private int max_amount;
}
