package com.cefet.rj.mg.sisca.domain.financeiro;

import java.util.Date;

public record DadosCadastroMensalidadeAluno(
        Long idAluno,
        Float valor,
        Date dataCriacao
) {}