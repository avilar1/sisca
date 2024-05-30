package com.cefet.rj.mg.sisca.domain.usuario;

import java.time.LocalDate;

public record DadosDetalhamentoUsuario(

        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        RoleEnum role
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(

              usuario.getNome(),
              usuario.getCpf(),
              usuario.getEmail(),
              usuario.getTelefone(),
              usuario.getData_nascimento(),
              usuario.getRole()
        );

    }
}
