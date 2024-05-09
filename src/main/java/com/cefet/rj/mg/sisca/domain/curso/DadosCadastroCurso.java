package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;

import java.util.List;

public record DadosCadastroCurso(
        String nome,
        int periodos,
        List<Aluno> alunos
) {
}
