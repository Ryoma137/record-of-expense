package com.recordofexpense.recordofexpense.service;

import com.recordofexpense.recordofexpense.entity.Amount;
import com.recordofexpense.recordofexpense.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AmountService {

    @Autowired
    AmountRepository amountRepository;

    private final JdbcTemplate jdbcTemplate;

    public AmountService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Amount> getFindAll() {
        return amountRepository.findAll();
    }

    public List<String> getCategoryList() {

        var originalCategoryList = amountRepository.getCategoryList();
        Set<String> eliminateDuplications = new LinkedHashSet<String>(originalCategoryList);
        return new ArrayList<String>(eliminateDuplications);
    }

    public void registerAmount(Amount amount) {

        amount.setId(null);
        amountRepository.save(amount);
    }

}
