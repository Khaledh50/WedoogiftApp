package com.glady.gladylivetest.service;

import com.glady.gladylivetest.entity.User;
import com.glady.gladylivetest.exception.UserException;
import com.glady.gladylivetest.repository.UserRepository;
import com.glady.gladylivetest.types.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserByPseudo(String pseudo) {
        return userRepository.getUserByPseudo(pseudo)
                .orElseThrow(() -> new UserException(format("user %s not found", pseudo), ExceptionType.CUSTOMER_NOT_FOUND));
    }
}
