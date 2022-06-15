package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmountRepositoryTest {

    @Autowired
    AmountRepository amountRepository;

    @Test
    @Sql("/test-schema-not-data-exist.sql")
    @DisplayName("IDがNullの時に@IDがNullの時に@GeneratedValueでIDが正しく生成されることでIDが正しく生成されること")
    void testGeneratedValue() {

        var amount = new Amount();
        amount.setId(null);
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        var amount2 = new Amount();
        amount2.setName("testName2");
        amount2.setPrice(1200);
        amount2.setCategory("testCategory2");
        amount2.setComments("testComment2");

        assertNull(amount.getId(), "DBに保存される前のIDはNullであるかの確認");
        assertNull(amount2.getId(), "DBに保存される前のIDはNullであるかの確認");

        amountRepository.save(amount);
        amountRepository.save(amount2);

        var actual = amountRepository.findAll();

        assertEquals(2, actual.size(), "追加したデータのみがDBに保存されている");

        assertEquals(1, actual.get(0).getId(), "IDをnullでinstanceにセットしたがDBにデータ保存時にID列を使用した主キー値(1)に変更されている");
        assertEquals(2, actual.get(1).getId(), "idをinstanceにセットしてないが、DBにデータ保存時にDBのID列を使用した主キー値(2)でIDが生成されている");

    }

    @Test
    @Sql("/test-schema-not-data-exist.sql")
    @DisplayName("@GeneratedValueで生成したIDが付与されているデータが削除できること")
    void testDeletableGeneratedValue() {

        var amount = new Amount();
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        var actual = amountRepository.findAll();

        assertEquals(1, actual.size(), "追加したデータのみがDBに保存されている");
        assertEquals(amount, actual.get(0), "与えられたデータがDBに追加されている");

        amountRepository.deleteAll();
        var deletedValue = amountRepository.findAll();
        assertTrue(deletedValue.isEmpty(), "　先ほど与えたデータが削除されている");

    }


    @Test
    @Sql("/test-schema-not-data-exist.sql")
    @DisplayName("DBのテーブル内にデータが存在しない時、与えられたデータがDBに追加されていること")
    void testAddDataWhenDataNotExistInDB() {

        var amount = new Amount();
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        var actual = amountRepository.findAll();

        assertEquals(1, actual.size(), "追加したデータのみがDBに保存されている");
        assertEquals(amount, actual.get(0), "追加したデータがレコードの1列目に保存されている");

    }

    @Test
    @Sql("/test-schema.sql")
    @DisplayName("DBのテーブル内にデータが存在する時、DBのID列の最後尾にデータが追加されていること")
    void testAddDataWhenDataExistInDB() {

        var amount = new Amount();
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> actual = amountRepository.findAll();

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

        assertEquals(7, actual.get(6).getId(), "DBのID列の最後尾columnにGeneratedValueで自動生成されたIDが追加されている");
        assertEquals("testName", actual.get(6).getName(), "追加したデータの名前がDBのID列の最後尾columnに追加されている");
        assertEquals(1000, actual.get(6).getPrice(), "追加したデータの価格がDBのID列の最後尾columnに追加されている");
        assertEquals("testCategory", actual.get(6).getCategory(), "追加したデータのカテゴリーがDBのID列の最後尾columnに追加されている");
        assertEquals("testComment", actual.get(6).getComments(), "追加したデータのコメントがDBのID列の最後尾columnに追加されている");

        assertEquals(7, actual.size(), "既存のDBに保存されているデータ数に今回追加したデータ数を合わせたデータ数が取得される");

    }


    @Test
    @Sql("/test-schema.sql")
    @DisplayName("与えられたデータのキーが一致するデータがDBに存在する時、与えられたデータでDBが更新されること")
    void testUpdateWhenDataContainsSameKeyWithGivenData() {

        List<Amount> originalValues = amountRepository.findAll();

        assertEquals(1, originalValues.get(0).getId());
        assertEquals("Sandwich", originalValues.get(0).getName());
        assertEquals(450, originalValues.get(0).getPrice());
        assertEquals("Food", originalValues.get(0).getCategory());
        assertEquals("What a scrumptious sandwich", originalValues.get(0).getComments());

        var amount = new Amount();
        amount.setId(1L);
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> updatedValues = amountRepository.findAll();

        assertEquals(amount, updatedValues.get(0), "与えられたデータのキーが一致するデータがDBに存在する時、与えられたデータでDBが更新されているか確認");
    }

    @Test
    @Sql("/test-schema.sql")
    @DisplayName("与えられたデータのキーが一致するデータがDBに存在しない時、DBが更新されないこと")
    void testUpdateWhenDataNotContainsSameKeyWithGivenData() {


        List<Amount> originalValues = amountRepository.findAll();

        assertEquals(6, originalValues.size());

        assertEquals(1, originalValues.get(0).getId());
        assertEquals("Sandwich", originalValues.get(0).getName());
        assertEquals(450, originalValues.get(0).getPrice());
        assertEquals("Food", originalValues.get(0).getCategory());
        assertEquals("What a scrumptious sandwich", originalValues.get(0).getComments());

        assertEquals(2, originalValues.get(1).getId());
        assertEquals("White T-Shirt", originalValues.get(1).getName());
        assertEquals(980, originalValues.get(1).getPrice());
        assertEquals("Clothes", originalValues.get(1).getCategory());
        assertEquals("Uniqlo T-Shirt", originalValues.get(1).getComments());

        assertEquals(3, originalValues.get(2).getId());
        assertEquals("iPhone", originalValues.get(2).getName());
        assertEquals(130000, originalValues.get(2).getPrice());
        assertEquals("Gadget", originalValues.get(2).getCategory());
        assertEquals("iPhone 13 Pro", originalValues.get(2).getComments());

        assertEquals(4, originalValues.get(3).getId());
        assertEquals("Green Curry", originalValues.get(3).getName());
        assertEquals(880, originalValues.get(3).getPrice());
        assertEquals("Food", originalValues.get(3).getCategory());
        assertEquals("Thai Cuisine", originalValues.get(3).getComments());

        assertEquals(5, originalValues.get(4).getId());
        assertEquals("iPad", originalValues.get(4).getName());
        assertEquals(140000, originalValues.get(4).getPrice());
        assertEquals("Gadget", originalValues.get(4).getCategory());
        assertEquals("iPad Pro", originalValues.get(4).getComments());

        assertEquals(6, originalValues.get(5).getId());
        assertEquals("Crispy Pizza", originalValues.get(5).getName());
        assertEquals(850, originalValues.get(5).getPrice());
        assertEquals("Food", originalValues.get(5).getCategory());
        assertEquals("gluten free", originalValues.get(5).getComments());

        var amount = new Amount();
        amount.setId(7L);
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> updatedValues = amountRepository.findAll();


        assertEquals(1, updatedValues.get(0).getId(), "DBのID列1列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("Sandwich", updatedValues.get(0).getName(), "DBのID列1列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(450, updatedValues.get(0).getPrice(), "DBのID列1列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Food", updatedValues.get(0).getCategory(), "DBのID列1列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("What a scrumptious sandwich", updatedValues.get(0).getComments(), "DBのID列1列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(2, updatedValues.get(1).getId(), "DBのID列2列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("White T-Shirt", updatedValues.get(1).getName(), "DBのID列2列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(980, updatedValues.get(1).getPrice(), "DBのID列2列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Clothes", updatedValues.get(1).getCategory(), "DBのID列2列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("Uniqlo T-Shirt", updatedValues.get(1).getComments(), "DBのID列2列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(3, updatedValues.get(2).getId(), "DBのID列3列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("iPhone", updatedValues.get(2).getName(), "DBのID列3列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(130000, updatedValues.get(2).getPrice(), "DBのID列3列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Gadget", updatedValues.get(2).getCategory(), "DBのID列3列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("iPhone 13 Pro", updatedValues.get(2).getComments(), "DBのID列3列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(4, updatedValues.get(3).getId(), "DBのID列4列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("Green Curry", updatedValues.get(3).getName(), "DBのID列4列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(880, updatedValues.get(3).getPrice(), "DBのID列4列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Food", updatedValues.get(3).getCategory(), "DBのID列4列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("Thai Cuisine", updatedValues.get(3).getComments(), "DBのID列4列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(5, updatedValues.get(4).getId(), "DBのID列5列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("iPad", updatedValues.get(4).getName(), "DBのID列5列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(140000, updatedValues.get(4).getPrice(), "DBのID列5列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Gadget", updatedValues.get(4).getCategory(), "DBのID列5列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("iPad Pro", updatedValues.get(4).getComments(), "DBのID列5列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(6, updatedValues.get(5).getId(), "DBのID列6列目に保存されているデータのIDが更新されていないかの確認");
        assertEquals("Crispy Pizza", updatedValues.get(5).getName(), "DBのID列6列目に保存されているデータの名前が更新されていないかの確認");
        assertEquals(850, updatedValues.get(5).getPrice(), "DBのID列6列目に保存されているデータの価格が更新されていないかの確認");
        assertEquals("Food", updatedValues.get(5).getCategory(), "DBのID列6列目に保存されているデータのカテゴリーが更新されていないかの確認");
        assertEquals("gluten free", updatedValues.get(5).getComments(), "DBのID列6列目に保存されているデータのコメントが更新されていないかの確認");

        assertEquals(amount, updatedValues.get(6), "与えられたデータのキーが一致するデータがDBに存在しない時、与えられたデータでDBの更新ではなく、与えられたデータのキーで新たなデータが登録されているかの確認");
    }
}