package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Cart;
import com.kelelas.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM restaurant_main.cart WHERE (dishes_id=:dishId and users_id=:userId)", nativeQuery = true)
    void deleteDishFromUserCart(Long userId, Long dishId);

    List<Cart> findAllByUserId( Long userId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM restaurant_main.cart WHERE  (users_id= :userId)", nativeQuery = true)
    void deleteFullUserCart(Long userId);
}
