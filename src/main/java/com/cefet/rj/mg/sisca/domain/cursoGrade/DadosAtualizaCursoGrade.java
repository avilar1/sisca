package com.cefet.rj.mg.sisca.domain.cursoGrade;

public record DadosAtualizaCursoGrade(
        Long id_curso,
        Long id_materia,
        int periodo
) {

    public DadosAtualizaCursoGrade(CursoGrade cursoGrade) {
        this(
            cursoGrade.getCurso().getId_curso(),
            cursoGrade.getMateria().getId_materia(),
            cursoGrade.getPeriodo()
        );
    }
}
