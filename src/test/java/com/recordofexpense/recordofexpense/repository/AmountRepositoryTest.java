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
    @DisplayName("DBのテーブル内にデータが存在する時、ID列の最後尾に与えられたIDの数値+1の値でIDが生成され、データがDB追加されていること")
    void testAddDataWhenDataExistInDB() {

        var amount = new Amount();
        amount.setName("testName");
        amount.setPrice(1000);
        amount.setCategory("testCategory");
        amount.setComments("testComment");

        amountRepository.save(amount);
        List<Amount> actual = amountRepository.findAll();

        assertEquals(7, actual.size());

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