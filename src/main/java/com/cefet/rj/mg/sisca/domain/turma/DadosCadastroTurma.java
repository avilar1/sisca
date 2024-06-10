package com.cefet.rj.mg.sisca.domain.turma;

import java.util.Date;

public record DadosCadastroTurma(

        Long id_funcionario,
        Long id_materia,
        Date semestre_turma

) {
}
