package com.cefet.rj.mg.sisca.domain.biblioteca;

import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date emprestado;

    @Column(name = "limite_emprestimo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date limiteEmprestimo;

    @Column(name = "data_entrega")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    public Emprestimo(DadosCadastroEmprestimo dados, Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.emprestado = dados.emprestado();
        this.limiteEmprestimo = dados.limiteEmprestimo();
    }
}
