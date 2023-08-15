package com.glady.gladylivetest.exception;

import com.glady.gladylivetest.types.ExceptionType;
import lombok.Data;

@Data
public class MealException extends RuntimeException {

    private final ExceptionType exception;
    private final String message;

    public MealException(String message, ExceptionType exception) {
        this.message = message;
        this.exception = exception;
    }

}