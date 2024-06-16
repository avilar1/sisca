package com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia;

import java.sql.Date;

public record DadosListagemTurmaAlunoFrequencia(
        Long id_turma,
        Long id_aluno,
        Date falta
) {

    public DadosListagemTurmaAlunoFrequencia(TurmaAlunoFrequencia turmaAlunoFrequencia) {
        this(
                turmaAlunoFrequencia.getId().getIdTurma(),
                turmaAlunoFrequencia.getId().getIdAluno(),
                turmaAlunoFrequencia.getFaltou()
        );
    }
}
