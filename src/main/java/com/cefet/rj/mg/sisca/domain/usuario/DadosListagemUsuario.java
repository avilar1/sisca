package com.cefet.rj.mg.sisca.domain.usuario;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.curso.Curso;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosListagemUsuario(
        Long id_usuario,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        RoleEnum role
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(
              usuario.getId_usuario(),
              usuario.getNome(),
              usuario.getCpf(),
              usuario.getEmail(),
              usuario.getTelefone(),
              usuario.getData_nascimento(),
              usuario.getRole()
        );

    }
}
