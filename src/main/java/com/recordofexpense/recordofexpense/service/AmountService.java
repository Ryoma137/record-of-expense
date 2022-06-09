package com.recordofexpense.recordofexpense.service;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AmountService {

    @Autowired
    AmountRepository amountRepository;

    public List<Amount> getFindAll() {
        return amountRepository.findAll();
    }

    public List<String> getCategoryList() {

        var originalCategoryList = amountRepository.getCategoryList();
        Set<String> eliminateDuplications = new LinkedHashSet<String>(originalCategoryList);
        return new ArrayList<String>(eliminateDuplications);
    }

    public int getTotalPrice(){
        var originalPrice = amountRepository.getPrice();
        int total = originalPrice.stream().mapToInt(value -> value).sum();

        return total;

    }


}
