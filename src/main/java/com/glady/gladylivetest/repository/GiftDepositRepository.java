package com.glady.gladylivetest.repository;

import com.glady.gladylivetest.entity.GiftDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GiftDepositRepository extends JpaRepository<GiftDeposit, Long> {

    @Query("SELECT m FROM GiftDeposit m WHERE m.user.id = :userId")
    List<GiftDeposit> findByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM GiftDeposit m WHERE m.user.id = :userId AND m.id = :giftDepositId")
    Optional<GiftDeposit> findByUserIdAndGiftDepositId(@Param("userId") Long userId, @Param("giftDepositId") Long giftDepositId);


}