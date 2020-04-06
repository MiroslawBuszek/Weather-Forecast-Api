package com.wb.exception;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wb.payload.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private List<String> errorListFormException(Exception ex) {
        return Collections.singletonList(ex.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(Exception ex, WebRequest request) {
        return new ErrorResponse("PropertyNotFoundKey", errorListFormException(ex), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ErrorResponse handleHttpClientErrorException(Exception ex, WebRequest request) {
        return new ErrorResponse("NotFound", errorListFormException(ex), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(ConnectException.class)
    public ErrorResponse handleConnectException(Exception ex, WebRequest request) {
        return new ErrorResponse("TimeOut", errorListFormException(ex), HttpStatus.REQUEST_TIMEOUT.value());
    }

    @ExceptionHandler(NoRouteToHostException.class)
    public ErrorResponse handleNoRouteToHostException(Exception ex, WebRequest request) {
        return new ErrorResponse("NoRout", errorListFormException(ex), HttpStatus.GATEWAY_TIMEOUT.value());
    }
}