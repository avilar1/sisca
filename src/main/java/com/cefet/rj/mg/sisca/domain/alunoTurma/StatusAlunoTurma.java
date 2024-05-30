package com.cefet.rj.mg.sisca.domain.alunoTurma;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "STATUS_ALUNO_TURMA")
@Entity(name = "StatusAlunoTurma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusAlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
