package com.cefet.rj.mg.sisca.domain.nota;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;

public record DadosCadastroNota(
        Long idAluno,
        Long idTurma,
        int p1,
        int p2,
        int pf


) {
}
