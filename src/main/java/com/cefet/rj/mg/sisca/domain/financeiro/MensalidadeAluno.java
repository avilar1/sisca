package com.cefet.rj.mg.sisca.domain.financeiro;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "MENSALIDADE_ALUNO")
@Entity(name = "MensalidadeAluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_mensalidade_aluno")
public class MensalidadeAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mensalidade_aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @Column(nullable = false)
    private float valor;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;
}
