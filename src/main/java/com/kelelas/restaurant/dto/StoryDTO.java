package com.kelelas.restaurant.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoryDTO {
    private Long id;
    private LocalDateTime date;
    private int price;
    private Long userid;
    private String dishes;
    private String status;

}
