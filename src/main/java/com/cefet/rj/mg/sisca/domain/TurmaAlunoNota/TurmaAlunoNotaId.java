package com.cefet.rj.mg.sisca.domain.TurmaAlunoNota;

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
public class TurmaAlunoNotaId implements Serializable {
    private Long matriculaAluno;
    private Long idTurma;
}
