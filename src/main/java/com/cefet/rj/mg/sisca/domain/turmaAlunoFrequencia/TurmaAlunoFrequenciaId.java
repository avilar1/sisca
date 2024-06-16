package com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TurmaAlunoFrequenciaId implements Serializable {
    @Column(name = "id_aluno")
    private Long idAluno;

    @Column(name = "id_turma")
    private Long idTurma;
}
