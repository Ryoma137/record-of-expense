package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface AmountRepository extends JpaRepository<Amount, Integer> {

    @Query("SELECT category FROM Amount")
    List<String> getCategoryList();

//    @Modifying
//    @Query("INSERT INTO Amount SET name=?, price=?, category=?,comments=?")
//    List<Amount> registerAmount(Amount registerAmount);

    @Modifying
    @Transactional
    @Query("INSERT INTO Amount(id, name, price,category,comments) VALUES(?,?,?,?,?)")
    Amount registerAmount(long id, String name, int price, String category, String comments);

}