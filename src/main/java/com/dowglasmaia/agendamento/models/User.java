package com.dowglasmaia.agendamento.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    @NotBlank
    @JsonProperty("username")
    private String userName;


    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
}
