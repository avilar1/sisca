package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCurso;
import com.cefet.rj.mg.sisca.domain.materia.Materia;

import java.util.List;

public record DadosDetalhamentoCurso(
        String nome,
        int periodos,
        List<Materia> materias
) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getNome(),
                curso.getPeriodos(),
                curso.getMaterias());
    }
}
