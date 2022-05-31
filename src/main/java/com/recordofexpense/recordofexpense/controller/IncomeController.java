package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
public class IncomeController {

    @Autowired
    private final AmountRepository amountRepository;

    @GetMapping("/amount/list")
    public List<Amount> getAmount() {
        return StreamSupport.stream(amountRepository.findAll().spliterator(), false).toList();
    }

    @GetMapping("/expense")
    public String getCategory(Model model){
        List<String> categoryList = StreamSupport.stream(amountRepository.getCategoryList().spliterator(),false).toList();
        model.addAttribute("categoryLists",categoryList);
        return "expense";
    }
}
