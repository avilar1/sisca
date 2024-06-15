package com.cefet.rj.mg.sisca.domain.biblioteca;

public record DadosAtualizacaoLivro(
        Long id,
        String titulo,
        String autor,
        String editor
) {}