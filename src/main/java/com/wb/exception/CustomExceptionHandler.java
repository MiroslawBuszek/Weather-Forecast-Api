package com.wb.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wb.payload.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(Exception ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getLocalizedMessage());
        return new ErrorResponse("PropertyNotFoundKey", errors, HttpStatus.NOT_FOUND.value());
    }
}