package com.glady.gladylivetest.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ExceptionType {

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND),

    GIFT_NOT_FOUND(HttpStatus.NOT_FOUND),

    MEAL_NOT_FOUND(HttpStatus.NOT_FOUND),

    BET_CONFLICT(HttpStatus.CONFLICT);


    @Getter
    final HttpStatus status;

    ExceptionType(HttpStatus status) {
        this.status = status;
    }

}
