package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;

import com.cefet.rj.mg.sisca.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Nota> notas;
//
//        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
//        private List<Frequencia> frequencias;

}

