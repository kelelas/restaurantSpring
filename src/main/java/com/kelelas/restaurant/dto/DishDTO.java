package com.kelelas.restaurant.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

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
        private String main_ingredient;
        private String off_ingredient;
        private Long main_ingredient_id;
        private Long off_ingredient_id;

}
