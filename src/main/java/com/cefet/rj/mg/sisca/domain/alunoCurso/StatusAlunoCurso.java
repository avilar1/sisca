package com.cefet.rj.mg.sisca.domain.alunoCurso;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "STATUS_ALUNO_CURSO")
@Entity(name = "StatusAlunoCurso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusAlunoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}