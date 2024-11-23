package com.nassar.usermanagement.exception.handler;

import com.nassar.usermanagement.exception.BusinessException;
import com.nassar.usermanagement.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e){
        e.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Throwable e) {
        e.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse("Internal server error");

        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
        e.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

}
