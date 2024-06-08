package com.cefet.rj.mg.sisca.domain.turma;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;

import java.util.Date;
import java.util.List;

public record DadosDetalhamentoTurma(
        Long id_turma,
        String nome,
        Long id_funcionario,
        Long id_materia,
        Date semestre_turma,
        List<AlunoTurma> alunosSituacao,
        List<TurmaAlunoNota> alunosNotas,
        List<TurmaAlunoFrequencia> alunosFrequencia
) {
    public DadosDetalhamentoTurma(Turma turma){
        this(
                turma.getId_turma(),
                turma.getNome(),
                turma.getId_funcionario(),
                turma.getId_materia(),
                turma.getSemestre_turma(),
                turma.getAlunosSituacao(),
                turma.getAlunosNotas(),
                turma.getAlunosFrequencia()
        );
    }
}
