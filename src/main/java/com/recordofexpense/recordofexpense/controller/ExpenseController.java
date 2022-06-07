package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.service.AmountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/add/expense")
    public String registerAmount(@Validated @ModelAttribute Amount registerAmount){


        Amount aaa = amountService.registerAmount(registerAmount.getId(), registerAmount.getName(), registerAmount.getPrice(), registerAmount.getCategory(),registerAmount.getComments());

      //  List<Amount> aaa = amountService.registerAmount(registerAmount);


//        if(bindingResult.hasErrors()){
//            return "expense";
//        }


        return "redirect:/expense";
    }


}
