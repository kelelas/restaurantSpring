package com.kelelas.restaurant.mapper;

import com.kelelas.restaurant.dto.BillDTO;
import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.dto.HistoryDTO;
import com.kelelas.restaurant.entity.Bill;
import com.kelelas.restaurant.entity.Dish;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class LocaleBillMapper extends LocalizedMapper<Bill, BillDTO> {
    @Override
    protected BillDTO toEngDto(Bill entity) {
        BillDTO billDTO = BillDTO.builder()
                .date(entity.getDate().format(DateTimeFormatter.ofPattern("MM.dd.yyyy HH:mm:ss")))
                .id(entity.getId())
                .price(entity.getPrice()/8)
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
            billDTO.getDishes().add(dishDTO);
        }
        return  billDTO;
    }

    @Override
    protected BillDTO toUkrDto(Bill entity) {
        BillDTO billDTO = BillDTO.builder()
                .date(entity.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")))
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
            billDTO.getDishes().add(dishDTO);
        }
        return  billDTO;
    }
}
