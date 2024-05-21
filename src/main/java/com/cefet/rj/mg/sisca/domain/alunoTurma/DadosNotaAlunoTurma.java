package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.nota.Nota;

import java.util.List;

public record DadosNotaAlunoTurma(
        String nomeAluno,
        String nomeTurma,
        List<Nota> notas
) {
    public DadosNotaAlunoTurma(AlunoTurma alunoTurma) {
        this(alunoTurma.getAluno().getUsuario().getNome(), alunoTurma.getTurma().getNome(), alunoTurma.getNotas());
    }
}
