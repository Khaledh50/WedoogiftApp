package com.glady.gladylivetest.service;

import com.glady.gladylivetest.entity.request.GiftDepositRequest;
import com.glady.gladylivetest.exception.GiftException;
import com.glady.gladylivetest.repository.GiftDepositRepository;
import com.glady.gladylivetest.entity.GiftDeposit;
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
public class GiftDepositService {
    private final GiftDepositRepository giftDepositRepository;

    private final UserService userService;


    public GiftDeposit addDistributeGiftDeposit(GiftDepositRequest request) {

        var user = userService.findUserByPseudo("uniTest");

        LocalDate expiryDate = request.getDistributionDate().plusDays(365);

        GiftDeposit giftDeposit = GiftDeposit.builder()
                .build().setAmount(request.getAmount())
                .setExpiryDate(expiryDate)
                .setDistributionDate(request.getDistributionDate())
                .setUser(user);

        return giftDepositRepository.save(giftDeposit);
    }

    public List<GiftDeposit> getDistributeGiftsDeposits(Long userId){

        return giftDepositRepository.findByUserId(userId);
    }

    public GiftDeposit getDistributeGift(Long giftDepositId,Long userId){

        return giftDepositRepository.findByUserIdAndGiftDepositId(userId, giftDepositId)
                .orElseThrow(() -> new GiftException(format("gift  %d not found", giftDepositId), ExceptionType.GIFT_NOT_FOUND));
    }


}