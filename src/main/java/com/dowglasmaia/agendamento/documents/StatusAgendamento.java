package com.dowglasmaia.agendamento.documents;

public enum StatusAgendamento {

    AGENDADO("Agendado"),
    ENVIADO("Enviado"),
    CANCELADO("Cancelado");

    private String value;

    StatusAgendamento(String value){
        this.value = value;
    }

    public static StatusAgendamento fromValue(String text){
        for (StatusAgendamento status : StatusAgendamento.values()) {
            if (status.value.equals(text)) {
                return status;
            }
        }
        return null;
    }

}
