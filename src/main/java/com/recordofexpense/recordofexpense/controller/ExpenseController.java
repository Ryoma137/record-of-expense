package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.service.AmountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class ExpenseController {

    private final AmountService amountService;

    @GetMapping("/expense")
    public String getCategory(Model model) {
        List<String> categoryList = amountService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "expense";
    }

    @PostMapping("/expense")
    public String registerExpense(Model model) {

        Amount registerExpense = amountService.registerAmount();
        model.addAttribute("registerExpense", registerExpense);

        return "redirect:/expense";
    }


}
