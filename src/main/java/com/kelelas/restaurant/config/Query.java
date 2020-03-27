package com.kelelas.restaurant.config;

public interface Query {
    String SELECT_ENG_DISHES = "SELECT  new com.kelelas.restaurant.dto.DishDTO(d.id, d.name_eng as name, d.price, d.image, i.name_eng as main_ingredient, i2.name_eng  as off_ingredient, d.main_ingredient_id, d.off_ingredient_id) from Dish as d left join Ingredient  as i on i.id = d.main_ingredient_id left join Ingredient  as i2 on i2.id = d.off_ingredient_id";
    String SELECT_UKR_DISHES = "SELECT  new com.kelelas.restaurant.dto.DishDTO(d.id, d.name_ukr as name, d.price, d.image, i.name_ukr as main_ingredient, i2.name_ukr  as off_ingredient,  d.main_ingredient_id, d.off_ingredient_id) from Dish as d left join Ingredient  as i on i.id = d.main_ingredient_id left join Ingredient  as i2 on i2.id = d.off_ingredient_id";
    String SELECT_ONE_ENG_DISH = "SELECT  new com.kelelas.restaurant.dto.DishDTO(d.id, d.name_eng as name, d.price, d.image, i.name_eng as main_ingredient, i2.name_eng  as off_ingredient,  d.main_ingredient_id, d.off_ingredient_id) from Dish as d left join Ingredient  as i on i.id = d.main_ingredient_id left join Ingredient  as i2 on i2.id = d.off_ingredient_id where d.id = ?1";
    String SELECT_ONE_UKR_DISH = "SELECT  new com.kelelas.restaurant.dto.DishDTO(d.id, d.name_ukr as name, d.price, d.image, i.name_ukr as main_ingredient, i2.name_ukr  as off_ingredient,  d.main_ingredient_id, d.off_ingredient_id) from Dish as d left join Ingredient  as i on i.id = d.main_ingredient_id left join Ingredient  as i2 on i2.id = d.off_ingredient_id where d.id = ?1";
    String FIND_DISH_BY_NAME = "SELECT d FROM Dish d WHERE d.name_eng = ?1";

    String SELECT_ENG_STORIES_BY_USERID = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_eng as dishes, s.status_eng as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.userid = ?1";
    String SELECT_UKR_STORIES_BY_USERID = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_ukr as dishes, s.status_ukr as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.userid = ?1";
    String SELECT_ENG_STORIES_BY_STATUS = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_eng as dishes, s.status_eng as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.status = ?1";
    String SELECT_UKR_STORIES_BY_STATUS = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_ukr as dishes, s.status_ukr as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.status = ?1";
    String SELECT_ENG_STORIES_BY_STATUS_AND_USERID = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_eng as dishes, s.status_eng as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.status = ?1 and h.userid = ?2";
    String SELECT_UKR_STORIES_BY_STATUS_AND_USERID = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_ukr as dishes, s.status_ukr as status) FROM HistoryItem as h left join Status as s on h.status = s.id where h.status = ?1 and h.userid = ?2";
    String SELECT_ENG_STORIES = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_eng as dishes, s.status_eng as status) FROM HistoryItem as h left join Status as s on h.status = s.id";
    String SELECT_UKR_STORIES = "SELECT new com.kelelas.restaurant.dto.StoryDTO(h.id, h.date, h.price, h.userid, h.dishes_list_ukr as dishes, s.status_ukr as status) FROM HistoryItem as h left join Status as s on h.status = s.id";

    String SELECT_ENG_INGREDIENTS = "SELECT new com.kelelas.restaurant.dto.IngredientDTO(id, amount, max_amount, name_eng as name) FROM Ingredient as i";
    String SELECT_UKR_INGREDIENTS = "SELECT new com.kelelas.restaurant.dto.IngredientDTO(id, amount, max_amount, name_ukr as name) FROM Ingredient as i";
}
