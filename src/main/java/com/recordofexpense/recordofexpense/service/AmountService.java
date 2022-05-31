package com.recordofexpense.recordofexpense.service;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountService {

    @Autowired
    AmountRepository amountRepository;

    public List<Amount> getFindAll() {
        return amountRepository.findAll();
    }

    public List<String> getCategoryList() {
        return amountRepository.getCategoryList();
    }

}
