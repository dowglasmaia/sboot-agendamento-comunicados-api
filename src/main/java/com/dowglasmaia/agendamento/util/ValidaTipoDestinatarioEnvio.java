package com.dowglasmaia.agendamento.util;

import static com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO.TipoDestinatarioEnum;

public class ValidaTipoDestinatarioEnvio {

    public static boolean validaDestinatario(String destinatario, TipoDestinatarioEnum tipo){
        if ((TipoDestinatarioEnum.SMS.equals(tipo) || TipoDestinatarioEnum.WHATSAPP.equals(tipo))
              && ValidaNumeroCelular.isValidPhoneNumber(destinatario)) {
            return true;
        }

        if (TipoDestinatarioEnum.EMAIL.equals(tipo) && ValidaEmail.isValidEmail(destinatario)) {
            return true;
        }

        if (TipoDestinatarioEnum.PUSH.equals(tipo) ) {
            return true;
        }

        return false;
    }
}