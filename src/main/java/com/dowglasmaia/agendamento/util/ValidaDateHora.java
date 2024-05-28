package com.dowglasmaia.agendamento.util;

import com.dowglasmaia.agendamento.execptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class ValidaDateHora {

    public static LocalDateTime validaDataHoraFutura(String dataString, String horaString){
        log.info("Inicio do metodo validaDataHoraFutura");

        LocalDate data = convertStringToLocalDate(dataString);
        LocalTime hora = convertStringToLocalTime(horaString);

        LocalDateTime dateTime = LocalDateTime.of(data, hora);
        log.info("DATA_HORA: {}", dateTime);

        if (dateTime.isBefore(LocalDateTime.now())) {
            log.error("A data e hora devem ser posteriores à data e hora atual: {}", dateTime);
            throw new BusinessException(
                  "A data e hora devem ser posteriores à data e hora atual: " + dateTime + ". Por favor, revise e tente novamente.",
                  HttpStatus.UNPROCESSABLE_ENTITY
            );
        }

        return dateTime;
    }


    public static LocalDate convertStringToLocalDate(String dateString){
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            log.error("A data fornecida é inválida: {}", dateString);
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
            log.error("O tempo fornecido é inválido: {}", timeString);
            throw new BusinessException(
                  "O tempo fornecido é inválido: '" + timeString + "'. Por favor revise e tente novamente.",
                  HttpStatus.UNPROCESSABLE_ENTITY
            );
        }
    }

}
