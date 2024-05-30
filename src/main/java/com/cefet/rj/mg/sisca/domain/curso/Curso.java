package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "CURSO")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int periodos;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
            name = "CURSO_MATERIA",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materias;

}
