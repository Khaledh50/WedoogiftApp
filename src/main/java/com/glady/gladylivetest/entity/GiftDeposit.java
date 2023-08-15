package com.glady.gladylivetest.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "Gift")
@Entity
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDate distributionDate;
    private LocalDate expiryDate;
    // Other fields, getters, setters, and constructors

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    User user;

}
