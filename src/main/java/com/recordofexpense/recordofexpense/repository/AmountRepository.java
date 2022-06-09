package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends JpaRepository<Amount,Integer> {

    @Query("SELECT category FROM Amount")
    List<String> getCategoryList();

    @Query("SELECT price FROM Amount")
    List<Integer> getPrice();


}
