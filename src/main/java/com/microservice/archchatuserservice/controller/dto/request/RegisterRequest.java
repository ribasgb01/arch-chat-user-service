package com.microservice.archchatuserservice.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(

        @NotBlank(message = "É necessário informar um email")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "É necessário informar uma senha")
        @Size(min = 5, message = "Senha precisa ter pelo menos 5 caracteres")
        String password,

        @NotBlank(message = "É necessário informar o seu nome de usuário")
        @Size(min = 5, message = "Nome do usuário precisa ter pelo menos 5 caracteres")
        String nickname,

        @NotBlank(message = "É necessário informar o nome completo")
        @Size(min = 3, message = "Nome inválido")
        String username,

        @NotNull(message = "É necessário informar a data de nascimento")
        LocalDate birthDate

) {}
