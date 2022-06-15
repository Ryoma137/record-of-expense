package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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


    }

    @Test
    @Sql("/test-schema.sql")
    @DisplayName("与えられたデータのキーが一致するデータがDBに存在しない時、DBが更新されないこと")
    void testUpdateWhenDataNotContainsSameKeyWithGivenData() {

    }

    //    @Test
//    @Sql("/test-schema.sql")
//    @DisplayName("与えられたデータのキーがDBのテーブル内に存在するデータのキーと重複していない時、与えられたデータがDBに追加されていること")
//    void testAddDataWhenDataNotContainsSameKeyWithGivenData() {
//
//        var amount = new Amount();
//        //  amount.setId(1);
//        amount.setName("testName");
//        amount.setPrice(1000);
//        amount.setCategory("testCategory");
//        amount.setComments("testComment");
//
//        amountRepository.save(amount);
//
//        var actual = amountRepository.findAll();
//
//
//        assertEquals(amount.getId(), actual.get(0).getId(), "追加するデータとレコードの1列目に保存されているデータが重複していない");
//        assertNotEquals(amount, actual.get(1), "追加するデータとレコードの2列目に保存されているデータが重複していない");
//        assertNotEquals(amount, actual.get(2), "追加するデータとレコードの3列目に保存されているデータが重複していない");
//        assertNotEquals(amount, actual.get(3), "追加するデータとレコードの4列目に保存されているデータが重複していない");
//        assertNotEquals(amount, actual.get(4), "追加するデータとレコードの5列目に保存されているデータが重複していない");
//        assertNotEquals(amount, actual.get(5), "追加するデータとレコードの6列目に保存されているデータが重複していない");
//        assertEquals(amount, actual.get(6), "与えられたデータがDBに追加されている");
//        assertEquals(amount.getName(), actual.get(7).getName(), "与えられたデータがDBに追加されている");
//
//        assertEquals(7, actual.size(), "test-schema.sqlに書いてあるレコード数と追加したレコード数の合計数と同じレコード数がDBに登録されている");
//    }

//    @Test
//    @Sql("/test-schema.sql")
//    @DisplayName("与えられたデータのキーがDBのテーブル内に存在するデータのキーと重複している時、与えられたデータがDBに追加されないこと")
//    void testAddDataWhenDataContainsSameKeyWithGivenData() {
//        var amount = new Amount();
//        amount.setId(1);
//        amount.setName("testName");
//        amount.setPrice(1000);
//        amount.setCategory("testCategory");
//        amount.setComments("testComment");
//
//        var actual = amountRepository.findAll();
//        actual.add(amount);
//
//        assertEquals(amount.getId(), actual.get(0).getId(), "与えられたデータのキーがDBのテーブル内に存在する");
//        assertNotEquals("testName", actual.get(0).getName(), "与えられたデータのキーが重複しているため、名前が更新されていない");
//        assertNotEquals("testCategory", actual.get(0).getCategory(), "与えられたデータのキーが重複しているため、カテゴリーが更新されていない");
//        assertNotEquals("testComment", actual.get(0).getComments(), "与えられたデータのキーが重複しているため、コメントが更新されていない");
//
//
//    }

}