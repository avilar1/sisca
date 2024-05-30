package com.cefet.rj.mg.sisca.domain.biblioteca;

import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "EMPRESTIMO")
@Entity(name = "Emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_emprestimo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate emprestado;

    @Column(name = "limite_emprestimo", nullable = false)
    private LocalDate limiteEmprestimo;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;
}
