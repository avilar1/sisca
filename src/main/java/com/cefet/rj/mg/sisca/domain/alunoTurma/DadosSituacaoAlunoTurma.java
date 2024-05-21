package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;

public record DadosSituacaoAlunoTurma(
        String nomeAluno,
        String nomeTurma,
        String situacao
) {
    public DadosSituacaoAlunoTurma(AlunoTurma alunoTurma) {
        this(alunoTurma.getAluno().getUsuario().getNome(), alunoTurma.getTurma().getNome(), alunoTurma.getSituacao());
    }
}
