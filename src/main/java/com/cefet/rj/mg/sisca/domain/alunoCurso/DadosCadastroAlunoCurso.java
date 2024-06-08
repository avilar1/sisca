package com.cefet.rj.mg.sisca.domain.alunoCurso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.curso.Curso;

public record DadosCadastroAlunoCurso(
        Aluno aluno,
        Curso curso,
        StatusAlunoCurso status,
        String ano_matricula
) {
}
