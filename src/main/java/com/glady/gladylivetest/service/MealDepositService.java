package com.glady.gladylivetest.service;

import com.glady.gladylivetest.entity.request.MealDepositRequest;
import com.glady.gladylivetest.exception.MealException;
import com.glady.gladylivetest.repository.MealDepositRepository;
import com.glady.gladylivetest.entity.MealDeposit;
import com.glady.gladylivetest.types.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
@Log4j2
public class MealDepositService {

    private final MealDepositRepository mealDepositRepository;
    private final UserService userService;

    public MealDeposit addDistributeMealDeposit(MealDepositRequest request) {

        var user = userService.findUserByPseudo("uniTest");

        LocalDate expiryDate = request.getDistributionDate().plusYears(1).withMonth(2).withDayOfMonth(28);
        MealDeposit mealDeposit = MealDeposit.builder()
                .build().setAmount(request.getAmount())
                .setExpiryDate(expiryDate)
                .setDistributionDate(request.getDistributionDate())
                .setUser(user);

         return mealDepositRepository.save(mealDeposit);
    }
    public List<MealDeposit> getDistributeMealsDeposits(Long userId) {

        return mealDepositRepository.findByUserId(userId);
    }
    public MealDeposit getDistributeMeal(Long mealDepositId,Long userId){

        return mealDepositRepository.findByUserIdAndMealDepositId(userId,mealDepositId)
                .orElseThrow(() -> new MealException(format("meal %s not found", mealDepositId), ExceptionType.MEAL_NOT_FOUND));
    }
}