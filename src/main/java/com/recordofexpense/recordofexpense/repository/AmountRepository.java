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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Amount(id, name, price,category,comments) VALUES(?,?,?,?,?)", nativeQuery = true)
    Amount save(Amount amount);

}