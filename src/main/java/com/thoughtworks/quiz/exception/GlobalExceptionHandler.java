package com.thoughtworks.quiz.exception;

import com.thoughtworks.quiz.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    // TODO GTB-4: - 存在没有使用的方法参数
    public ErrorMessage userNotFoundExceptionHandler(UserNotFoundException e){
        return new ErrorMessage(new Date().toString(),HttpStatus.NOT_FOUND.getReasonPhrase(), String.valueOf(HttpStatus.NOT_FOUND.value()),e.getMessage());
    }
}
