package com.kelelas.restaurant.service;

import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.repository.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DishService {
    DishRepository dishRepository;
    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return new ArrayList<>(dishRepository.findAll());
    }
    public Optional<Dish> getDishById(Long id){
        return dishRepository.findById(id);
    }
}
