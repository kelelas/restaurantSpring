package com.kelelas.restaurant.entity.locale;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DishLocale {
    private Long id;
    private String image;
    private String name;
    private String main_ingredient;
    private String off_ingredient;
    private int price;
}
