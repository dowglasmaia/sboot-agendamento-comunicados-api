package com.dowglasmaia.agendamento.execptions;

import com.dowglasmaia.provider.model.ResponseErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ResponseErrorDTO> handlerUnprocessableEntityExeption(BusinessException ex){
        ResponseErrorDTO exceptionResponse = new ResponseErrorDTO();
        exceptionResponse.setCode(ex.getHttpStatus().name());
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ResponseErrorDTO>(exceptionResponse, ex.getHttpStatus());
    }

}
