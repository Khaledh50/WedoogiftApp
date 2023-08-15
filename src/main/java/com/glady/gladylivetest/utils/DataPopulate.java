package com.glady.gladylivetest.utils;

import com.glady.gladylivetest.entity.*;
import com.glady.gladylivetest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;


@Configuration
@Order(0)
@RequiredArgsConstructor
public class DataPopulate {

    private final GiftDepositRepository giftDepositRepository;

    private final  MealDepositRepository mealDepositRepository;

    private final UserRepository userRepository;


    @PostConstruct
    public void createRealPsgData() {


        User user= userRepository.save(new User().setPseudo("uniTest").setName("test"));


        mealDepositRepository.save(new MealDeposit()
                        .setAmount(new BigDecimal("20.0"))
                        .setExpiryDate(LocalDate.of(2023,8,20))
                        .setDistributionDate(LocalDate.of(2023,8,20))
                        .setUser(user)
                );
        mealDepositRepository.save(new MealDeposit()
                .setAmount(new BigDecimal("15.0"))
                .setExpiryDate(LocalDate.of(2023,9,20))
                .setDistributionDate(LocalDate.of(2023,8,20))
                .setUser(user)
        );
        mealDepositRepository.save(new MealDeposit()
                .setAmount(new BigDecimal("50.0"))
                .setExpiryDate(LocalDate.of(2023,10,20))
                .setDistributionDate(LocalDate.of(2023,9,20))
                .setUser(user)
        );
        mealDepositRepository.save(new MealDeposit()
                .setAmount(new BigDecimal("10.0"))
                .setExpiryDate(LocalDate.of(2023,11,20))
                .setDistributionDate(LocalDate.of(2023,10,20))
                .setUser(user)
        );
        mealDepositRepository.save(new MealDeposit()
                .setAmount(new BigDecimal("30.0"))
                .setExpiryDate(LocalDate.of(2023,6,20))
                .setDistributionDate(LocalDate.of(2023,5,20))
                .setUser(user)
        );


        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,6,20))
                .setDistributionDate(LocalDate.of(2023,4,20))
                .setUser(user)
        );
        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,7,20))
                .setDistributionDate(LocalDate.of(2023,6,20))
                .setUser(user)
        );
        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,8,20))
                .setDistributionDate(LocalDate.of(2023,8,20))
                .setUser(user)
        );
        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,9,20))
                .setDistributionDate(LocalDate.of(2023,8,20))
                .setUser(user)
        );
        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,10,20))
                .setDistributionDate(LocalDate.of(2023,9,20))
                .setUser(user)
        );
        giftDepositRepository.save(new GiftDeposit()
                .setAmount(new BigDecimal("20.0"))
                .setExpiryDate(LocalDate.of(2023,11,20))
                .setDistributionDate(LocalDate.of(2023,9,20))
                .setUser(user)
        );


    }



}
