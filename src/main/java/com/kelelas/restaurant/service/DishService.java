package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.mapper.LocaleDishMapper;
import com.kelelas.restaurant.repository.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service

public class DishService {
    DishRepository dishRepository;
    LocaleDishMapper mapper;
    @Autowired
    public DishService(DishRepository dishRepository, LocaleDishMapper mapper) {
        this.dishRepository = dishRepository;
        this.mapper = mapper;
    }


    public List<DishDTO> getLocaleDishes(){
            return dishRepository.findAll().stream()
                    .map(mapper :: dtoMapper).collect(Collectors.toList());
    }
}
