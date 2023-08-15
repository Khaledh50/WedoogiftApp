package com.glady.gladylivetest.utils;

import lombok.extern.log4j.Log4j2;


@Log4j2
public final class Helpers {

    private Helpers() {

    }

    public static int getRandomIndex(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }





}
