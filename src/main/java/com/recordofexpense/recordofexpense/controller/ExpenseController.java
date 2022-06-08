package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.service.AmountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class ExpenseController {

    private final AmountService amountService;

    @GetMapping("/expense")
    public String getCategory(Model model) {
        List<String> categoryList = amountService.getCategoryList();
        model.addAttribute("categories", categoryList);
        return "expense";
    }


}
