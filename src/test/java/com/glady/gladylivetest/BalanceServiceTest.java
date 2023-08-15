package com.glady.gladylivetest;

import com.glady.gladylivetest.entity.GiftDeposit;
import com.glady.gladylivetest.entity.MealDeposit;
import com.glady.gladylivetest.entity.User;

import com.glady.gladylivetest.repository.GiftDepositRepository;
import com.glady.gladylivetest.repository.MealDepositRepository;
import com.glady.gladylivetest.service.BalanceCalculationService;
import com.glady.gladylivetest.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DataJpaTest
@ActiveProfiles("test")
public class BalanceServiceTest {

    @InjectMocks
    private BalanceCalculationService balanceCalculationService;

    @Mock
    private GiftDepositRepository giftDepositRepository;

    @Mock
    private MealDepositRepository mealDepositRepository;



    @BeforeEach
    public void setup() {

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        GiftDeposit giftDeposit  = GiftDeposit.builder()
                .build()
                .setAmount(new BigDecimal("50.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 1))
                .setExpiryDate(LocalDate.of(2023, 8, 15).plusDays(45))
                .setUser(user);

        giftDepositRepository.save(giftDeposit);

        GiftDeposit giftDeposit2  = GiftDeposit.builder()
                .build()
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 15))
                .setExpiryDate(LocalDate.of(2023, 8, 15).plusDays(30))
                .setUser(user);

        giftDepositRepository.save(giftDeposit2);

        MealDeposit mealDeposit  = MealDeposit.builder()
                .build()
                .setAmount(new BigDecimal("50.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 15))
                .setExpiryDate(LocalDate.of(2023, 8, 15).plusDays(45))
                .setUser(user);

        mealDepositRepository.save(mealDeposit);

        MealDeposit mealDeposit2  = MealDeposit.builder()
                .build()
                .setAmount(new BigDecimal("20.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 15))
                .setExpiryDate(LocalDate.of(2023, 8, 15).plusDays(30))
                .setUser(user);

        mealDepositRepository.save(mealDeposit2);
    }

    @Test
    public void testCalculateUserBalance() {
        Long userId = 1L;

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        when(giftDepositRepository.findByUserId(userId)).thenReturn(
               Arrays.asList(
                        new GiftDeposit()
                                .setId(1L)
                                .setAmount(new BigDecimal("50.00"))
                                .setDistributionDate(LocalDate.of(2023, 8, 1))
                                .setExpiryDate(LocalDate.of(2023, 8, 1).plusDays(45))
                                .setUser(user), // User doit être initialisé selon vos besoins
                        new GiftDeposit()
                                .setId(2L)
                                .setAmount(new BigDecimal("100.00"))
                                .setDistributionDate(LocalDate.of(2023, 8, 15))
                                .setExpiryDate(LocalDate.of(2023, 7, 15).plusDays(50))
                                .setUser(user)
                )

        );

        when(mealDepositRepository.findByUserId(userId)).thenReturn(Arrays.asList(
                new MealDeposit()
                        .setId(1L)
                        .setAmount(new BigDecimal("50.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 1))
                        .setExpiryDate(LocalDate.now().plusDays(45))
                        .setUser(user),

                 new MealDeposit()
                        .setId(2L)
                        .setAmount(new BigDecimal("20.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 15))
                        .setExpiryDate(LocalDate.now().plusDays(50))
                        .setUser(user)
            )
        );

        BigDecimal result = balanceCalculationService.calculateUserBalance(userId);
        assertEquals(new BigDecimal("220.00"), result);
    }

}
