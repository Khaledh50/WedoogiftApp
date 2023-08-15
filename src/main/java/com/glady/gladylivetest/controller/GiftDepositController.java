package com.glady.gladylivetest.controller;

import com.glady.gladylivetest.entity.GiftDeposit;
import com.glady.gladylivetest.entity.request.GiftDepositRequest;
import com.glady.gladylivetest.service.GiftDepositService;
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
public class GiftDepositController {

    private final GiftDepositService giftDepositService;
    private final UserService userService;


    @PostMapping(Urls.ADD_GIFT)
    public ResponseEntity<GiftDeposit> distributeGiftDeposit(@RequestBody GiftDepositRequest request) {

        GiftDeposit giftDeposit = giftDepositService.addDistributeGiftDeposit(request);
        return ResponseEntity.ok(giftDeposit);
    }

    @GetMapping(Urls.GET_GIFTS)
    public ResponseEntity<Object> getDistributeGiftsDeposits() {

        var user = userService.findUserByPseudo("uniTest");
        List<GiftDeposit> giftDeposits  = giftDepositService.getDistributeGiftsDeposits(user.getId());
        return giftDeposits.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pas de résultat")
                : ResponseEntity.ok(giftDeposits);
    }

    @GetMapping(Urls.GET_GIFT)
    public GiftDeposit getDistributeGift(@PathVariable("giftId") Long giftId) {

        var user = userService.findUserByPseudo("uniTest");
        return giftDepositService.getDistributeGift(giftId,user.getId());
    }


}