package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("IDがNullの時に@IDがNullの時に@GeneratedValueでIDが正しく生成されること")
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

        assertNotNull(actual.get(0).getId(), "DBに保存されたIDがnullではなく、IDが生成されている");
        assertNotNull(actual.get(1).getId(), "DBに保存されたIDがnullではなく、IDが生成されている");

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

        assertNotNull(actual.get(0).getId(), "与えられたデータがDBに追加されている");
        assertEquals(1, actual.size(), "追加したデータのみがDBに保存されている");

        amountRepository.deleteAll();
        var deletedValue = amountRepository.findAll();
        assertTrue(deletedValue.isEmpty(), "　与えたデータが削除されている");

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

        List<Amount> originalValue = amountRepository.findAll();

        assertNotNull(originalValue.get(0).getId(), "既にDBのID列1列目にデータが保存されているかの確認");
        assertNotNull(originalValue.get(1).getId(), "既にDBのID列2列目にデータが保存されているかの確認");
        assertNotNull(originalValue.get(2).getId(), "既にDBのID列3列目にデータが保存されているかの確認");
        assertNotNull(originalValue.get(3).getId(), "既にDBのID列4列目にデータが保存されているかの確認");
        assertNotNull(originalValue.get(4).getId(), "既にDBのID列5列目にデータが保存されているかの確認");
        assertNotNull(originalValue.get(5).getId(), "既にDBのID列6列目にデータが保存されているかの確認");

        assertEquals(6, originalValue.size(), "既存のDBに保存されているデータ数の確認");

        var amount = new Amount();
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> updatedValue = amountRepository.findAll();

        assertNotNull(updatedValue.get(6).getId(), "与えられたデータがDBのID列の最後尾に追加されていることを確認");
        assertEquals(7, updatedValue.size(), "既存のDBに保存されているデータ数に今回追加したデータ数を合わせたデータ数が取得される");
    }


    @Test
    @Sql("/test-schema.sql")
    @DisplayName("与えられたデータのキーと一致するデータがDBに存在する時、与えられたデータでDBが更新されること")
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
    @DisplayName("与えられたデータのキーが一致するデータがDBに存在しない時、DBが更新されず新たなデータとして登録されているかの確認")
    void testUpdateWhenDataNotContainsSameKeyWithGivenData() {


        List<Amount> originalValues = amountRepository.findAll();

        assertEquals(6, originalValues.size(), "DBに保存されているデータ数を確認");

        assertNotNull(originalValues.get(0).getId(), "DBのID列1列目に保存されているデータが保存されているかの確認");
        assertNotNull(originalValues.get(1).getId(), "DBのID列2列目に保存されているデータが保存されているかの確認");
        assertNotNull(originalValues.get(2).getId(), "DBのID列3列目に保存されているデータが保存されているかの確認");
        assertNotNull(originalValues.get(3).getId(), "DBのID列4列目に保存されているデータが保存されているかの確認");
        assertNotNull(originalValues.get(4).getId(), "DBのID列5列目に保存されているデータが保存されているかの確認");
        assertNotNull(originalValues.get(5).getId(), "DBのID列6列目に保存されているデータが保存されているかの確認");

        var amount = new Amount();
        amount.setId(7L);
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> updatedValues = amountRepository.findAll();

        assertNotNull(updatedValues.get(6), "与えられたデータのキーが一致するデータがDBに存在しない時、与えられたデータでDBの更新ではなく、与えられたデータのキーで新たなデータが登録されているかの確認");
        assertEquals(7, updatedValues.size(), "データが与えられた後のDBに保存されているデータ数を確認");

    }
}