package com.cefet.rj.mg.sisca.domain.curso;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCurso;
import com.cefet.rj.mg.sisca.domain.cursoGrade.CursoGrade;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @Column(name = "id_curso")
    private Long id_curso;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int periodos;

    @ManyToMany(mappedBy = "cursos")
    @JsonBackReference
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "curso")
    private List<CursoGrade> cursoGrades;


    public Curso(DadosCadastroCurso dados){
        this.nome = dados.nome();
        this.periodos = dados.periodos();
    }

    public void atualizarCurso(DadosAtualizaCurso dados) {

        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.periodos() != null) {
            this.periodos = dados.periodos();
        }

    }
}
