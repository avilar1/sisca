package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoAlunoTurma(
        TurmaAlunoId Id_turma,
        StatusAlunoTurma situacao,
        Long id_aluno,
        String nome_aluno,
        Long id_funcionario,
        String nome_funcionario,
        Long id_materia,
        String nome_materia,
        String semestre_turma,
        Float p1,
        Float p2,
        Float pf,
        List<Date> faltas

) {
    public DadosDetalhamentoAlunoTurma(AlunoTurma alunoTurma) {
        this(
                alunoTurma.getId(),
                alunoTurma.getSituacao(),
                alunoTurma.getAluno()  != null ? alunoTurma.getAluno().getId_aluno() : null,
                alunoTurma.getAluno()  != null ? alunoTurma.getAluno().getUsuario().getNome() : null,
                alunoTurma.getTurma()  != null ? alunoTurma.getTurma().getProfessor().getId_funcionario() : null,
                alunoTurma.getTurma()  != null ? alunoTurma.getTurma().getProfessor().getUsuario().getNome() : null,
                alunoTurma.getTurma()  != null ? alunoTurma.getTurma().getMateria().getId_materia() : null,
                alunoTurma.getTurma()  != null ? alunoTurma.getTurma().getMateria().getNome() : null,
                alunoTurma.getTurma()  != null ? alunoTurma.getTurma().getSemestre_turma() : null,
                alunoTurma.getTurmaAlunoNota() != null ? alunoTurma.getTurmaAlunoNota().getP1() : null,
                alunoTurma.getTurmaAlunoNota() != null ? alunoTurma.getTurmaAlunoNota().getP2() : null,
                alunoTurma.getTurmaAlunoNota() != null ? alunoTurma.getTurmaAlunoNota().getPf() : null,
                alunoTurma.getTurmaAlunoFrequencia() != null ? alunoTurma.getTurmaAlunoFrequencia().stream().map(TurmaAlunoFrequencia::getFaltou).collect(Collectors.toList()) : null
        );
    }
}
