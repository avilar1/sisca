package com.cefet.rj.mg.sisca.domain.aluno;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "ALUNO")
@Entity(name = "Aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id_aluno;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "matricula_aluno", nullable = false, unique = true)
    private int matricula_aluno;

    /*
    Status:
    1 - ativo
    2 - trancado
    3 - formado
    4 - licença especial
    0 - desmatriculado/excluído
     */
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "ano_matricula", length = 6)
    private String ano_matricula;

    @Column(name = "valor_mensalidade", precision = 10, scale = 2)
    private BigDecimal valor_mensalidade;

//    @OneToMany(mappedBy = "aluno")
//    private List<AlunoTurma> alunoTurmas;


    @OneToMany
    @JoinTable(
            name = "ALUNO_CURSO",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    @JsonManagedReference
    private List<Curso> cursos;

    public Aluno(DadosCadastroAluno dados, Usuario usuario) {
        this.usuario = usuario;
        this.matricula_aluno = dados.matricula_aluno();
        this.status = dados.status();
        this.ano_matricula = dados.ano_matricula();
    }


    public void atualizarAluno(DadosAtualizaAluno dados) {
        if (dados.matricula_aluno() != null) {
            this.matricula_aluno = dados.matricula_aluno();
        }
        if(dados.status() != null) {
            this.status = dados.status();
        }
        if(dados.ano_matricula() != null) {
            this.ano_matricula = dados.ano_matricula();
        }
    }

    public void setId_aluno(long l) {
    }

    public void setNome(String testeAluno) {
    }
}
