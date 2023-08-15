package com.glady.gladylivetest.service;

import com.glady.gladylivetest.repository.GiftDepositRepository;
import com.glady.gladylivetest.repository.MealDepositRepository;
import com.glady.gladylivetest.entity.GiftDeposit;
import com.glady.gladylivetest.entity.MealDeposit;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class BalanceCalculationService {
    private final GiftDepositRepository giftDepositRepository;
    private final MealDepositRepository mealDepositRepository;


    public BigDecimal calculateUserBalance(Long userId) {

        BigDecimal giftBalance = calculateGiftBalance(userId,LocalDate.now());
        BigDecimal mealBalance = calculateMealBalance(userId,LocalDate.now());
        return giftBalance.add(mealBalance);
    }

    private BigDecimal calculateGiftBalance(Long userId,LocalDate date) {
        List<GiftDeposit> giftDeposits = giftDepositRepository.findByUserId(userId);
        return giftDeposits.stream()
                .filter(deposit -> !deposit.getExpiryDate().isBefore(date))
                .map(GiftDeposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateMealBalance(Long userId,LocalDate date) {
        List<MealDeposit> mealDeposits = mealDepositRepository.findByUserId(userId);
        return mealDeposits.stream()
                .filter(deposit -> !deposit.getExpiryDate().isBefore(date))
                .map(MealDeposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}