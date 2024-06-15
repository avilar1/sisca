package com.cefet.rj.mg.sisca.domain.biblioteca;

public record DadosDetalhamentoLivro(
        Long id,
        String titulo,
        String autor,
        String editor
) {}
