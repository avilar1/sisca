package com.cefet.rj.mg.sisca.domain.biblioteca;

import java.util.Date;

public record DadosCadastroEmprestimo(
        Long idLivro,
        Long idUsuario,
        Date emprestado,
        Date limiteEmprestimo
) {}