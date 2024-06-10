package com.cefet.rj.mg.sisca.domain.alunoCurso;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoCursoId implements Serializable {

    private Long id_curso;
    private Long id_aluno;
}

