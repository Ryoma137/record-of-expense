package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.service.AmountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class IncomeController {

    private AmountService amountService;

    @GetMapping("/expense")
    public String getCategory(Model model) {
        List<String> categoryList = amountService.getCategoryList();
        model.addAttribute("categoryLists", categoryList);
        return "expense";
    }
}
