package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.turma.Turma;

import java.util.List;

public record DadosDetalhamentoCurso(
        String nome,
        int periodos,
        List<Aluno> alunos,
        List<Turma> turmas,
        List<Materia> materias
) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getNome(),
                curso.getPeriodos(),
                curso.getAlunos(),
                curso.getTurmas(),
                curso.getMaterias());
    }
}
