package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;

public record DadosDetalhamentoAlunoTurma(
        TurmaAlunoId Id_turma,
        Aluno matricula_aluno,
        Turma id_turma,
        StatusAlunoTurma situacao

) {
    public DadosDetalhamentoAlunoTurma(AlunoTurma alunoTurma) {
        this(alunoTurma.getId(),
                alunoTurma.getMatricula_aluno(),
                alunoTurma.getId_turma(),
                alunoTurma.getSituacao());
    }
}
