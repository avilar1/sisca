package com.cefet.rj.mg.sisca.domain.almoxarifado;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "ESTOQUE")
@Entity(name = "Estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estoque;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(nullable = false)
    private float quantidade;

    private String descricao;

    @Column(name = "data_transacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataTransacao;

    public Estoque(DadosCadastroEstoque dados, Funcionario funcionario, Produto produto) {
        this.produto = produto;
        this.funcionario = funcionario;
        this.quantidade = dados.quantidade();
        this.descricao = dados.descricao();
        this.dataTransacao = dados.dataTransacao();
    }
}
