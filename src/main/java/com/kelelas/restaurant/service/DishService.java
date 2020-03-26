package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.repository.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service

public class DishService {
    DishRepository dishRepository;
    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    public Optional<Dish> getDishById(Long id){
        return dishRepository.findById(id);
    }

    public List<DishDTO> getLocaleDishes(HttpServletRequest request){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return dishRepository.findUkrDishes();
        else
            return dishRepository.findEngDishes();
    }
    public  DishDTO getOneLocaleDish(HttpServletRequest request, Long id){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return dishRepository.findUkrDishById(id);
        else
            return dishRepository.findEngDishById(id);
    }
}
