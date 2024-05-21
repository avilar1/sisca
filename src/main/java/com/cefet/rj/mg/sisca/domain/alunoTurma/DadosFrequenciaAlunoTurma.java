package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.frequencia.Frequencia;
import com.cefet.rj.mg.sisca.domain.nota.Nota;

import java.util.List;

public record DadosFrequenciaAlunoTurma(
        String nomeAluno,
        String nomeTurma,
        List<Frequencia> notas
) {
    public DadosFrequenciaAlunoTurma(AlunoTurma alunoTurma) {
        this(alunoTurma.getAluno().getUsuario().getNome(), alunoTurma.getTurma().getNome(), alunoTurma.getFrequencias());
    }
}
