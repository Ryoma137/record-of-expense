package com.recordofexpense.recordofexpense.controller;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
public class IncomeController {

    private final AmountRepository amountRepository;

    @GetMapping("/amount")
    public List<Amount> getAmount(){
        return StreamSupport.stream(amountRepository.findAll().spliterator(),false).toList();
    }

}
