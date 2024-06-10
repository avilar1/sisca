package com.cefet.rj.mg.sisca.domain.cursoGrade;


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

    public CursoGrade(DadosCadastroCursoGrade dados) {
        this.id = new CursoGradeId(dados);
    }
}
