package com.kelelas.restaurant.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DishDTO {
        private Long id;
        private String name;
        private int price;
        private String image;
        List<IngredientDTO> ingredients = new ArrayList<>();
}
