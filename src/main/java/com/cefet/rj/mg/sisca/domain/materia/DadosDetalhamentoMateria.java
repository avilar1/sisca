package com.cefet.rj.mg.sisca.domain.materia;

import com.cefet.rj.mg.sisca.domain.curso.Curso;

import java.util.List;

public record DadosDetalhamentoMateria(
        String nome
) {
    public DadosDetalhamentoMateria(Materia materia){
        this(materia.getNome());
    }
}
