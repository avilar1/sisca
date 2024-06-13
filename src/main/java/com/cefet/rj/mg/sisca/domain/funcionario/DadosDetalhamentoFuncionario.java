package com.cefet.rj.mg.sisca.domain.funcionario;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.funcionario.RoleTipoFuncionarioEnum;
import jakarta.persistence.DiscriminatorColumn;

import java.time.LocalDate;

public record DadosDetalhamentoFuncionario(
        Long id_funcionario,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        int matricula_funcionario,
        String tipo_funcionario
) {
    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(
                funcionario.getId_funcionario(),
                funcionario.getUsuario().getNome(),
                funcionario.getUsuario().getCpf(),
                funcionario.getUsuario().getEmail(),
                funcionario.getUsuario().getTelefone(),
                funcionario.getUsuario().getData_nascimento(),
                funcionario.getMatricula_funcionario(),
                funcionario.getClass().getSimpleName()
        );
    }
}
