package com.cefet.rj.mg.sisca.domain.aluno;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "ALUNO")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_aluno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "matricula_aluno", nullable = false, unique = true)
    private int matricula_aluno;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "ano_matricula", length = 6)
    private String ano_matricula;

    @Column(name = "valor_mensalidade", precision = 10, scale = 2)
    private BigDecimal valor_mensalidade;

//    @OneToMany(mappedBy = "aluno")
//    private List<AlunoTurma> alunoTurmas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ALUNO_CURSO",
            joinColumns = @JoinColumn(name = "matricula_aluno", referencedColumnName = "matricula_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<Curso> cursos;
}
