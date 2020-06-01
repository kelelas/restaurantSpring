package com.kelelas.restaurant.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BillDTO {
    private Long id;
    private LocalDateTime date;
    private int price;
    private String status;
    private String userName;
    private List<DishDTO> dishes = new ArrayList<>();
}
