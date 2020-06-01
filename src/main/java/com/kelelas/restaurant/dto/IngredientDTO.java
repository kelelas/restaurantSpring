package com.kelelas.restaurant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IngredientDTO {
    private Long id;
    private int amount;
    private int maxAmount;
    private String name;
}
