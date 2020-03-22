package com.kelelas.restaurant.entity.locale;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Builder
public class StoryItemLocale {
    private Long id;
    private Long userId;
    private int price;
    private int status;
    private String dishes;
    private LocalDateTime date;
}
