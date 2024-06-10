package com.cefet.rj.mg.sisca.domain.cursoGrade;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CursoGradeId implements Serializable {

    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "id_materia")
    private Long idMateria;

    public CursoGradeId(DadosCadastroCursoGrade dados) {
        this.idCurso = dados.id_curso();
        this.idMateria = dados.id_materia();
    }
}
