package com.recordofexpense.recordofexpense;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AmountService {
    @Autowired
    AmountRepository amountRepository;

    public List<Amount> getAmount(){
        return amountRepository.findAll();
    }

    public Amount getAmountById(long id){
        return amountRepository.findById((int) id).orElse(null);
    }

    public Amount getAmountByCategory(String category){
        return amountRepository.findByCategory(category);
    }

}
