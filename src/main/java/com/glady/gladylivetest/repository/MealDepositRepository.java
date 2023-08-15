package com.glady.gladylivetest.repository;

import com.glady.gladylivetest.entity.MealDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MealDepositRepository extends JpaRepository<MealDeposit, Long> {
    @Query("SELECT m FROM MealDeposit m WHERE m.user.id = :userId")
    List<MealDeposit> findByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM MealDeposit m WHERE m.user.id = :userId AND m.id = :mealDepositId")
    Optional<MealDeposit> findByUserIdAndMealDepositId(@Param("userId") Long userId, @Param("mealDepositId") Long mealDepositId);
}