package com.glady.gladylivetest.entity.request;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GiftDepositRequest {
    private BigDecimal amount;
    private LocalDate distributionDate;

}