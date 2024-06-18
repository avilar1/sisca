package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TURMA_ALUNO")
@Entity(name = "AlunoTurma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurma {

        @Id
        @EmbeddedId
        private TurmaAlunoId id;

        @ManyToOne
        @MapsId("idAluno")
        @JoinColumn(name = "id_aluno")
        private Aluno aluno;

        @ManyToOne
        @MapsId("idTurma")
        @JoinColumn(name = "id_turma")
        private Turma turma;

        @Enumerated(EnumType.STRING)
        @Column(name = "status_aluno_turma")
        private StatusAlunoTurma  situacao;

        @OneToOne
        @JoinColumns({
                @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno"),
                @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
        })
        private TurmaAlunoNota turmaAlunoNota;

        @OneToMany
        @JoinColumns({
                @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno"),
                @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
        })
        @JsonBackReference
        private List<TurmaAlunoFrequencia> turmaAlunoFrequencia;

        public AlunoTurma(Turma turma, Aluno aluno, StatusAlunoTurma situacao) {
                TurmaAlunoId turmaAlunoId = new TurmaAlunoId(aluno.getId_aluno(), turma.getId_turma());

                this.id = turmaAlunoId;
                this.aluno = aluno;
                this.turma = turma;
                this.situacao = situacao;
        }

        public void trancarMatricula() {
                this.situacao = StatusAlunoTurma.TRANCADO;
        }

        public boolean isMatriculaTrancada() {
                return this.situacao == StatusAlunoTurma.TRANCADO;
        }


//
//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Frequencia> frequencias;

}

