package com.cefet.rj.mg.sisca.domain.turma;

import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TURMA")
@Entity(name = "Turma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_turma;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlunoTurma> alunosSituacao;

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurmaAlunoNota> alunosNotas;

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurmaAlunoFrequencia> alunosFrequencia;
}
