package com.dowglasmaia.agendamento.util;

import com.dowglasmaia.agendamento.execptions.BusinessException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {

    public static LocalDate convertStringToLocalDate(String dateString){
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new BusinessException(
                  "A data fornecida é inválida: '" + dateString + "'. Por favor revise e tente novamente.",
                  HttpStatus.UNPROCESSABLE_ENTITY
            );
        }
    }

    public static LocalTime convertStringToLocalTime(String timeString){
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new BusinessException(
                  "O tempo fornecido é inválido: '" + timeString + "'. Por favor revise e tente novamente.",
                  HttpStatus.UNPROCESSABLE_ENTITY
            );
        }
    }

}
