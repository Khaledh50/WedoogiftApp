package com.glady.gladylivetest.controller;

public interface Urls {

    String BASE_PATH = "/api/v1";

    /** giftDeposit apis **/
    String ADD_GIFT = "/gift-distribute";

    String GET_GIFT = "/gift-distribute/gifts/{giftId}";

    String GET_GIFTS = "/gift-distribute/gifts";


    /** mealDeposit api **/
    String ADD_MEAL = "/meal-distribute";

    String GET_MEAL = "/meal-distribute/meals/{mealId}";
    String GET_MEALS = "/meal-distribute/meals";


    /** balance api **/
    String BALANCE = "/balance";

    /** customers apis **/
    String USERS = "/customers";

    String CURRENT_USER = USERS + "/current";

}
