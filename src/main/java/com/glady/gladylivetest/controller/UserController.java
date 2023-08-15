package com.glady.gladylivetest.controller;

import com.glady.gladylivetest.entity.User;
import com.glady.gladylivetest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping(Urls.CURRENT_USER)
    public User fetchCustomer() {
        return userService.findUserByPseudo("uniTest");
    }






}
