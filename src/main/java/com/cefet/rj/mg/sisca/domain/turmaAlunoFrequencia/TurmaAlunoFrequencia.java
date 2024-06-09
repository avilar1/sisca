package com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "TURMA_ALUNO_FREQUENCIA")
@Entity(name = "TurmaAlunoFrequencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"matricula_aluno", "id_turma", "faltou"})
public class TurmaAlunoFrequencia {

    @EmbeddedId
    private TurmaAlunoFrequenciaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("matriculaAluno")
    @JoinColumn(name = "matricula_aluno")
    private Aluno matricula_aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTurma")
    @JoinColumn(name = "id_turma")
    private Turma id_turma;

    private LocalDate faltou;

    public TurmaAlunoFrequencia( Aluno aluno, Turma turma, LocalDate faltou) {
        this.id = new TurmaAlunoFrequenciaId(aluno.getId_aluno(), turma.getId_turma());
        this.matricula_aluno = aluno;
        this.id_turma = turma;
        this.faltou = faltou;
    }
}

