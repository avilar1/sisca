package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCurso;
import com.cefet.rj.mg.sisca.domain.cursoGrade.CursoGrade;
import com.cefet.rj.mg.sisca.domain.materia.Materia;

import java.util.List;

public record DadosDetalhamentoCurso(
        Long id_curso,
        String nome,
        int periodos,
        List<CursoGrade> cursoGrades
) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(
                curso.getId_curso(),
                curso.getNome(),
                curso.getPeriodos(),
                curso.getCursoGrades()
        );
    }
}
