package com.cefet.rj.mg.sisca.domain.turma;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
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

    private String nome;


    @Column(name = "id_funcionario")
    private Long id_funcionario;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "professor_id")
//    private Professor professor;

    private Long id_materia;

    private Date semestre_turma = new Date();

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlunoTurma> alunosSituacao;

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurmaAlunoNota> alunosNotas;

    @OneToMany(mappedBy = "id_turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurmaAlunoFrequencia> alunosFrequencia;

    public Turma(DadosCadastroTurma dadosCadastroTurma) {
        this.id_materia = dadosCadastroTurma.id_materia();
        this.id_funcionario = dadosCadastroTurma.id_funcionario();
        this.semestre_turma = dadosCadastroTurma.semestre_turma();
        this.nome = this.id_turma.toString() == null ? "0" +
                dadosCadastroTurma.id_funcionario().toString() +
                dadosCadastroTurma.semestre_turma().toString() :
                dadosCadastroTurma.id_materia().toString() +
                dadosCadastroTurma.id_funcionario().toString() +
                dadosCadastroTurma.semestre_turma().toString();
    }




}
