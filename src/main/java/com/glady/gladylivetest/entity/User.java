package com.glady.gladylivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "user")
@Entity
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;
    // Other fields, getters, setters, and constructors

    @Column(name = "pseudo")
    private String pseudo;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(targetEntity = GiftDeposit.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<GiftDeposit> giftDeposits = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(targetEntity = MealDeposit.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<MealDeposit> mealDeposits = new ArrayList<>();;

}