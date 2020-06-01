package com.kelelas.restaurant.service;

import com.kelelas.restaurant.config.ConstantBundle;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.History;
import com.kelelas.restaurant.entity.Ingredient;
import com.kelelas.restaurant.exception.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class AdminPageService {
    HistoryService historyService;
    IngredientService ingredientService;
    BillService billService;
@Autowired
    public AdminPageService(HistoryService historyService, IngredientService ingredientService, BillService billService) {
        this.historyService = historyService;
        this.ingredientService = ingredientService;
        this.billService = billService;
    }

    @Transactional
    public void confirm(List<Dish> dishes){
        try {
            dishes.stream()
                    .map(Dish::getIngredients)
                    .forEach((i)
                            -> i.forEach((j)
                            ->{j.setAmount(j.getAmount()-1);ingredientService.save(j);}));
        }catch (Exception e){
            throw new DBException(e);
        }

    }

    @Transactional
    public void updateStoryById(String id) {
        try {
            History historyItem = historyService.getStoryById(Long.valueOf(id));
                historyItem.setStatusId((long) ConstantBundle.getIntProperty("status.newOrder"));
                confirm(historyItem.getDishes());
                billService.saveNewBill(historyItem);
                historyService.update(historyItem);

        }catch (Exception e){
            throw new DBException(e);
        }

    }
    @Transactional
    public void updateIngredientById(String id) throws Exception {
        try {
            Ingredient ingredient = ingredientService.getIngredientById(Long.valueOf(id));
            ingredient.setAmount(ingredient.getMaxAmount());
            ingredientService.save(ingredient);
        }catch (Exception e){
            throw new DBException(e);
        }

    }
}
