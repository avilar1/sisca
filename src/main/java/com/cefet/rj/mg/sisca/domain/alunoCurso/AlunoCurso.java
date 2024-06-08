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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_aluno")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private StatusAlunoCurso status;

    @Column(name = "ano_matricula", length = 6)
    private String ano_matricula;

    public AlunoCurso(DadosCadastroAlunoCurso dados) {

        this.aluno = dados.aluno();
        this.curso = dados.curso();
        this.status = dados.status();
        this.ano_matricula = dados.ano_matricula();
    }
}
