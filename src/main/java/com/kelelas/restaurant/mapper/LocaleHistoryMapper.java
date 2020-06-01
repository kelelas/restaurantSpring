package com.kelelas.restaurant.mapper;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.dto.HistoryDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.History;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LocaleHistoryMapper extends LocalizedMapper<History, HistoryDTO> {
    @Override
    protected HistoryDTO toEngDto(History entity) {
        HistoryDTO historyDTO = HistoryDTO.builder()
                .date(entity.getDate())
                .id(entity.getId())
                .price(entity.getPrice())
                .status(entity.getStatus().getStatusEng())
                .userName(entity.getUser().getNameEng())
                .dishes(new ArrayList<>())
                .build();
        for (Dish dish : entity.getDishes()){
            DishDTO dishDTO = DishDTO.builder()
                    .price(dish.getPrice())
                    .name(dish.getNameEng())
                    .image(dish.getImage())
                    .id(dish.getId())
                    .build();
            historyDTO.getDishes().add(dishDTO);
        }
        return  historyDTO;
    }

    @Override
    protected HistoryDTO toUkrDto(History entity) {
        HistoryDTO historyDTO = HistoryDTO.builder()
                .date(entity.getDate())
                .id(entity.getId())
                .price(entity.getPrice())
                .status(entity.getStatus().getStatusUkr())
                .userName(entity.getUser().getNameUkr())
                .dishes(new ArrayList<>())
                .build();
        for (Dish dish : entity.getDishes()){
            DishDTO dishDTO = DishDTO.builder()
                    .price(dish.getPrice())
                    .name(dish.getNameUkr())
                    .image(dish.getImage())
                    .id(dish.getId())
                    .build();
            historyDTO.getDishes().add(dishDTO);
        }
        return  historyDTO;
    }
}
