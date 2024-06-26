package com.cefet.rj.mg.sisca.domain.aluno;

import com.cefet.rj.mg.sisca.domain.curso.Curso;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoAluno(
        Long id_aluno,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        int matricula_aluno,
        int status,
        String ano_matricula,
        BigDecimal valor_mensalidade,
        List<Curso> cursos
) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId_aluno(),
                aluno.getUsuario().getNome(),
                aluno.getUsuario().getCpf(),
                aluno.getUsuario().getEmail(),
                aluno.getUsuario().getTelefone(),
                aluno.getUsuario().getData_nascimento(),
                aluno.getMatricula_aluno(),
                aluno.getStatus(),
                aluno.getAno_matricula(),
                aluno.getValor_mensalidade(),
                aluno.getCursos()
        );
    }
}
