package com.recordofexpense.recordofexpense.service;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(6, actual.size());

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
        assertEquals("iPad", actual.get(4).getName());
        assertEquals(140000, actual.get(4).getPrice());
        assertEquals("Gadget", actual.get(4).getCategory());
        assertEquals("iPad Pro", actual.get(4).getComments());

        assertEquals(6, actual.get(5).getId());
        assertEquals("Crispy Pizza", actual.get(5).getName());
        assertEquals(850, actual.get(5).getPrice());
        assertEquals("Food", actual.get(5).getCategory());
        assertEquals("gluten free", actual.get(5).getComments());
    }

    @Test
    @DisplayName("Food, Clothes, Gadgetが重複されずにDBから取得される　（合計三件DBから取得される）")
    void testGetCategory() {

        List<String> actual = amountService.getCategoryList();

        assertEquals(3, actual.size(), "三件のデータが取得される");

        assertTrue(actual.contains("Food"), "actualのリストに Foodの文字列が入っている");
        assertTrue(actual.contains("Clothes"), "actualのリストに Clothesの文字列が入っている");
        assertTrue(actual.contains("Gadget"), "actualのリストに Gadgetの文字列が入っている");

    }
}