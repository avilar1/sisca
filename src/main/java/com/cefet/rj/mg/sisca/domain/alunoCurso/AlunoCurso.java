package com.cefet.rj.mg.sisca.domain.alunoCurso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ALUNO_CURSO")
@Entity(name = "AlunoCurso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoCurso {

    @EmbeddedId
    private AlunoCursoId id;

    @OneToOne
    @MapsId("id_curso")
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToOne
    @MapsId("id_aluno")
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(nullable = false)
    private int id_status;

    @Column(name = "ano_matricula", nullable = false, length = 6)
    private String ano_matricula;

    public AlunoCurso(Aluno aluno, Curso curso, int status, String ano_matricula) {
        AlunoCursoId id = new AlunoCursoId(curso.getId_curso(), aluno.getId_aluno());
        this.aluno = aluno;
        this.curso = curso;
        this.id = id;
        this.id_status = status;
        this.ano_matricula = ano_matricula;
    }
}
