package com.cefet.rj.mg.sisca.domain.alunoTurma;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.frequencia.Frequencia;
import com.cefet.rj.mg.sisca.domain.nota.Nota;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "ALUNOTURMA")
@Entity(name = "AlunoTurma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoTurma {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "aluno_id")
        private Aluno aluno;

        @ManyToOne
        @JoinColumn(name = "turma_id")
        private Turma turma;

        private String situacao;

        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
        private List<Nota> notas;

        @OneToMany(mappedBy = "alunoTurma", cascade = CascadeType.ALL)
        private List<Frequencia> frequencias;


}
