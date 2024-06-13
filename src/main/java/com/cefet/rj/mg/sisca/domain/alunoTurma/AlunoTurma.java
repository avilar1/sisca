package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.TurmaService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Table(name = "TURMA_ALUNO")
@Entity(name = "AlunoTurma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurma {

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


//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Nota> notas;
//
//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Frequencia> frequencias;

}

