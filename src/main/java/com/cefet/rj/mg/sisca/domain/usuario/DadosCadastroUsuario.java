package com.cefet.rj.mg.sisca.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroUsuario (
        @NotBlank
        String nome,

        @NotBlank
        String senha,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotNull
        LocalDateTime data_nascimento,
        @NotNull
        RoleEnum role
) {
}