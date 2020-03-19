package com.kelelas.restaurant.dto;


import com.kelelas.restaurant.entity.HistoryItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoriesDTO {
    private List<HistoryItem> stories;
}
