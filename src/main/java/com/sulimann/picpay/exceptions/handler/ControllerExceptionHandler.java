package com.sulimann.picpay.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sulimann.picpay.exceptions.PicPayException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var fieldErrors = e.getFieldErrors()
            .stream()
            .map(fe -> new FieldErrorDetail(fe.getField(), fe.getDefaultMessage()))
            .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Requisição inválida");
        pb.setProperty("Invalid Params", fieldErrors);

        return pb;
    }

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e){
        return e.toProblemDetail();
    }

    private record FieldErrorDetail(String field, String message){}

}
