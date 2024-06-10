package com.cefet.rj.mg.sisca.domain.aluno;

public record DadosAtualizaAluno(

        Long id_aluno,

        Integer matricula_aluno,
        Integer status,
        String ano_matricula,
        Long id_curso
) {
}
