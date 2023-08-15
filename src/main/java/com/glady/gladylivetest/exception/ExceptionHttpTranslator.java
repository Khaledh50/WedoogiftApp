package com.glady.gladylivetest.exception;

import com.glady.gladylivetest.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHttpTranslator {

    @ExceptionHandler(UserException.class)
    public ResponseEntity businessException(HttpServletRequest request, final UserException e) {
        return new ResponseEntity(new ExceptionDto().setErrormessage(e.getMessage()).setPath(request.getServletPath()),
                e.getException().getStatus());
    }

    @ExceptionHandler(GiftException.class)
    public ResponseEntity eventSearchException(HttpServletRequest request, final GiftException e) {
        return new ResponseEntity(new ExceptionDto().setErrormessage(e.getMessage()).setPath(request.getServletPath()),
                e.getException().getStatus());
    }

    @ExceptionHandler(MealException.class)
    public ResponseEntity betSearchException(HttpServletRequest request, final MealException e) {
        return new ResponseEntity(new ExceptionDto().setErrormessage(e.getMessage()).setPath(request.getServletPath()),
                e.getException().getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return new ResponseEntity(new ExceptionDto().setErrormessage("Request mal formée").setPath(request.getRequestURI()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity ResponseEntityhandleInternalServerError(Exception ex, HttpServletRequest request) {

        return new ResponseEntity(new ExceptionDto().setErrormessage("Erreur serveur").setPath(request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
