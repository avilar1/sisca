package com.cefet.rj.mg.sisca.domain.turma;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "TURMA")
@Entity(name = "Turma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_turma;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @Column(name = "semestre_turma", nullable = false, length = 6)
    private String semestre_turma;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_funcionario")
//    private Professor professor;

//    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AlunoTurma> alunosSituacao;
//
//    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TurmaAlunoNota> alunosNotas;
//
//    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TurmaAlunoFrequencia> alunosFrequencia;

    public Turma(DadosCadastroTurma dadosCadastroTurma, Professor professor, Materia materia) {
        this.professor = professor;
        this.materia = materia;
        this.semestre_turma = dadosCadastroTurma.semestre_turma();
    }

    public void atualizar(DadosAtualizaTurma dadosAtualizaTurma, Materia materia, Professor professor) {
        if(dadosAtualizaTurma.semestre_turma() != null) {
            this.semestre_turma = dadosAtualizaTurma.semestre_turma();
        }

        this.materia = materia;
        this.professor = professor;

    }




}
