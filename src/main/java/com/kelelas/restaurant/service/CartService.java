package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Cart;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.mapper.LocaleDishMapper;
import com.kelelas.restaurant.repository.CartRepository;
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
public class CartService {
    CartRepository cartRepository;
    LocaleDishMapper mapper;
@Autowired
    public CartService(CartRepository cartRepository, LocaleDishMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public void addToCart(Cart cart){
        cartRepository.save(cart);
    }

    public void deleteFromCart(Cart cart){
        cartRepository.deleteDishFromUserCart(cart.getUserId(), cart.getDishId());
    }

    public List<DishDTO> getLocalCart(Long userId){
        return cartRepository.findAllByUserId(userId).stream().map(Cart::getDish)
                .map(mapper::dtoMapper).collect(Collectors.toList());
}

    public List<Dish> getCart(Long userId){
        return cartRepository.findAllByUserId(userId).stream()
                .map(Cart::getDish).collect(Collectors.toList());
    }

    public void clearCart(Long userId){
        cartRepository.deleteFullUserCart(userId);
    }
}
