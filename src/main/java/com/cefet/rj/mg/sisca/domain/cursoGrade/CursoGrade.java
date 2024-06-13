package com.cefet.rj.mg.sisca.domain.cursoGrade;


import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "CURSO_GRADE")
@Entity(name = "CursoGrade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CursoGrade {

    @EmbeddedId
    private CursoGradeId id;

    @ManyToOne
    @MapsId("idCurso")
    @JoinColumn(name = "id_curso")
    @JsonBackReference
    private Curso curso;

    @ManyToOne
    @MapsId("idMateria")
    @JoinColumn(name = "id_materia")
    @JsonManagedReference
    private Materia materia;

    @Column(name = "periodo", nullable = false)
    private int periodo;

    public CursoGrade(DadosCadastroCursoGrade dados, Curso curso, Materia materia) {
        this.id = new CursoGradeId(dados);
        this.curso = curso;
        this.materia = materia;
        this.periodo = dados.periodo();
    }

    public void atualizaPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
