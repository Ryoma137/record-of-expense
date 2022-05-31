package com.recordofexpense.recordofexpense.service;

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
    @DisplayName("Test find all")
    void testFindAll() {
        List<Amount> actual = amountService.getFindAll();
        assertNotNull(actual, "Amount should not null");
        assertEquals(9, actual.size());

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

        assertEquals(3, actual.get(2).getId());
        assertEquals("iPhone", actual.get(2).getName());
        assertEquals(130000, actual.get(2).getPrice());
        assertEquals("Gadget", actual.get(2).getCategory());
        assertEquals("iPhone 13 Pro", actual.get(2).getComments());

        assertEquals(4, actual.get(3).getId());
        assertEquals("Green Curry", actual.get(3).getName());
        assertEquals(880, actual.get(3).getPrice());
        assertEquals("Food", actual.get(3).getCategory());
        assertEquals("Thai Cuisine", actual.get(3).getComments());

        assertEquals(5, actual.get(4).getId());
        assertEquals("Black T-Shirt", actual.get(4).getName());
        assertEquals(1080, actual.get(4).getPrice());
        assertEquals("Clothes", actual.get(4).getCategory());
        assertEquals("H&M T-Shirt", actual.get(4).getComments());

        assertEquals(6, actual.get(5).getId());
        assertEquals("iPad", actual.get(5).getName());
        assertEquals(140000, actual.get(5).getPrice());
        assertEquals("Gadget", actual.get(5).getCategory());
        assertEquals("iPad Pro", actual.get(5).getComments());

        assertEquals(7, actual.get(6).getId());
        assertEquals("Crispy Pizza", actual.get(6).getName());
        assertEquals(850, actual.get(6).getPrice());
        assertEquals("Food", actual.get(6).getCategory());
        assertEquals("gluten free", actual.get(6).getComments());

        assertEquals(8, actual.get(7).getId());
        assertEquals("Jeans", actual.get(7).getName());
        assertEquals(12000, actual.get(7).getPrice());
        assertEquals("Clothes", actual.get(7).getCategory());
        assertEquals("Levis", actual.get(7).getComments());

        assertEquals(9, actual.get(8).getId());
        assertEquals("MacBook", actual.get(8).getName());
        assertEquals(150000, actual.get(8).getPrice());
        assertEquals("Gadget", actual.get(8).getCategory());
        assertEquals("MacBook Air", actual.get(8).getComments());

    }

    @Test
    @DisplayName("Test get category")
    void testGetCategory() {

        List<String> actual = amountService.getCategoryList();
        assertEquals("Food", actual.get(0));
        assertEquals("Food", actual.get(3));
        assertEquals("Food", actual.get(6));

        assertEquals("Clothes", actual.get(1));
        assertEquals("Clothes", actual.get(4));
        assertEquals("Clothes", actual.get(7));

        assertEquals("Gadget", actual.get(2));
        assertEquals("Gadget", actual.get(5));
        assertEquals("Gadget", actual.get(8));

    }
}