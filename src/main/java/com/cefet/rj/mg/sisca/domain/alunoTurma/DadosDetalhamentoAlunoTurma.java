package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;

public record DadosDetalhamentoAlunoTurma(
        TurmaAlunoId Id_turma,
        Long id_aluno,
        String nome_aluno,
        Long id_funcionario,
        String nome_funcionario,
        Long id_materia,
        String nome_materia,
        String semestre_turma,
        StatusAlunoTurma situacao

) {
    public DadosDetalhamentoAlunoTurma(AlunoTurma alunoTurma) {
        this(
                alunoTurma.getId(),
                alunoTurma.getAluno().getId_aluno(),
                alunoTurma.getAluno().getUsuario().getNome(),
                alunoTurma.getTurma().getProfessor().getId_funcionario(),
                alunoTurma.getTurma().getProfessor().getUsuario().getNome(),
                alunoTurma.getTurma().getMateria().getId_materia(),
                alunoTurma.getTurma().getMateria().getNome(),
                alunoTurma.getTurma().getSemestre_turma(),
                alunoTurma.getSituacao());
    }
}
