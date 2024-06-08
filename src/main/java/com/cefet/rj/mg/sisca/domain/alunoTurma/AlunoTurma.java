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

@Table(name = "ALUNOTURMA")
@Entity(name = "AlunoTurma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"matricula_aluno", "id_turma"})
public class AlunoTurma {

        @EmbeddedId
        private TurmaAlunoId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("matriculaAluno")
        @JoinColumn(name = "matricula_aluno")
        private Aluno matricula_aluno;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("idTurma")
        @JoinColumn(name = "id_turma")
        private Turma id_turma;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_status_aluno_turma")
        private StatusAlunoTurma situacao;

        public AlunoTurma(Long idTurma, Long idAluno) {
                AlunoService alunoService = new AlunoService();
                TurmaService turmaService = new TurmaService();
                TurmaAlunoId turmaAlunoId = new TurmaAlunoId(idAluno, idTurma);
                this.id = turmaAlunoId;
                this.matricula_aluno = alunoService.detalharaluno(idAluno);
                Optional<Turma> turmaOptional = turmaService.encontrarTurma(idTurma);
                if (turmaOptional.isPresent()) {
                        this.id_turma = turmaOptional.get();
                } else {
                        throw new IllegalArgumentException("Turma com id " + idTurma + " não encontrada");
                }

        }


//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Nota> notas;
//
//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Frequencia> frequencias;

}

