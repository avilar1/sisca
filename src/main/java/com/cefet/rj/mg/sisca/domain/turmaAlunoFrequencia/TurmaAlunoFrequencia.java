package com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Table(name = "TURMA_ALUNO_FREQUENCIA")
@Entity(name = "TurmaAlunoFrequencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TurmaAlunoFrequencia {

    @EmbeddedId
    private TurmaAlunoFrequenciaId id;

    @ManyToOne
    @MapsId("idAluno")
    @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno")
    @JsonManagedReference
    private Aluno aluno;

    @ManyToOne
    @MapsId("idTurma")
    @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
    @JsonManagedReference
    private Turma turma;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "faltou")
    private Date faltou;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno"),
            @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
    })
    @JsonIgnoreProperties
    private AlunoTurma alunoTurma;

    public TurmaAlunoFrequencia( Aluno aluno, Turma turma, Date faltou) {
        this.id = new TurmaAlunoFrequenciaId(aluno.getId_aluno(), turma.getId_turma());
        this.aluno = aluno;
        this.turma = turma;
        this.faltou = faltou;
    }
}

