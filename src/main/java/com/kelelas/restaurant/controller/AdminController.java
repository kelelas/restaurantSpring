package com.kelelas.restaurant.controller;


import com.kelelas.restaurant.config.ConstantBundle;
import com.kelelas.restaurant.exception.DBException;
import com.kelelas.restaurant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    HistoryService storiesService;
    IngredientService ingredientService;
    AdminPageService adminPageService;

    public AdminController() {
    }
    @Autowired
    public AdminController(AdminPageService adminPageService, HistoryService storiesService, IngredientService ingredientService) {
        this.adminPageService = adminPageService;
        this.storiesService = storiesService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/orders_list")
    public String ordersPage(Model model){
        model.addAttribute("items", storiesService.getLocaleStoriesByStatus((long) ConstantBundle.getIntProperty("status.waitingForConfirm")));
        return "/admin/orders_list.html";
    }

    @RequestMapping("/statistics")
    public String statisticsPage(Model model, @PageableDefault(size = 5, sort = "date", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("items",storiesService.getLocaleStories(pageable));
        return "/admin/statistics.html";
    }

    @RequestMapping("/update_ingredients")
    public String updateIngredientPage(Model model){
        model.addAttribute("items", ingredientService.getLocaleIngredients());
        return "/admin/update_ingredients.html";
    }




    @PostMapping(value = "/confirm{orderId}")
    public String updateOrder(@PathVariable String orderId){
            try {
                adminPageService.updateStoryById(orderId);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        return "redirect:/admin/orders_list";
    }
    @PostMapping(value = "/update{ingredientId}")
    public String updateIngredient(@PathVariable String ingredientId){
        RedirectView redirectView = new RedirectView();
        try {
            adminPageService.updateIngredientById(ingredientId);
            redirectView.setUrl("/admin/update_ingredients");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/admin/update_ingredients";
    }
}

