package com.glady.gladylivetest;
import com.glady.gladylivetest.entity.GiftDeposit;
import com.glady.gladylivetest.entity.MealDeposit;
import com.glady.gladylivetest.entity.User;
import com.glady.gladylivetest.entity.request.GiftDepositRequest;
import com.glady.gladylivetest.exception.GiftException;
import com.glady.gladylivetest.repository.GiftDepositRepository;
import com.glady.gladylivetest.service.GiftDepositService;
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
public class GiftDepositServiceTest {

    @InjectMocks
    private GiftDepositService giftDepositService;

    @Mock
    private GiftDepositRepository giftDepositRepository;

    @Mock
    private UserService userService;


    @BeforeEach
    public void setup() {

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        GiftDeposit expectedGiftDeposit  = GiftDeposit.builder()
                .build()
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.now().plusDays(365))
                .setUser(user);

        giftDepositRepository.save(expectedGiftDeposit);
    }

    @Test
    public void test_AddDistributeGiftDeposit() {

        GiftDepositRequest request = new GiftDepositRequest();
        request.setAmount(new BigDecimal("100.00")); // Montant du cadeau
        request.setDistributionDate(LocalDate.of(2023, 8, 20));

        User user = User.builder().build()
                        .setId(1L)
                         .setName("John Doe")
                        .setPseudo("uniTest");

        GiftDeposit expectedGiftDeposit  = GiftDeposit.builder()
                .build()
                .setId(1L)
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.now().plusDays(365))
                .setUser(user);

        when(giftDepositRepository.save(any(GiftDeposit.class))).thenReturn(expectedGiftDeposit);
        GiftDeposit result = giftDepositService.addDistributeGiftDeposit(request);

        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedGiftDeposit);
    }

    @Test
    public void test_GetDistributeGiftsDeposits() {

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        List<GiftDeposit> expectedGiftDeposits = Arrays.asList(
                new GiftDeposit()
                        .setId(1L)
                        .setAmount(new BigDecimal("100.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 20))
                        .setExpiryDate(LocalDate.of(2023, 8, 20).plusDays(365))
                        .setUser(user), // User doit être initialisé selon vos besoins
                new GiftDeposit()
                        .setId(2L)
                        .setAmount(new BigDecimal("75.00"))
                        .setDistributionDate(LocalDate.of(2023, 8, 1))
                        .setExpiryDate(LocalDate.of(2023, 8, 1).plusDays(365))
                        .setUser(user) // User doit être initialisé selon vos besoins
        );


        when(giftDepositRepository.findByUserId(user.getId())).thenReturn(expectedGiftDeposits);
        List<GiftDeposit> result = giftDepositService.getDistributeGiftsDeposits(user.getId());

        assertEquals(expectedGiftDeposits, result);
    }

    @Test
    public void test_GetDistributeGift() {
        Long giftDepositId = 1L;

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        GiftDeposit expectedGiftDeposit  = GiftDeposit.builder()
                .build()
                .setId(1L)
                .setAmount(new BigDecimal("100.00"))
                .setDistributionDate(LocalDate.of(2023, 8, 20))
                .setExpiryDate(LocalDate.of(2023, 8, 20).plusDays(365))
                .setUser(user);


        when(giftDepositRepository.findByUserIdAndGiftDepositId(user.getId(), giftDepositId)).thenReturn(Optional.of(expectedGiftDeposit));
        GiftDeposit result = giftDepositService.getDistributeGift(user.getId(),giftDepositId);

        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedGiftDeposit);
    }

    @Test
    public void testGetDistributeGift_WhenNotFound() {
        Long giftDepositId = 1L;

        User user = User.builder().build()
                .setId(1L)
                .setName("John Doe")
                .setPseudo("uniTest");

        assertThrows(GiftException.class, () -> giftDepositService.getDistributeGift(user.getId(),giftDepositId));
    }
}
