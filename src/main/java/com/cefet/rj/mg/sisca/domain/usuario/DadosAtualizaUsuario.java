package com.cefet.rj.mg.sisca.domain.usuario;

import java.time.LocalDate;

public record DadosAtualizaUsuario(
        Long id_usuario,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        RoleEnum role
) {

}
