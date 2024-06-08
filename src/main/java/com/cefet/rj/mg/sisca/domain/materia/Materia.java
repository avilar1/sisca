package com.cefet.rj.mg.sisca.domain.materia;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.interfaces.CursoInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "MATERIA")
@Entity(name = "Materia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_materia")
public class Materia implements CursoInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "materias")
    private List<Curso> cursos;

    public Materia(DadosCadastroMateria dados, Long id_curso) {
        this.nome = dados.nome();
        Curso curso = retornarCurso(id_curso);
        this.cursos.add(curso);
    }

    public void atualizarMateria(DadosAtualizaMateria dados){

    }

}
