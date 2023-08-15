package com.glady.gladylivetest.controller;

import com.glady.gladylivetest.entity.response.BalanceResponse;
import com.glady.gladylivetest.service.BalanceCalculationService;
import com.glady.gladylivetest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BalanceController {

    private final BalanceCalculationService balanceCalculationService;

    private final UserService userService;

    @GetMapping(Urls.BALANCE)
    public ResponseEntity<BalanceResponse> getUserBalance() {

        var user = userService.findUserByPseudo("uniTest");
        BigDecimal balance = balanceCalculationService.calculateUserBalance(user.getId());

        BalanceResponse response = new BalanceResponse();
        response.setBalance(balance);

        return ResponseEntity.ok(response);
    }
}