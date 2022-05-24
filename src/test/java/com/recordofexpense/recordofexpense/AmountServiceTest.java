package com.recordofexpense.recordofexpense;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmountServiceTest {

    @Autowired
    private AmountService amountService;

    @Test
    @DisplayName("Test get all amount")
    void getAmount() {
        List<Amount> actual = amountService.getAmount();
        assertNotNull(actual, "Amount should not null");
        // assertEquals(actual.size() == 1, actual);

    }
}