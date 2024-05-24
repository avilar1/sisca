package com.cefet.rj.mg.sisca.domain.TurmaAlunoFrequencia;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TurmaAlunoFrequenciaId implements Serializable {
    private Long matriculaAluno;
    private Long idTurma;
}
