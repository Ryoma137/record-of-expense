package com.recordofexpense.recordofexpense;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql("/test-schema.sql")
class AmountServiceTest {

    @Autowired
    private AmountService amountService;

    @Test
    @DisplayName("Test get all amount")
    void testFindAll() {
        List<Amount> actual = amountService.getAmount();
        assertNotNull(actual, "Amount should not null");
        assertEquals(3,actual.size());

    }
}