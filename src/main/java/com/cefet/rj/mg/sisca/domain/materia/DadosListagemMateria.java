package com.cefet.rj.mg.sisca.domain.materia;

public record DadosListagemMateria(
        Long id_materia,
        String nome
) {

    public DadosListagemMateria(Materia materia) {
        this(
                materia.getId_materia(),
                materia.getNome()
        );
    }
}
