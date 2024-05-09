package com.cefet.rj.mg.sisca.domain.funcionario;

import com.cefet.rj.mg.sisca.domain.usuario.Usuario;

public record DadosCadastroFuncionario(
        Usuario usuario,
        String matricula_funcionario
) {
}
