package com.glady.gladylivetest.controller;

import com.glady.gladylivetest.entity.MealDeposit;
import com.glady.gladylivetest.entity.request.MealDepositRequest;
import com.glady.gladylivetest.service.MealDepositService;
import com.glady.gladylivetest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MealDepositController {


    private final MealDepositService mealDepositService;

    private final UserService userService;

    @PostMapping(Urls.ADD_MEAL)
    public ResponseEntity<MealDeposit> distributeMealDeposit(@RequestBody MealDepositRequest request) {

        MealDeposit mealDeposit = mealDepositService.addDistributeMealDeposit(request);
        return ResponseEntity.ok(mealDeposit);
    }

    @GetMapping(Urls.GET_MEALS)
    public ResponseEntity<Object> getDistributeMealsDeposits() {

        var user = userService.findUserByPseudo("uniTest");
        List<MealDeposit> mealDeposits  = mealDepositService.getDistributeMealsDeposits(user.getId());
        return mealDeposits.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pas de résultat")
                : ResponseEntity.ok(mealDeposits);
    }

    @GetMapping(Urls.GET_MEAL)
    public MealDeposit getDistributeMeal(@PathVariable("mealId") Long mealId) {

        var user = userService.findUserByPseudo("uniTest");
        return mealDepositService.getDistributeMeal(mealId,user.getId());
    }

}
