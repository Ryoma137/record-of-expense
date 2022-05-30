package com.recordofexpense.recordofexpense;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountService {

    @Autowired
    AmountRepository amountRepository;

    public List<Amount> getAmount() {
        return amountRepository.findAll();
    }

    public List<String> getAmountByCategory() {
        return amountRepository.getCategoryList();
    }

}
