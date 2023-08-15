package com.glady.gladylivetest;
import com.glady.gladylivetest.entity.MealDeposit;
import com.glady.gladylivetest.entity.User;
import com.glady.gladylivetest.entity.request.MealDepositRequest;
import com.glady.gladylivetest.exception.MealException;
import com.glady.gladylivetest.repository.MealDepositRepository;
import com.glady.gladylivetest.service.MealDepositService;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
public class MealDepositServiceTest {
    @InjectMocks
    private MealDepositService mealDepositService;

    @Mock
    private MealDepositRepository mealDepositRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        MealDeposit expectedMealDeposit  = MealDeposit.builder()
                .build()
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.now().plusYears(1).withMonth(2).withDayOfMonth(28))
                .setUser(user);

        mealDepositRepository.save(expectedMealDeposit);
    }

    @Test
    public void test_AddDistributeMealDeposit() {

        MealDepositRequest request = new MealDepositRequest();
        request.setAmount(new BigDecimal("100.00")); // Montant du cadeau
        request.setDistributionDate(LocalDate.of(2023, 8, 20));

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        MealDeposit expectedMealDeposit  = MealDeposit.builder()
                .build()
                .setId(1L)
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.now().plusYears(1).withMonth(2).withDayOfMonth(28))
                .setUser(user);


        when(mealDepositRepository.save(any(MealDeposit.class))).thenReturn(expectedMealDeposit);
        MealDeposit result = mealDepositService.addDistributeMealDeposit(request);

        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedMealDeposit);
    }

    @Test
    public void test_GetDistributeMealsDeposits() {

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        List<MealDeposit> expectedGiftDeposits = Arrays.asList(
                new MealDeposit()
                        .setId(1L)
                        .setAmount(new BigDecimal("100.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 20))
                        .setExpiryDate(LocalDate.now().plusYears(1).withMonth(2).withDayOfMonth(28))
                        .setUser(user),
                new MealDeposit()
                        .setId(2L)
                        .setAmount(new BigDecimal("50.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 1))
                        .setExpiryDate(LocalDate.of(2024, 8, 1))
                        .setUser(user)

        );


        when(mealDepositRepository.findByUserId(user.getId())).thenReturn(expectedGiftDeposits);
        List<MealDeposit> result = mealDepositService.getDistributeMealsDeposits(user.getId());

        assertEquals(expectedGiftDeposits, result);
    }

    @Test
    public void test_GetDistributeMeal() {
        Long mealDepositId = 1L;

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        MealDeposit expectedMealDeposit  = MealDeposit.builder()
                .build()
                .setId(1L)
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.now().plusYears(1).withMonth(2).withDayOfMonth(28))
                .setUser(user);

        when(mealDepositRepository.findByUserIdAndMealDepositId(user.getId(), mealDepositId)).thenReturn(Optional.of(expectedMealDeposit));
        MealDeposit result = mealDepositService.getDistributeMeal(user.getId(),mealDepositId);

        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedMealDeposit);
    }

    @Test
    public void testGetDistributeMeal_WhenNotFound() {
        Long mealDepositId = 1L;

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        assertThrows(MealException.class, () -> mealDepositService.getDistributeMeal(user.getId(),mealDepositId));
    }
}
