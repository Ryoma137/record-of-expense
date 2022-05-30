package com.recordofexpense.recordofexpense.repository;

import com.recordofexpense.recordofexpense.entity.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountRepository extends JpaRepository<Amount,Integer> {

    @Query("SELECT category FROM Amount")
    Amount getCategoryList(String category);
}
