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
        assertEquals(3, actual.size());

        assertEquals(1, actual.get(0).getId());
        assertEquals("Sandwich", actual.get(0).getName());
        assertEquals(450, actual.get(0).getPrice());
        assertEquals("Food", actual.get(0).getCategory());
        assertEquals("What a scrumptious sandwich", actual.get(0).getComments());

        assertEquals(2, actual.get(1).getId());
        assertEquals("White T-Shirt", actual.get(1).getName());
        assertEquals(980, actual.get(1).getPrice());
        assertEquals("Clothes", actual.get(1).getCategory());
        assertEquals("Uniqlo T-Shirt", actual.get(1).getComments());

        assertEquals(3,actual.get(2).getId());
        assertEquals("iPhone",actual.get(2).getName());
        assertEquals(130000,actual.get(2).getPrice());
        assertEquals("Gadget",actual.get(2).getCategory());
        assertEquals("iPhone 13 Pro",actual.get(2).getComments());

    }
}