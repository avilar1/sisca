package com.cefet.rj.mg.sisca.domain.aluno;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;

import java.util.List;

public record DadosCadastroAluno(
        Usuario usuario,
        int matricula_aluno,
        int status,
        String ano_matricula,
        List<Curso> cursos
) {

}
