package com.cefet.rj.mg.sisca.domain.turmaAlunoNota;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "TURMA_ALUNO_NOTA")
@Entity(name = "TurmaAlunoNota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"matricula_aluno", "id_turma"})
public class TurmaAlunoNota {

    @EmbeddedId
    private TurmaAlunoNotaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("matriculaAluno")
    @JoinColumn(name = "matricula_aluno")
    private Aluno matricula_aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTurma")
    @JoinColumn(name = "id_turma")
    private Turma id_turma;

    private Float p1;
    private Float p2;
    private Float pf;
}

