package com.dowglasmaia.agendamento.execptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

}
